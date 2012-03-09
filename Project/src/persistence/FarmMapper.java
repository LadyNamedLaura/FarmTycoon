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
	private final static String GET_FARM_SQL = "SELECT ? FROM farm where ? = ?";
	
	private final static String UPDATE_SQL = "UPDATE farm SET ? WHERE farmName = ?";
	
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
}
