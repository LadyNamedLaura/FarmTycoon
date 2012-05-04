package persistence.mappers;

import java.util.HashMap;
import java.util.Map;

import persistence.DBmap;

public class Crop implements persistence.Mapper {
	@SuppressWarnings("serial")
	public domain.tiles.Crop load(DBmap map) {
		return new domain.tiles.Crop(map.getStr("type"), map.getLong("planted"));
	}

	public DBmap save(domain.Savable obj) {
		domain.tiles.Crop crop = (domain.tiles.Crop) obj;
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
