package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.AstronautaDB;
import modelo.Astronauta;

public class TestaDAOAstronauta {

	public static void main(String[] args) throws SQLException {
		ArrayList<String> missoes = new ArrayList<String>();
		missoes.add("BRAZUCA-1");
		Astronauta astronauta = new Astronauta("Erasmo", "de Castro", "Leite Jr", "BRA", "DF", "Brasilia", "M", "1968-10-17", null, null, "Falso astronauta brasileiro", missoes);

		try (Connection con = AstronautaDB.getConnection()) {
			AstronautaDAO dao = new AstronautaDAO(con);
			dao.salva(astronauta);

			List<Astronauta> astronautas = dao.lista();
			for (Astronauta a : astronautas) {
				System.out.println("Existe o astronauta: " + a);
			}
		}

	}

}


