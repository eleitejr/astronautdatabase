package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListaAstronauta {

	public static void main(String[] args) throws SQLException {

		try	(Connection connection = AstronautaDB.getLocalConnection()) {

			String sql = "SELECT * FROM astronauta";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.execute();

				try (ResultSet resultSet = statement.getResultSet()) {

					while (resultSet.next()) {
						int id = resultSet.getInt("idAstronauta");
						String sobrenome = resultSet.getString("Sobrenome");
						String meio = resultSet.getString("Nome_do_Meio");
						String nome = resultSet.getString("Primeiro_Nome");
						String pais = resultSet.getString("Pais_Nasc");
						Date dataNasc = resultSet.getDate("DtNasc");
						//Date dataFalec = resultSet.getDate("DtFalec");
						String m1 = resultSet.getString("m1");
						String m2 = resultSet.getString("m2");
						String m3 = resultSet.getString("m3");
						String m4 = resultSet.getString("m4");
						String m5 = resultSet.getString("m5");
						String m6 = resultSet.getString("m6");
						String m7 = resultSet.getString("m7");
						String m8 = resultSet.getString("m8");
						System.out.println(id + " " 
								+ pais + " " 
								+ nome + " "
								+ meio + " "
								+ sobrenome + "\t\t\t"
								+ dataNasc + "\t"
								// + dataFalec + "\t"
								+ m1 + "\t" 
								+ m2 + "\t" 
								+ m3 + "\t" 
								+ m4 + "\t" 
								+ m5 + "\t" 
								+ m6 + "\t"
								+ m7 + "\t" 
								+ m8);
					}
				}
			}
		}
	}
}
