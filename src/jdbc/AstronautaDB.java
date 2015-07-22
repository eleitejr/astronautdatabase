package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AstronautaDB {

	public static Connection getRemoteConnection() throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://xmysql.astronautdatabase.com:3306/astronautdatabase", "astronautdataba", "Juliana11@");
		return connection;
	}
	
	public static Connection getLocalConnection() throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/astronautdatabase", "root", "");
		return connection;
	}

}
