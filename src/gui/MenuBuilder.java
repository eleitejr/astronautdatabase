package gui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

final class MenuBuilder {

    static String imagePrefix = "";

    static JMenu newMenu(String nm, int ac, String itens[], ActionListener al){
        JMenu menu = new JMenu(nm);	menu.setMnemonic(ac);
        JMenuItem mi;
        for (int i = 0; i<itens.length; i+=3){
            if (itens[i]!=null) {
                if (itens[i+1]!=null) {
                    ImageIcon icon = new ImageIcon(imagePrefix + itens[i+1]);
                    mi = new JMenuItem(itens[i], icon);
                } else { mi = new JMenuItem(itens[i]); }
                if (itens[i+2]!=null) { mi.setMnemonic(itens[i+2].charAt(0)); }
                mi.addActionListener(al);
                menu.add(mi);
            } else { menu.addSeparator(); }
        }
        return menu;
    }

    static JMenu newRadioButtonMenu(String nome, int acc, String itens[],
                                    ItemListener il) {
        JMenu menu = new JMenu(nome); menu.setMnemonic(acc);
        ButtonGroup bgCB = new ButtonGroup();
        JRadioButtonMenuItem mi;
        for (int i=0; i<itens.length; i+=3) {
            if (itens[i]!=null) {
                if (itens[i+1]!=null) {
                    ImageIcon icon = new ImageIcon(imagePrefix + itens[i+1]);
                    mi = new JRadioButtonMenuItem(itens[i], icon, false);
                } else { mi = new JRadioButtonMenuItem(itens[i]); }
                if (itens[i+2]!=null) { mi.setMnemonic(itens[i+2].charAt(0)); }
                mi.addItemListener(il);
                bgCB.add(mi);
                menu.add(mi);
            } else { menu.addSeparator(); }
        }
        menu.getItem(0).setSelected(true);
        return menu;
    }
}
