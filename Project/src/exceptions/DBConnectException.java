package exceptions;

import java.sql.SQLException;

public class DBConnectException extends SQLException {
	private String dbname;
	public DBConnectException(String name){
		super();
		this.dbname = name;
	}
	public String getDBName() {
		return this.dbname;
	}
	public void setDBName(String name) {
		this.dbname=name;
	}
}
