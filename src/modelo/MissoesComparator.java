package modelo;

import java.util.Comparator;

class MissoesComparator implements Comparator<Astronauta> {
    public int compare(Astronauta astronauta, Astronauta outroAstronauta) {
        int comp = (astronauta.getN_missoes() > outroAstronauta.getN_missoes()) ? 1 : 0;
        if(comp == 0){
            comp = (astronauta.getN_missoes() == outroAstronauta.getN_missoes()) ? 0 : -1;
        }
        return comp;
    }
}
