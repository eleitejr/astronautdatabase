package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AstronautaDB {

	public static Connection getConnection() throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://xmysql.astronautdatabase.com:3306/astronautdatabase", "astronautdataba", "");
		return connection;
	}

}
