package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AstronautaDB {
	public static Connection getLocalConnection() throws SQLException {
		String driver = "jdbc:mysql:";
		String host = "//localhost:3306/";
		String database = "AstronautDatabase";
		String user = "root";
		String pw = "";
		String url = driver + host + database; 
		Connection connection = DriverManager.getConnection(url, user, pw);
		return connection;
	}
	
	public static Connection getRemoteConnection() throws SQLException {
		String driver = "jdbc:mysql:";
		String host = "//xmysql.astronautdatabase.com:3306/";
		String database = "astronautdatabase";
		String user = "astronautdataba";
		String pw = "Juliana11@";
		String url = driver + host + database; 
		Connection connection = DriverManager.getConnection(url, user, pw);
		return connection;
	}
	
	public static boolean estaConectado() throws SQLException {
		boolean 
			conectouRemoto = false, 
			conectouLocal = false;
	
		conectouRemoto = tentaConexaoRemota().isValid(0);
						
			if (!conectouRemoto){
				conectouLocal = tentaConexaoLocal().isValid(0);
			}
			
		return conectouRemoto || conectouLocal;
	}
	
	public static Connection tentaConexaoRemota() throws SQLException {
		@SuppressWarnings("unused")
		boolean conectouRemoto = false;
		System.out.println("Estabelecendo conexão remota...");
		try (Connection connection = AstronautaDB.getRemoteConnection()) {
			conectouRemoto = true;
			System.out.println("Conexão remota aberta!");
			mostraCon(connection);	
			return connection;
		} 
	
	}

	public static Connection tentaConexaoLocal() throws SQLException {
		@SuppressWarnings("unused")
		boolean conectouLocal = false;
		System.out.println("Estabelecendo conexão local...");
		try (Connection connection = AstronautaDB.getLocalConnection()) {
			conectouLocal = true;
			System.out.println("Conexão local aberta!");
			mostraCon(connection);
			return connection;
		}

	}
	
	private static void mostraCon(Connection connection) throws SQLException {
		System.out.println("Conexão estabelecida.");
		System.out.println("URL: " + connection.getMetaData().getURL());
		System.out.println("Banco de Dados: " + connection.getMetaData().getDatabaseProductName());
		System.out.println();
	}
}
