package persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import exceptions.DBConnectException;
import exceptions.SystemDBException;

/**
 * Enum containing all information needed to save Savable objects to the
 * database. Every Class which implements {@link domain.Savable} should have an
 * entry in this enum.
 * 
 * @author Rigès De Witte
 * @author Simon Peeters
 * @author Barny Pieters
 * @author Laurens Van Damme
 * 
 */
public enum MapperList {
	/**
	 * @see domain.Tile
	 */
	Tile(new persistence.mappers.Tile()),
	/**
	 * @see domain.Crop
	 */
	Crop(new persistence.mappers.Crop()),
	/**
	 * @see domain.Clock
	 */
	Clock(new persistence.mappers.Clock()),
	/**
	 * @see domain.Farm
	 */
	Farm(new persistence.mappers.Farm());

	private DB db;
	private String tablename;
	private Mapper mapper;
	private Map<String, String> fields;
	private int nextid = 0;

	private MapperList(Mapper mapper) {
		this("", mapper);
		this.tablename = name();
	}

	private MapperList(String name, Mapper mapper) {
		try {
			this.db = PersistenceController.getInstance().getDB();
		} catch (DBConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mapper = mapper;
		this.tablename = name;
		this.fields = mapper.getFields();
	}

	/**
	 * Saves an {@link domain.savable} object to the database.
	 * 
	 * @param obj
	 *            object to save.
	 * @throws SQLException
	 * @see persistence.Mapper#save(domain.Savable)
	 */
	public void save(domain.Savable obj) throws SQLException {
		this.initIfNeed();
		java.sql.Statement st = db.getConnection().createStatement();
		ResultSet rs;
		String update = "";
		System.err.println("saving " + this.tablename + " with id = "
				+ obj.getId());
		Map<String, Object> values;
		rs = st.executeQuery(String.format(
				"SELECT COUNT(*) AS count FROM %s WHERE id = %d",
				this.tablename, obj.getId()));
		values = mapper.save(obj);
		if (rs.getInt("count") > 0) {
			// ugly format black magic
			update = String.format("UPDATE %s SET %s WHERE id = %d",
					this.tablename, "%s%s%s%4$s", obj.getId());
			for (Map.Entry<String, Object> e : values.entrySet())
				update = String.format(update, e.getKey(), " = ", e.getValue()
						.toString(), "%5$s %s%s%s%4$s", ",");
			update = String.format(update, "", "", "", "", "");
		} else {
			update = String.format(
					"INSERT INTO %s (%s%%s%%s%%s) VALUES(%s%%s%%s%%s)",
					this.tablename, "id", obj.getId());
			for (Map.Entry<String, Object> e : values.entrySet())
				update = String.format(update, ", ", e.getKey(), "%s%s%s", ",",
						e.getValue().toString(), "%s%s%s");
			update = String.format(update, "", "", "", "", "", "");
		}
		st.executeUpdate(update);
	}

	public void init() throws SQLException {
		java.sql.Statement st = db.getConnection().createStatement();
		st.executeUpdate(String.format("DROP TABLE IF EXISTS %s",
				this.tablename));
		this.initIfNeed();
	}

	public void initIfNeed() throws SQLException {
		java.sql.Statement st = db.getConnection().createStatement();
		String update = "id INTEGER PRIMARY KEY";
		System.err.println(this.name());
		System.err.println(fields);
		for (Map.Entry<String, String> e : this.fields.entrySet())
			update += ", " + e.getKey() + " " + e.getValue();
		st.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s (%s)",
				this.tablename, update));

	}

	public domain.Savable loadById(int id) throws SQLException,
			IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, ClassNotFoundException,
			InvocationTargetException {
		java.sql.Statement st = db.getConnection().createStatement();
		java.sql.ResultSet rs = st.executeQuery(String.format(
				"SELECT * FROM %s WHERE id = %d", this.tablename, id));
		java.sql.ResultSetMetaData meta = rs.getMetaData();

		Map<String, Object> data = new HashMap<String, Object>();
		int count = meta.getColumnCount();
		for (int i = 1; i <= count; i++) {
			System.err.println(meta.getColumnName(i) + " : " + rs.getObject(i));
			data.put(meta.getColumnName(i), rs.getObject(i));
		}
		return mapper.load(data);
	}

	public int getNextID() {
		return nextid++;
	}
}