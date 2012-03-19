package persistence.mappers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Tile implements persistence.Mapper {
	@SuppressWarnings("serial")
	private static final Map<String, String> fields = new HashMap<String, String>() {
		{
			put("x", "INT");
			put("y", "INT");
			put("state", "TEXT");
			put("stateid", "INT");
		}
	};

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
				state = null;
			}
		}
		return new domain.Tile((Integer) data.get("x"),
				(Integer) data.get("y"), state);
	}

	public Map<String, Object> save(domain.Savable obj) throws SQLException {
		domain.Tile tile = (domain.Tile) obj;
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("x", tile.getCoords()[0]);
		ret.put("y", tile.getCoords()[1]);
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
		return fields;
	}
}
