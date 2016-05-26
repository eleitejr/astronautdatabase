/**
 *
 */
package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * @author Erasmo
 *
 */
@SuppressWarnings("serial")
public class JListaDePaises extends JList<Pais>{
    private DefaultListModel<Pais> modelPais;

    public DefaultListModel<Pais> getModelPais() {
        return modelPais;
    }

    public void setModelPais(DefaultListModel<Pais> modelPais) {
        this.modelPais = modelPais;
    }

    public JListaDePaises(DefaultListModel<Pais> dataModel) {
        super();
        this.modelPais = dataModel;
    }

    public JListaDePaises(Collection<Pais> paises) {
        inicializa(paises);
    }

    public JListaDePaises(Pais[] listData) {
        super(listData);
        // TODO Auto-generated constructor stub
    }

    public JListaDePaises(Vector<? extends Pais> listData) {
        super(listData);
        // TODO Auto-generated constructor stub
    }

    private void inicializa(Collection<Pais> paises){
        modelPais 	= new DefaultListModel<>();
        for (Pais p : paises){
            modelPais.addElement(p);
        }

        setModel(modelPais);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }


    public String mostraNome(String paisISO, ArrayList<Pais> paises){
        for (Pais p : paises){
            if (p.getId().equalsIgnoreCase(paisISO)){
                return p.getNome();
            }
        }
        return null;
    }

    public String mostraISO(String pais, ArrayList<Pais> paises){
        for (Pais p : paises){
            if (p.getNome().equalsIgnoreCase(pais)){
                return p.getId();
            }
        }
        return null;
    }

    /**
     *
     */
    public JListaDePaises() {
        // TODO Auto-generated constructor stub
    }

}
