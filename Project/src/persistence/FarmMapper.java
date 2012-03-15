package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import persistence.PersistenceController;

import domain.Farm;

public class FarmMapper 
{
	private final static String GET_FARM_SQL = "SELECT Cash FROM Farm";
	
	private final static String UPDATE_SQL = "UPDATE Farm SET Cash";
	
	private Farm farm;
	
	
	private PreparedStatement initSaveFarm(Connection connection) {
		try {
			PreparedStatement pStatement = connection
					.prepareStatement(UPDATE_SQL);
			return pStatement;
		}
		// detect problems interacting with the database
		catch (SQLException sqlException) {
			JOptionPane.showMessageDialog(null, sqlException.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		return null;
	}

	public void saveFarm(Farm Farm) {
		Connection connection = PersistenceController.getInstance().getConnect();
		PreparedStatement pStatement = initSaveFarm(connection);

		try {
			pStatement.setInt(1, farm.getCash());
			pStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public int getCash()
	{
		int cash = 0;
		// create Statement for querying database
		Statement statement;
		Connection connection = PersistenceController.getInstance().getConnect();
		try {
			statement = connection.createStatement();

			// query database
			ResultSet resultSet = statement.executeQuery("SELECT Cash FROM Farm");
			if (resultSet.next()) {
				cash = resultSet.getInt("Cash");
			}
			statement.close();
			return cash;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (Integer) null;
	}
}
