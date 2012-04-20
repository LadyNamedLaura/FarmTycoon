package persistence.mappers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.Coordinate;

public class Tile implements persistence.Mapper {
	public domain.Tile load(Map<String, Object> data) {
		domain.TileState state;
		if ((Integer) data.get("stateid") == -1) {
			state = domain.tiles.StateList.valueOf((String) data.get("state"))
					.getNew();
		} else {
			try {
				state = (domain.TileState) persistence.MapperList.valueOf(
						(String) data.get("state")).loadById(
						(Integer) data.get("stateid"));
			} catch (Exception e) {
				e.printStackTrace();
				state = null;
			}
		}
		long expiryTime;
		if (data.get("expiryTime") instanceof Long)
			expiryTime = (Long) data.get("expiryTime");
		else
			expiryTime = (Integer) data.get("expiryTime");
		return new domain.Tile(new Coordinate((Integer) data.get("x"),
				(Integer) data.get("y")), state, expiryTime);
	}

	public Map<String, Object> save(domain.Savable obj) throws SQLException {
		domain.Tile tile = (domain.Tile) obj;
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("x", tile.getCoordinate().getX());
		ret.put("y", tile.getCoordinate().getY());
		ret.put("expiryTime", (Long) tile.getExpiryTime());
		ret.put("state", "'" + tile.getState().getStateType().name() + "'");
		if (tile.getState() instanceof domain.Savable) {
			domain.Savable state = (domain.Savable) tile.getState();
			ret.put("stateid", state.getId());
			state.save();
		} else {
			ret.put("stateid", -1);
		}
		return ret;
	}

	public Map<String, String> getFields() {
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("x", "INT");
		fields.put("y", "INT");
		fields.put("expiryTime", "BIGINT");
		fields.put("state", "TEXT");
		fields.put("stateid", "INT");
		return fields;
	}
}
