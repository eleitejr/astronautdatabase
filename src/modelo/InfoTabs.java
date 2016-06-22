package modelo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erasmo.leite on 22/06/2016.
 */
public class InfoTabs extends JTabbedPane {
    private final		JPanel						panel1 = new JPanel(new BorderLayout());
    private final		JPanel						panel2 = new JPanel(new FlowLayout());
    private final		JPanel						panel3 = new JPanel(new SpringLayout());

    public InfoTabs(String aba1, String aba2, String aba3) {
        addTab(aba1, panel1);
        addTab(aba2, panel2);
        addTab(aba3, panel3);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public JPanel getPanel3() {
        return panel3;
    }
}
