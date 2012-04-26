package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	TILE(new persistence.mappers.Tile()),
	/**
	 * @see domain.Crop
	 */
	CROP(new persistence.mappers.Crop()),
	/**
	 * @see domain.Clock
	 */
	CLOCK(new persistence.mappers.Clock()),
	/**
	 * @see domain.Farm
	 */
	FARM(new persistence.mappers.Farm()),
	/**
	 * @see domain.Inventory.InvItem
	 */
	INVITEM(new persistence.mappers.InvItem());

	private DB db;
	private String tablename;
	private final Mapper mapper;
	private final Map<String, String> fields;
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
		rs = st.executeQuery(String.format(
				"SELECT COUNT(*) AS count FROM %s WHERE id = %d",
				this.tablename, obj.getId()));
		mapper.save(obj);
		if (rs.getInt("count") > 0)
			st.executeUpdate(
					mapper.save(obj).getUpdateSql(tablename, obj.getId()));
		else
			st.executeUpdate(
					mapper.save(obj).getInsertSql(tablename, obj.getId()));
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
		for (Map.Entry<String, String> e : this.fields.entrySet())
			update += ", " + e.getKey() + " " + e.getValue();
		st.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s (%s)",
				this.tablename, update));

	}

	public domain.Savable loadById(int id) throws SQLException {
		java.sql.Statement st = db.getConnection().createStatement();
		java.sql.ResultSet rs = st.executeQuery(String.format(
				"SELECT * FROM %s WHERE id = %d", this.tablename, id));
		return mapper.load(new DBmap(mapper, rs));
	}

	public Set<domain.Savable> loadAll() throws SQLException {
		Set<domain.Savable> set = new HashSet<domain.Savable>();
		java.sql.Statement st = db.getConnection().createStatement();
		java.sql.ResultSet rs = st.executeQuery(String.format(
				"SELECT * FROM %s", this.tablename));
		while (rs.next())
			set.add(mapper.load(new DBmap(mapper, rs)));
		return set;
	}

	public int getNextID() {
		return nextid++;
	}
}