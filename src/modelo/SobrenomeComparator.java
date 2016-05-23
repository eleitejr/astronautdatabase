package modelo;

import java.util.Comparator;

class SobrenomeComparator implements Comparator<Astronauta> {
    public int compare(Astronauta astronauta, Astronauta outroAstronauta) {
        return astronauta.getSobrenome().
                compareTo(outroAstronauta.getSobrenome());
    }
}
