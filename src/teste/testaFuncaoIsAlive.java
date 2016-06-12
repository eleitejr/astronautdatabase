package teste;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Astronauta;

/**
 * Created by erasmo on 31/05/16.
 */
public class testaFuncaoIsAlive {

	public static void imprimeListaAstronautasFalecidos(ArrayList<Astronauta> astronautas){
		System.out.println("Astronautas Falecidos até " + LocalDate.now());
		for (Astronauta astronauta : astronautas){
			if (!astronauta.isAlive()){
				// se a funcao isAlive() retorna False, imprime no console o nome e a data de falecimento
				System.out.println(astronauta + " " + astronauta.getDtFalec());
			}
		}
	}


}

