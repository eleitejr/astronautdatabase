package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Astronauta;
import modelo.Pais;


public class AstronautaDAO {

	private final Connection con;

	public AstronautaDAO(Connection con) {
		this.con = con;
	}

	public void salva(Astronauta astronauta) throws SQLException {

		String sql = "INSERT INTO astronauta("
				+ "IdAstronauta, "
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
				+ "Info_eng, "
				+ "m1, m2, m3, m4, m5, m6, m7, m8,"
				+ "Imagem) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, astronauta.getIdAstronauta());
			stmt.setString(2, astronauta.getPrimeiro_Nome());
			stmt.setString(3, astronauta.getNome_do_Meio());
			stmt.setString(4, astronauta.getSobrenome());
			stmt.setString(5, astronauta.getPais_Nasc());
			stmt.setString(6, astronauta.getEstado_Nasc());
			stmt.setString(7, astronauta.getCidade_Nasc());
			stmt.setString(8, astronauta.getSexo());
			stmt.setDate(9, Date.valueOf(astronauta.getDtNasc()));
			stmt.setDate(10, Date.valueOf(astronauta.getDtFalec()));
			stmt.setString(11, astronauta.getFoto());
			stmt.setString(12, astronauta.getInfo());
			stmt.setString(13, astronauta.getInfo_eng());

			int numMissoes = astronauta.getMissao().size();
			for (int k = 0; k < numMissoes; k++){
				stmt.setString(14 + k, astronauta.getMissao().get(k));
			}
			for (int k = numMissoes; k < 8; k++){
				stmt.setString(14 + k, null);
			}

			stmt.setBlob(22, astronauta.getImagem());

			stmt.execute();

		}
	}

	public List<Astronauta> lista() throws SQLException, NumberFormatException {

		int id;

		String 	nome,
				nome_m,
				sobrenome,
				pais,
				cidade,
				estado,
				sexo,
				foto,
				info,
				info_eng;

		LocalDate 	dataNasc, dataFalec = LocalDate.of(1900,1, 1);

		int n_missoes = 0;

		Blob	imagem = null;

		List<Astronauta> astronautas = new ArrayList<>();

		String sql = "SELECT * FROM astronauta";

		Connection con = ConnectionFactory.getConnection();

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					id = rs.getInt("idAstronauta");
					nome = rs.getString("Primeiro_Nome");
					nome_m = rs.getString("Nome_do_Meio");
					sobrenome = rs.getString("Sobrenome");
					pais = rs.getString("Pais_Nasc");
					cidade = rs.getString("Cidade_Nasc");
					estado = rs.getString("Estado_Nasc");
					sexo = rs.getString("Sexo");
					dataNasc = rs.getDate("DtNasc").toLocalDate();
					dataFalec = rs.getDate("DtFalec").toLocalDate();
					foto = rs.getString("Foto");
					info = rs.getString("Info");
					info_eng = rs.getString("Info_eng");

					ArrayList<String> missoes = new ArrayList<>();

					for (int m = 1; m <= 8; m++){
						String mission = rs.getString("m" + (m));
						missoes.add(mission);

						n_missoes = rs.getInt("N_missoes");

						imagem = rs.getBlob("Imagem");
					}

					// falta inserir campos de foto e biografia

					Astronauta astronauta = new Astronauta(

							id, 		// int
							nome, 		// String
							nome_m, 	// String
							sobrenome, 	// String
							pais, 		// String
							estado, 	// String
							cidade,		// String
							foto,		// String
							sexo, 		// String
							info,		// String
							info_eng,	// String
							dataNasc, 	// sql.Date
							dataFalec,  // sql.Date
							missoes,	// ArrayList<String>
							n_missoes,  // int
							imagem		// sql.Blob
					);

					astronauta.setIdAstronauta(id);
					astronautas.add(astronauta);
				}
			}
		}
		return astronautas;
	}

	public ArrayList<Astronauta> pegaAstronautas()
			throws SQLException, NullPointerException {
		ArrayList<Astronauta> alAstros = new ArrayList<>();
		Statement statement = this.con.createStatement();
		@SuppressWarnings("unused")
		boolean resultado = statement.execute
				("SELECT * FROM astronauta ORDER BY idAstronauta");
		try (ResultSet resultSet = statement.getResultSet()) {
			while (resultSet.next()){
				int id = resultSet.getInt("idAstronauta");
				String nome = resultSet.getString("Primeiro_Nome");
				String nome_do_meio = resultSet.getString("Nome_do_Meio");
				String sobrenome = resultSet.getString("Sobrenome");
				String pais = resultSet.getString("Pais_Nasc");
				String estado = resultSet.getString("Estado_Nasc");
				String cidade = resultSet.getString("Cidade_Nasc");
				String sexo = resultSet.getString("Sexo");
				LocalDate dataNasc = resultSet.getDate("DtNasc").toLocalDate();
				LocalDate dataFalec;

				try {
					// tenta carregar data de falecimento
					dataFalec = resultSet.getDate("DtFalec").toLocalDate();
				}
				catch (NullPointerException ex) {
					// caso a data de falecimento seja nula,
					// carrega valor futuro que será usado para
					// teste na execução de AstronautaGUI
					dataFalec = LocalDate.of(2200, 1, 1);
				}

				String foto = resultSet.getString("Foto");
				String info = resultSet.getString("Info");
				String info_eng = resultSet.getString("Info_eng");
				ArrayList<String> missao = new ArrayList<>();
				for (int i = 1; i<=8; i++) {
					missao.add(resultSet.getString("m" + i));
				}
				int n_missoes = resultSet.getInt("N_missoes");
				Blob imagem = resultSet.getBlob("Imagem");

				Astronauta a = new Astronauta(
						id,
						nome,
						nome_do_meio,
						sobrenome,
						pais,
						estado,
						cidade,
						foto,
						sexo,
						info,
						info_eng,
						dataNasc,
						dataFalec,
						missao,
						n_missoes,
						imagem);

				alAstros.add(a);
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println("Erro de acesso ao Banco de Dados");
			e.printStackTrace();
		}
		return alAstros;
	}

	public ArrayList<Pais> pegaPaises()
			throws SQLException {
		ArrayList<Pais> alPaises = new ArrayList<>();
		Statement statement = this.con.createStatement();
		@SuppressWarnings("unused")
		boolean resultado = statement.execute
				("SELECT * FROM paises");
		try (ResultSet resultSet = statement.getResultSet()) {
			while (resultSet.next()){
				String id = resultSet.getString("iso3");
				String nome = resultSet.getString("nome");

				Pais pais = new Pais(id, nome);

				alPaises.add(pais);
			}
			resultSet.close();
		} catch (Exception e) {
			System.out.println("Erro de acesso ao Banco de Dados");
			e.printStackTrace();
		}
		return alPaises;
	}

	public void adiciona(Astronauta astronauta) {
		// TODO Auto-generated method stub

	}

	public boolean remove(Astronauta astronauta) throws SQLException{
		// TODO: implementar remocao de astronauta
		int ConfirmaExclusao = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do astronauta: " + astronauta + " ?", "Exclusão de Registro", JOptionPane.QUESTION_MESSAGE);
		if (ConfirmaExclusao == 1) try {
			Statement statement = this.con.createStatement();
			boolean resultado = statement.execute
					("REMOVE FROM astronauta WHERE IdAstronauta = " + astronauta.getIdAstronauta());
			if (resultado) {
				System.out.println("Registro excluido com sucesso.");
				statement.close();
				return true;}

			else {
				System.out.println("Erro de acesso ao banco de dados! Não foi possível excluir o registro.");
				return false;
			}

		} catch (Exception e) {
			System.out.println("Erro de acesso ao Banco de Dados");
			e.printStackTrace();
		}
		return false;
	}




	public static final Astronauta NENHUM
			= new Astronauta(0, "SEM REGISTRO", "", "", "", "", "",
			"anonymous.jpg", "M", "", "", LocalDate.of(1900, 1, 1),
			LocalDate.of(2100, 1, 1), new ArrayList<>(), 0, null);

}