package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.AstronautaDB;
import modelo.Astronauta;


public class TestaDAOAstronauta {

	public static void main(String[] args) throws SQLException {
		
		ArrayList<String> missoes = new ArrayList<String>();
		
		Date dataNasc = new Date((1968-1900) + (10-1) + (17));
		Date dataFalec = null;
		
		missoes.add("BRAZUCA-1");
		@SuppressWarnings("unused")
		Astronauta astronauta = new Astronauta(
				0, "Erasmo", 
				"de Castro", 
				"Leite Jr", 
				"BRA", 
				"DF", 
				"Brasilia", 
				"M", 
				null, 
				"Falso astronauta brasileiro", 
				"Brazilian Astronaut", 
				dataNasc, 
				dataFalec, 
				missoes,
				null
				);

		try (Connection con = AstronautaDB.getLocalConnection()) {
			AstronautaDAO dao = new AstronautaDAO(con);
			//dao.salva(astronauta);
		
			List<Astronauta> astronautas = dao.lista();
			
		
			for (Astronauta a : astronautas) {
				System.out.println("Existe o astronauta: " + a);
			}
		}

	}

}


