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
						String m1 = resultSet.getString("m1");
						String m2 = resultSet.getString("m2");
						String m3 = resultSet.getString("m3");
						String m4 = resultSet.getString("m4");
						String m5 = resultSet.getString("m5");
						String m6 = resultSet.getString("m6");
						String m7 = resultSet.getString("m7");
						String m8 = resultSet.getString("m8");
						System.out.println(id + " " 
								+ pais + " " + nome + " " + sobrenome + "\t\t\t" 
								+ m1 + "\t" + m2 + "\t" + m3 + "\t" + m4 + "\t" + m5 + "\t" + m6 + "\t"+ m7 + "\t" + m8);
					}

					resultSet.close();
				}

				statement.close();
			}

			connection.close();
		}

	}
}
