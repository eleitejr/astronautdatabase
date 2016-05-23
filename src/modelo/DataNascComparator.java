package modelo;

import java.util.Comparator;

class DataNascComparator implements Comparator<Astronauta> {
    public int compare(Astronauta astronauta, Astronauta outroAstronauta) {
        return astronauta.getDtNasc().
                compareTo(outroAstronauta.getDtNasc());
    }
}