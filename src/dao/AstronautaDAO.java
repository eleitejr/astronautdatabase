package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jdbc.AstronautaDB;
import modelo.Astronauta;
import modelo.Missao;

public class AstronautaDAO {

	public List<Astronauta> lista() throws SQLException {

		List<Astronauta> astronautas = new ArrayList<>();
		String sql = "select * from astronauta";

		Connection con = AstronautaDB.getConnection();

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("idAstronauta");
					String nome = rs.getString("Primeiro_Nome");
					String nome_m = rs.getString("Nome_do_Meio");
					String sobrenome = rs.getString("Sobrenome");
					String pais = rs.getString("Pais_Nasc");
					String cidade = rs.getString("Cidade_Nasc");
					String estado = rs.getString("Estado_Nasc");
					String sexo = rs.getString("Sexo");

					int diaNasc = rs.getInt("diaNasc");
					int mesNasc = rs.getInt("mesNasc");
					int anoNasc = rs.getInt("anoNasc");

					LocalDate dataNasc = LocalDate.of(anoNasc, mesNasc, diaNasc);

					int diaFalec = rs.getInt("diaFalec");
					int mesFalec = rs.getInt("mesFalec");
					int anoFalec = rs.getInt("anoFalec");

					LocalDate dataFalec = LocalDate.of(anoFalec, mesFalec, diaFalec);
					ArrayList<Missao> missoes = new ArrayList<>(8);

					//missoes.addAll(rs.getString("m1")); // implementar isto!!!

					// falta inserir campos de foto, biografia e missoes 

					Astronauta astronauta = new Astronauta(nome, nome_m, sobrenome, pais, estado, cidade, sexo, dataNasc, dataFalec, null, null, missoes );
					astronauta.setId(id);
					astronautas.add(astronauta);
				}
			}
		}
		return astronautas;
	}

}
