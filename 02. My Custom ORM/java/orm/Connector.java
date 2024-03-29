package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

	private static Connection connection;
	
	public static void createConnectionToSpecificDb(String username, String password, String dbName) 
			throws SQLException {
		
		Properties props = new Properties(); 
		props.setProperty("user", username);
		props.setProperty("password", password);
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, props);
	}
	
	
	public static void createConnectionToLocalHost(String username, String password) 
			throws SQLException {
		
		Properties props = new Properties(); 
		props.setProperty("user", username);
		props.setProperty("password", password);
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", props);
	}
	
	
	
	public static Connection getConnection(){
		return connection;
	}
}
