package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.DBCloseException;
import exceptions.DBConnectException;

public class PersistenceController 
{
	private static PersistenceController persistenceController;
	private DB db;

	public static PersistenceController getInstance() throws DBConnectException 
	{
		if (persistenceController == null)
			persistenceController = new PersistenceController();
		return persistenceController;
	}

	private PersistenceController() throws DBConnectException {
		db = new DB();
	}
	public void setSaveName(String name) throws DBCloseException, DBConnectException {
		db.close();
		db = new DB(name);
	}
	public DB getDB(){
		return db;
	}
	public boolean saveExists() {
		try{
			java.sql.Statement st = db.getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT COUNT(*) AS count FROM Farm WHERE id = 0");
			return (rs.getInt("count") > 0);
		} catch (SQLException e) {
			return false;
		}
	}
}
