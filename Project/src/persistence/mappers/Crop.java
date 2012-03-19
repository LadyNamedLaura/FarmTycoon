package persistence.mappers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Crop implements persistence.Mapper {
	@SuppressWarnings("serial")
	private static final Map<String, String> fields = new HashMap<String, String>() {
		{
			put("type", "TEXT");
			put("planted", "BIGINT");
		}
	};

	public domain.tiles.Crop load(Map<String, Object> data) throws SQLException {
		return new domain.tiles.Crop((String) data.get("type"),
				(Long) data.get("planted"));
	}

	public Map<String, Object> save(domain.Savable obj) {
		domain.tiles.Crop crop = (domain.tiles.Crop) obj;
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("type", "'" + crop.getType() + "'");
		ret.put("planted", crop.getPlanted().getTime());
		return ret;
	}

	public Map<String, String> getFields() {
		return fields;
	}
}
