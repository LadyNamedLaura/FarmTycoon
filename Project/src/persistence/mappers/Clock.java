package persistence.mappers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.Savable;

public class Clock implements persistence.Mapper {
	@SuppressWarnings("serial")
	private static final Map<String, String> fields = new HashMap<String, String>() {
		{
			put("Offset", "BIGINT");
			put("Multi", "DOUBLE");
		}
	};

	public domain.Clock load(Map<String, Object> data) {
		data.get("Multi");
		data.get("Offset");
		return new domain.Clock((Double) data.get("Multi"),
				(Long) data.get("Offset"));
	}

	public Map<String, Object> save(Savable obj) throws SQLException {
		Map<String, Object> ret = new HashMap<String, Object>();
		domain.Clock clock = (domain.Clock) obj;
		ret.put("Offset", clock.getOffset());
		ret.put("Multi", clock.getMultiplier());
		return ret;
	}

	public Map<String, String> getFields() {
		return fields;
	}
}
