package persistence;

import java.sql.SQLException;
import exceptions.*;

public class DB {
	private java.sql.Connection connection;

	/**
	 * 
	 * @throws DBConnectException
	 * @throws SystemDBException 
	 */
	// constructor connects to database
	public DB() throws DBConnectException, SystemDBException {
		this("farmsave");
	}

	/**
	 * 
	 * @param name
	 * @throws DBConnectException
	 * @throws SystemDBException 
	 */
	public DB(String name) throws DBConnectException, SystemDBException {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = java.sql.DriverManager.getConnection(String.format(
					"jdbc:sqlite:%s.sav", name));
		} catch (java.sql.SQLException sqlException) {
			DBConnectException e = (DBConnectException) sqlException;
			e.setDBName(name);
			throw e;
		}
		// detect problems loading database driver'
		catch (ClassNotFoundException classNotFound) {
			throw new SystemDBException();
		}
	}

	public void close() throws DBCloseException {
		try {
			connection.close();
		} catch (java.sql.SQLException e) {
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
