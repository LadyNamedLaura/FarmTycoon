package persistence;

import java.sql.SQLException;
import exceptions.*;

public class DB {
	private java.sql.Connection connection;

	/**
	 * 
	 * @throws DBConnectException
	 */
	// constructor connects to database
	public DB() throws DBConnectException {
		this("farmsave");
	}
	/**
	 * 
	 * @param name
	 * @throws DBConnectException
	 */
	public DB(String name) throws DBConnectException {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = java.sql.DriverManager.getConnection(
					String.format("jdbc:sqlite:%s.sav",name));
		} catch (java.sql.SQLException sqlException) {
			DBConnectException e =
					(DBConnectException) sqlException;
			e.setDBName(name);
			throw e;
		}
		// detect problems loading database driver
		catch (ClassNotFoundException classNotFound) {
			new SystemDBException();
		}
	}
	public void init() throws DBUpdateException {
		
		try {
			MapperList[] list = MapperList.values();
			for (MapperList info : list) {
				info.init();
			}
		} catch (SQLException e) {
			throw (DBUpdateException) e;
		}

	}

	public void close() throws DBCloseException {
		try {
			connection.close();
		}
		catch (java.sql.SQLException e) {
				throw (DBCloseException) e; 
		}
	}

	public java.sql.Connection getConnection() {
			return connection;
	}
	/**
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable {
		this.close();
		super.finalize();
	}

}
