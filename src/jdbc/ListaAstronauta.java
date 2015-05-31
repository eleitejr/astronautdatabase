package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaAstronauta {

	public static void main(String[] args) throws SQLException {

		try	(Connection connection = AstronautaDB.getConnection()) {

			String sql = "select * from astronauta";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.execute();

				try (ResultSet resultSet = statement.getResultSet()) {

					while (resultSet.next()) {
						int id = resultSet.getInt("idAstronauta");
						String sobrenome = resultSet.getString("Sobrenome");
						String nome = resultSet.getString("Primeiro_Nome");
						String pais = resultSet.getString("Pais_Nasc");
						System.out.println(id + " " + pais + " " + nome + " " + sobrenome);
					}

					resultSet.close();
				}

				statement.close();
			}

			connection.close();
		}

	}
}
