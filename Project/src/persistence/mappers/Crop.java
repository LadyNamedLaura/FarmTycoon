package persistence.mappers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import persistence.DBmap;

public class Crop implements persistence.Mapper {
	@SuppressWarnings("serial")
	public domain.tiles.Crop load(DBmap map) throws SQLException {
		return new domain.tiles.Crop(map.getStr("type"), map.getLong("planted"));
	}

	public DBmap save(domain.Savable obj) {
		domain.tiles.Crop crop = (domain.tiles.Crop) obj;
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("type", "'" + crop.getType() + "'");
		ret.put("planted", crop.getPlanted().getTime());
		return new DBmap(this, new String[] { "type", "planted" },
				new Object[] { crop.getType(), crop.getPlanted().getTime() });
	}

	public Map<String, String> getFields() {
		return new HashMap<String, String>() {
			{
				put("type", "TEXT");
				put("planted", "BIGINT");
			}
		};
	}
}
