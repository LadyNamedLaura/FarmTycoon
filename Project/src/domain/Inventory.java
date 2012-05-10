package domain;

import java.util.Map;
import java.util.NoSuchElementException;

import exceptions.InventoryFullException;

public class Inventory extends java.util.AbstractMap<Product, Integer> {
	public static final int DEFAULTSPACE=5;
	public static final int BARNSPACE=25;
	private final Product[] keys = Product.values();
	private final int size = keys.length;
	private int barncount;

	private final int[] vals = new int[keys.length];
	
	public Inventory(int barncount){
		this.barncount=barncount;
	}
	
	public void setBarnCount(int count){
		this.barncount = count;
	}

	public int spaceLeft() {
		return getSize()-count();
	}
	public int getSize() {
		return DEFAULTSPACE + (BARNSPACE * barncount);
	}
	public int count() {
		int count=0;
		for(int val:values())
			count+=val;
		return count;
	}
	public Integer add(Product key) throws InventoryFullException {
		return add(key,1);
	}
	public Integer add(Product key, int amount) throws InventoryFullException {
		if(amount > spaceLeft())
			throw new InventoryFullException();
		put(key, get(key)+amount);
		return get(key);
	}
	
	public Integer remove(Product key, int amount) {
		vals[key.ordinal()] -= amount;
		return get(key);
	}
	
	public void save() throws java.sql.SQLException{
		for (Entry<Product, Integer> e : entrySet())
			((InvItem) e).save();
	}

	public void load() throws java.sql.SQLException {
		InvItem.loadAll(InvItem.class);
	}

	public Integer put(Product key, Integer value) {
		int index = key.ordinal();
		int oldValue = vals[index];
		vals[index] = value;
		return oldValue;
	}

	public void clear() {
		java.util.Arrays.fill(vals, 0);
	}
	
	public java.util.Set<Product> keySet() {
		return new KeySet();
	}
	public java.util.Collection<Integer> values() {
		return new Values();
	}
	public java.util.Set<Map.Entry<Product, Integer>> entrySet() {
		return new EntrySet();
	}

	private class KeySet extends java.util.AbstractSet<Product> {
		public java.util.Iterator<Product> iterator() {
			return new InventoryIterator<Product>() {
				protected Product get(int i) {
					return keys[i];
				}
			};
		}
		public int size() {
			return size;
		}
	}

	private class Values extends java.util.AbstractCollection<Integer> {
		public java.util.Iterator<Integer> iterator() {
			return new InventoryIterator<Integer>(){
				protected Integer get(int i) {
					return vals[i];
				}
			};
		}
		public int size() {
			return size;
		}
	}

	private class EntrySet extends java.util.AbstractSet<Map.Entry<Product, Integer>> {
		public java.util.Iterator<Map.Entry<Product, Integer>> iterator() {
			return new InventoryIterator<Entry<Product, Integer>>() {
				public Entry<Product, Integer> get(int i) {
					return new InvItem(i);
				}
			};
		}
		public int size() {
			return size;
		}
	}

	private abstract class InventoryIterator<T> implements java.util.Iterator<T> {
		int index = 0;

		public boolean hasNext() {
			return index != size;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return get(index++);
		}
		protected abstract T get(int i);
	}

	public class InvItem extends Savable implements Map.Entry<Product, Integer> {
		public InvItem(int index) {
			if (index >= size || index < 0)
				throw new NoSuchElementException();
			this.id = index;
		}

		public Product	getKey()	{ return keys[id]; }
		public Integer	getValue()	{ return vals[id]; }
		public String	toString()	{ return getKey() + "=" + getValue(); }

		public Integer setValue(Integer value) {
			Integer oldValue = vals[id];
			vals[id] = value;
			return oldValue;
		}
	}
}