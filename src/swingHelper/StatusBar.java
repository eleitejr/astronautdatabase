package swingHelper;

import javax.swing.JLabel;
import java.awt.Dimension;

/**
 * Created by erasmo.leite on 19/05/2016.
 */
public class StatusBar extends JLabel {

    /** Cria uma nova instância de StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
    }

    public void setMessage(String message) {
        setText(" "+message);
    }
}
