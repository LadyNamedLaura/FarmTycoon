package domain;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Inventory extends HashMap<Product, Integer>{
	public static class InvItem extends Savable implements Map.Entry<Product, Integer> {
		Product key;
		Integer val;
		
		public InvItem(Map.Entry<Product, Integer> entry){
			this(entry.getKey(), entry.getValue());
		}
		public InvItem(Product key, Integer val){
			this.key = key;
			this.val = val;
			this.id = key.name().hashCode();
		}
		@Override
		public Product getKey() {
			return key;
		}

		@Override
		public Integer getValue() {
			return val;
		}

		@Override
		public Integer setValue(Integer value) {
			Integer old = val;
			val = value;
			return old;
		}
	}
	
	public Set<InvItem> itemSet(){
		HashSet<InvItem> set=new HashSet<InvItem>();
		for (Map.Entry<Product,Integer> entry : entrySet())
			set.add(new InvItem(entry));
		return set;
	}
	public Integer get(Product key) {
		Integer val = super.get(key);
		if(val==null)
			val=0;
		return val;
	}
	
	public Integer put(Map.Entry<Product, Integer> entry) {
		return put(entry.getKey(),entry.getValue());
	}
	public Integer add(Product key) {
		return add(key,1);
	}
	public Integer add(Product key, int amount) {
		super.put(key, get(key)+amount);
		return get(key);
	}
	
	public void save() throws SQLException{
		//persistence.MapperList.InvItem.init();
		for (InvItem item : itemSet())
			item.save();
	}
	
	public static Inventory load(Class<? extends Savable> type, int id) throws SQLException{
		return load();
	}
	public static Inventory load() throws SQLException {
		Inventory inv = new Inventory();
		for (Savable obj : InvItem.loadAll(InvItem.class))
			inv.put((InvItem) obj);
		return inv;
	}
	
}
