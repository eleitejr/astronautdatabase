package dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Astronauta;


class TestaDAOAstronauta {

	public static void main(String[] args) throws SQLException {

		ArrayList<String> missoes;
		missoes = new ArrayList<>();

		LocalDate dataNasc = LocalDate.of(1968, 10, 17);
		LocalDate dataFalec = LocalDate.of(3000,1,12);

		missoes.add("BRAZUCA-1");
		@SuppressWarnings("unused")
		Astronauta astronauta = new Astronauta(
				0,"Erasmo",
				"de Castro",
				"Leite Jr",
				"BRA",
				"DF",
				"Brasilia",
				"erasmo_leite.jpg",
				"M",
				"Falso astronauta brasileiro",
				"Brazilian Astronaut",
				dataNasc,
				dataFalec,
				missoes,
				0, null
		);

		try (Connection con = ConnectionFactory.getConnection()) {
			AstronautaDAO dao = new AstronautaDAO(con);
			//dao.salva(astronauta);

			List<Astronauta> astronautas = dao.lista();


			for (Astronauta a : astronautas) {
				System.out.println("Existe o astronauta: " + a);
			}
		}

	}

}

