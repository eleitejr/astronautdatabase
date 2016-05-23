
package modelo;

import java.util.Comparator;

class IdAstronautaComparator implements Comparator<Astronauta> {
    public int compare(Astronauta astronauta, Astronauta outroAstronauta) {
        int comp = (astronauta.getIdAstronauta() > outroAstronauta.getIdAstronauta()) ? 1 : 0;
        if(comp == 0){
            comp = (astronauta.getIdAstronauta() == outroAstronauta.getIdAstronauta()) ? 0 : -1;
        }
        return comp;
    }
}
