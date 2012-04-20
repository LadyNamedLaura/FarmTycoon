package persistence.mappers;

import java.util.HashMap;
import java.util.Map;

public class InvItem implements persistence.Mapper {

	public domain.Inventory.InvItem load(Map<String, Object> data) {
		return new domain.Inventory.InvItem(
				domain.Product.valueOf((String) data.get("Product")),
				(Integer) data.get("Amount"));
	}

	public Map<String, Object> save(domain.Savable obj) {
		Map<String, Object> ret = new HashMap<String, Object>();
		domain.Inventory.InvItem item = (domain.Inventory.InvItem) obj;
		ret.put("Product", item.getKey().name());
		ret.put("Amount", item.getValue());
		return ret;
	}

	@Override
	public Map<String, String> getFields() {
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("Product", "TEXT");
		fields.put("Amount", "INT");
		return fields;
	}

}
