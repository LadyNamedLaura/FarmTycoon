package persistence.mappers;

import java.util.HashMap;
import java.util.Map;

import domain.Savable;

public class Clock implements persistence.Mapper {
	public domain.Clock load(Map<String, Object> data) {
		return new domain.Clock((Double) data.get("Multi"),
				(Long) data.get("Offset"));
	}

	public Map<String, Object> save(Savable obj) {
		Map<String, Object> ret = new HashMap<String, Object>();
		domain.Clock clock = (domain.Clock) obj;
		ret.put("Offset", clock.getOffset());
		ret.put("Multi", clock.getMultiplier());
		return ret;
	}

	public Map<String, String> getFields() {
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("Offset", "BIGINT");
		fields.put("Multi", "DOUBLE");
		return fields;
	}
}