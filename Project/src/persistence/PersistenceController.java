package persistence;

import java.sql.Connection;
import java.util.List;

import persistence.FarmMapper;

import domain.Farm;

public class PersistenceController 
{
	private static PersistenceController persistenceController;
	private FarmMapper farmMapper;
	private Connect connection;

	public static PersistenceController getInstance() 
	{
		if (persistenceController == null)
			persistenceController = new PersistenceController();
		return persistenceController;
	}

	private PersistenceController() {
		connection = new Connect();
		farmMapper = new FarmMapper();
	}


	public void saveBoek(Farm farm) {
		farmMapper.saveFarm(farm);
	}


	public Connection getConnect() {
		return connection.getConnection();
	}

	public void closeConnection() {
		connection.closeConnect();
	}
}
