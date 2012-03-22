package persistence;

import java.sql.SQLException;
import java.util.Map;

/**
 * Abstract class defining the interface between the database and
 * {@link MappeList} items
 * 
 */
public interface Mapper {
	/**
	 * Turns the data passed in to the {@link domain.Savable} object this Mapper
	 * is made for. The complete row of the database is passed ass a
	 * <"columnname",value> Map.
	 * 
	 * @param data
	 *            a Map containing the database data of the object to load.
	 * @return the Savable object generated from the database data.
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 */
	public abstract domain.Savable load(Map<String, Object> data)
			throws SQLException;

	/**
	 * Converts a {@link domain.Savable} object into database ready data. All
	 * data needed should be passed in a {@code <"columnname",value>} Map.
	 * 
	 * @param obj
	 *            the object to convert
	 * @return a <"columnname", value> map containing all data needed to
	 *         reconstruct the object.
	 * @throws SQLException
	 */
	public abstract Map<String, Object> save(domain.Savable obj)
			throws SQLException;

	public abstract Map<String, String> getFields();
}