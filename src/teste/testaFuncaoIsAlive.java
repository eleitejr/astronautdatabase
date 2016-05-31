package teste;

import modelo.Astronauta;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by erasmo on 31/05/16.
 */
public class testaFuncaoIsAlive {

        public static void imprimeListaAstronautasFalecidos(ArrayList<Astronauta> astronautas){
            System.out.println("Astronautas Falecidos até " + LocalDate.now());
            for (Astronauta astronauta : astronautas){
                if (!astronauta.isAlive()){
                    System.out.println(astronauta + " " + astronauta.getDtFalec());
                }
            }
        }


    }

