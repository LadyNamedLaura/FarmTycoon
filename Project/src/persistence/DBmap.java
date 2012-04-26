package persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBmap extends HashMap<String, Object> {
	final Map<String, String> fields;

	public DBmap(Mapper mapper, java.sql.ResultSet rs) throws SQLException {
		this(mapper);
		java.sql.ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		for (int i = 1; i <= count; i++)
			put(meta.getColumnName(i), rs.getObject(i));
	}

	public DBmap(Mapper mapper) {
		fields = mapper.getFields();
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

	public String getUpdateSql(String tablename, int id) {
		String update = String.format("UPDATE %s SET %s WHERE id = %d",
				tablename, "%s%s%s%4$s", id);
		for (Map.Entry<String, Object> e : entrySet())
			update = String.format(update, e.getKey(), " = ",
					format(e.getValue()), "%5$s %s%s%s%4$s", ",");
		return String.format(update, "", "", "", "", "");
	}

	public String getInsertSql(String tablename, int id) {
		String update = String.format(
				"INSERT INTO %s (%s%%s%%s%%s) VALUES(%s%%s%%s%%s)", tablename,
				"id", id);
		for (Map.Entry<String, Object> e : entrySet())
			update = String.format(update, ", ", e.getKey(), "%s%s%s", ",",
					format(e.getValue()), "%s%s%s");
		return String.format(update, "", "", "", "", "", "");
	}

	private String format(Object obj) {
		if (obj instanceof String)
			return "'" + (String) obj + "'";
		return obj.toString();
	}
}
