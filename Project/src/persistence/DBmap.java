package persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import api.StringJoiner;

public class DBmap extends HashMap<String, Object> {
	private final Mapper mapper;

	public DBmap(Mapper mapper, java.sql.ResultSet rs) throws SQLException {
		this(mapper);
		java.sql.ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		for (int i = 1; i <= count; i++)
			put(meta.getColumnName(i), rs.getObject(i));
	}

	public DBmap(Mapper mapper) {
		this.mapper = mapper;
	}

	public DBmap(Mapper mapper, String[] keys, Object[] vals) {
		this(mapper);
		for (int i = 0; i < keys.length; i++)
			put(keys[i], vals[i]);
	}

	public int getInt(String key) {
		return (Integer) get(key);
	}

	public double getDouble(String key) {
		return (Double) get(key);
	}

	public String getStr(String key) {
		return (String) get(key);
	}

	public long getLong(String key) {
		if (get(key) instanceof Long)
			return (Long) get(key);
		return (Integer) get(key);
	}

	public String getUpdateSql() {
		if(get("id")==null)
			return null;
		return getUpdateSql(getInt("id"));
	}
	public String getUpdateSql(int id) {
		StringJoiner pairs = new StringJoiner(",");
		for (Map.Entry<String, Object> e : entrySet())
			pairs.add(e.getKey() + " = " + format(e.getValue()));
		return String.format("UPDATE %s SET %s WHERE id = %d", mapper
				.getClass().getSimpleName(), pairs.toString(), id);
	}

	public String getInsertSql() {
		if(get("id")==null)
			return null;
		return getInsertSql(getInt("id"));
	}
	public String getInsertSql(int id) {
		StringJoiner names = new StringJoiner(",", "id");
		StringJoiner values = new StringJoiner(",", id);
		for (Map.Entry<String, Object> e : entrySet()) {
			names.add(e.getKey());
			values.add(format(e.getValue()));
		}
		return String.format("INSERT INTO %s (%s) VALUES(%s);", mapper
				.getClass().getSimpleName(), names.toString(), values
				.toString());
	}

	private String format(Object obj) {
		if (obj instanceof String)
			return "'" + (String) obj + "'";
		return obj.toString();
	}
}
