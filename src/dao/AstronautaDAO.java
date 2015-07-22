package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.AstronautaDB;
import modelo.Astronauta;
import modelo.Pais;


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

			stmt.setString(1, astronauta.getPrimeiro_Nome());
			stmt.setString(2, astronauta.getNome_do_Meio());
			stmt.setString(3, astronauta.getSobrenome());
			stmt.setString(4, astronauta.getPais_Nasc());
			stmt.setString(5, astronauta.getEstado_Nasc());
			stmt.setString(6, astronauta.getCidade_Nasc());
			stmt.setString(7, astronauta.getSexo());
			stmt.setDate(8, astronauta.getDtNasc());
			stmt.setDate(9, astronauta.getDtFalec());
			stmt.setString(10, astronauta.getFoto());
			stmt.setString(11, astronauta.getInfo());

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
					int id = rs.getInt(1);
					astronauta.setIdAstronauta(id);
				}
			}
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
		
		Date 	dataNasc, dataFalec;
		
		Blob	imagem = null;

		List<Astronauta> astronautas = new ArrayList<>();
		
		String sql = "SELECT * FROM astronauta";

		Connection con = AstronautaDB.getLocalConnection();

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
					dataNasc = rs.getDate("DtNasc");
					dataFalec = rs.getDate("DtFalec");
					foto = rs.getString("Foto");
					info = rs.getString("Info");
					info_eng = rs.getString("Info_eng");
														
					ArrayList<String> missoes = new ArrayList<String>();

					for (int m = 1; m <= 8; m++){
						String mission = rs.getString("m" + (m));
						missoes.add(mission);
						
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
								dataNasc, 	// Date
								dataFalec,  // Date
								missoes,	// ArrayList<String>
								imagem		// Blob
					);
					
					astronauta.setIdAstronauta(id);
					astronautas.add(astronauta);
				}
			}
		}
		return astronautas;
	}
	
	public ArrayList<Astronauta> pegaAstronautas(Connection connection)
			throws SQLException {
		ArrayList<Astronauta> astronautas = new ArrayList<>();
		Statement statement = connection.createStatement();
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
					Date dataNasc = resultSet.getDate("DtNasc");
					Date dataFalec = resultSet.getDate("DtFalec");
					String foto = resultSet.getString("Foto");
					String info = resultSet.getString("Info");
					String info_eng = resultSet.getString("Info_eng");
					ArrayList<String> missao = new ArrayList<String>();
					for (int i = 1; i<=8; i++) {
						missao.add(resultSet.getString("m" + i));
					}
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
							imagem);
		
					astronautas.add(a);
			}
				resultSet.close();
			} catch (Exception e) {
				System.out.println("Erro de acesso ao Banco de Dados");
				e.printStackTrace();
				}
			return astronautas;
	}
	
	public ArrayList<Pais> pegaPaises(Connection connection)
			throws SQLException {
		ArrayList<Pais> paises = new ArrayList<>();
		Statement statement = connection.createStatement();
		@SuppressWarnings("unused")
		boolean resultado = statement.execute
		("SELECT * FROM paises");
			try (ResultSet resultSet = statement.getResultSet()) {
				while (resultSet.next()){
					String id = resultSet.getString("iso3");
					String nome = resultSet.getString("nome");
					
					Pais pais = new Pais(id, nome);
		
					paises.add(pais);
			}
				resultSet.close();
			} catch (Exception e) {
				System.out.println("Erro de acesso ao Banco de Dados");
				e.printStackTrace();
				}
			return paises;
	}

	public void adiciona(Astronauta astronauta) {
		// TODO Auto-generated method stub
		
	}


}