package persistence.mappers;

import java.util.HashMap;
import java.util.Map;

public class Farm implements persistence.Mapper {
	@SuppressWarnings("serial")
	private static final Map<String, String> fields = new HashMap<String, String>() {
		{
			put("cash", "INT");
		}
	};

	public domain.Farm load(Map<String, Object> data) {
		return new domain.Farm((Integer) data.get("cash"), true);
	}

	public Map<String, Object> save(domain.Savable obj) {
		Map<String, Object> ret = new HashMap<String, Object>();
		domain.Farm farm = (domain.Farm) obj;
		ret.put("cash", farm.getCash());
		return ret;
	}

	public Map<String, String> getFields() {
		return fields;
	}
}
