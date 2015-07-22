package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ListaPaises {

	public static void main(String[] args) throws SQLException{

		Connection connection = AstronautaDB.getLocalConnection();
		Statement statement = connection.createStatement();
		@SuppressWarnings("unused")
		boolean resultado = statement.execute("select * from paises");
		ResultSet resultSet = statement.getResultSet();

		while (resultSet.next()) {
			String id = resultSet.getString("iso3");
			String nome = resultSet.getString("nome");
			System.out.println(id + " " + nome);


		}

		resultSet.close();
		statement.close();
		connection.close();	

	}
}
