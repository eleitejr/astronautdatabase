package modelo;

import java.util.Comparator;

class CidadeComparator implements Comparator<Astronauta> {
    public int compare(Astronauta astronauta, Astronauta outroAstronauta) {
        return astronauta.getCidade_Nasc().
                compareTo(outroAstronauta.getCidade_Nasc());
    }
}