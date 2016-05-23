package gui;

import java.util.Collections;
import java.util.Comparator;

import modelo.Astronauta;

enum OrdenarAstronautas implements Comparator<Astronauta> {

    PorDtNasc() {
        public int compare(Astronauta one, Astronauta other) {
            return one.getDtNasc().compareTo(other.getDtNasc());
        }
    },

    PorSobrenome() {
        public int compare(Astronauta one, Astronauta other) {
            return one.getSobrenome().compareTo(other.getSobrenome());
        }
    },

    PorCidade() {
        public int compare(Astronauta one, Astronauta other) {
            return one.getCidade_Nasc().compareTo(other.getCidade_Nasc());
        }
    },

    PorNumDeMissoes() {
        public int compare(Astronauta one, Astronauta other) {
            int comp = (one.getN_missoes() > other.getN_missoes()) ? 1 : 0;
            if(comp == 0){
                comp = (one.getN_missoes() == other.getN_missoes()) ? 0 : -1;
            }
            return comp;
        }
    },

    PorIdAstronauta() {
        public int compare(Astronauta one, Astronauta other) {
            int comp = (one.getIdAstronauta() > other.getIdAstronauta()) ? 1 : 0;
            if(comp == 0){
                comp = (one.getIdAstronauta() == other.getIdAstronauta()) ? 0 : -1;
            }
            return comp;
        }
    };


    public abstract int compare(Astronauta one, Astronauta other);

    public Comparator<Astronauta> asc() {
        return this;
    }

    public Comparator<Astronauta> desc() {
        return Collections.reverseOrder(this);
    }
}