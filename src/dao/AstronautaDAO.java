package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.AstronautaDB;
import modelo.Astronauta;

public class AstronautaDAO {

	private final Connection con;

	public AstronautaDAO(Connection con) {
		this.con = con;
	}

	public void salva(Astronauta astronauta) throws SQLException {

		String sql = "INSERT INTO astronauta("
				+ "Primeiro_Nome, "
				+ "Nome_do_Meio, "
				+ "Sobrenome, "
				+ "Pais_Nasc, "
				+ "Estado_Nasc, "
				+ "Cidade_Nasc, "
				+ "Sexo, "
				+ "DtNasc, "
				+ "DtFalec, "
				+ "Foto, "
				+ "Info, "
				+ "m1, m2, m3, m4, m5, m6, m7, m8) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, astronauta.getNome());
			stmt.setString(2, astronauta.getM_nome());
			stmt.setString(3, astronauta.getSobrenome());
			stmt.setString(4, astronauta.getPais());
			stmt.setString(5, astronauta.getEstado());
			stmt.setString(6, astronauta.getCidade());
			stmt.setString(7, astronauta.getSexo());
			stmt.setString(8, astronauta.getData_Nasc().toString());
			stmt.setString(9, astronauta.getData_Falec().toString());
			stmt.setString(10, astronauta.getFoto());
			stmt.setString(11, astronauta.getBio());

			int numMissoes = astronauta.getMissao().size();
			for (int k = 0; k < numMissoes; k++){
				stmt.setString(12 + k, astronauta.getMissao().get(k));
			}
			for (int k = numMissoes; k < 8; k++){
				stmt.setString(12 + k, null);
			}

			stmt.execute();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()){
					int id = rs.getInt("idAstronauta");
					astronauta.setId(id);
				}
			}
		}
	}

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
					/*
					String dataNascTxt = rs.getString("DtNasc");
					String dataFalecTxt = rs.getString("DtFalec");

					int diaNasc = Integer.parseInt(dataNascTxt.substring(8, 9));
					int mesNasc = Integer.parseInt(dataNascTxt.substring(5, 6));
					int anoNasc = Integer.parseInt(dataNascTxt.substring(0, 3));

					LocalDate dataNasc = LocalDate.of(anoNasc, mesNasc, diaNasc);

					int diaFalec = Integer.parseInt(dataFalecTxt.substring(8, 9));
					int mesFalec = Integer.parseInt(dataFalecTxt.substring(5, 6));
					int anoFalec = Integer.parseInt(dataFalecTxt.substring(0, 3));
					LocalDate dataFalec = LocalDate.of(anoFalec, mesFalec, diaFalec);
					 */
					ArrayList<String> missoes = new ArrayList<String>();

					for (int m = 0; m < 8; m++){
						String mission = rs.getString("m" + (m + 1));
						missoes.add(mission);
					}

					// falta inserir campos de foto e biografia

					Astronauta astronauta = new Astronauta(nome, nome_m, sobrenome, pais, estado, cidade, sexo, null, null, null, null, missoes );
					astronauta.setId(id);
					astronautas.add(astronauta);
				}
			}
		}
		return astronautas;
	}

}
