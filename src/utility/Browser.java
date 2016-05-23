package utility;

/**
 * Created by erasmo.leite on 21/05/2016.
 */
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Browser extends JFrame {

    protected JEditorPane m_browser;

    protected MemComboBox m_locator = new MemComboBox();

    public Browser() {
        super("HTML Browser");
        setSize(500, 300);
        getContentPane().setLayout(new BorderLayout());

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(new JLabel("Address"));

        m_locator.load("addresses.dat");
        BrowserListener lst = new BrowserListener();
        m_locator.addActionListener(lst);

        MemComboAgent agent = new MemComboAgent(m_locator);

        p.add(m_locator);

        getContentPane().add(p, BorderLayout.NORTH);

        m_browser = new JEditorPane();
        m_browser.setEditable(false);
        m_browser.addHyperlinkListener(lst);

        JScrollPane sp = new JScrollPane();
        sp.getViewport().add(m_browser);
        getContentPane().add(sp, BorderLayout.CENTER);

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                m_locator.save("addresses.dat");
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);

        setVisible(true);
        m_locator.grabFocus();
    }

    class BrowserListener implements ActionListener, HyperlinkListener {
        public void actionPerformed(ActionEvent evt) {
            String sUrl = (String) m_locator.getSelectedItem();
            if (sUrl == null || sUrl.length() == 0 )
                return;

            BrowserLoader loader = new BrowserLoader(sUrl);
            loader.start();
        }

        public void hyperlinkUpdate(HyperlinkEvent e) {
            URL url = e.getURL();
            if (url == null )
                return;
            BrowserLoader loader = new BrowserLoader(url.toString());
            loader.start();
        }
    }

    class BrowserLoader extends Thread {
        protected String m_sUrl;

        public BrowserLoader(String sUrl) {
            m_sUrl = sUrl;
        }

        public void run() {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            try {
                URL source = new URL(m_sUrl);
                m_browser.setPage(source);
                m_locator.add(m_sUrl);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(Browser.this, "Error: "
                        + e.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public static void main(String argv[]) {
        new Browser();
    }

}
class MemComboAgent extends KeyAdapter {
    protected JComboBox m_comboBox;

    protected JTextField m_editor;

    public MemComboAgent(JComboBox comboBox) {
        m_comboBox = comboBox;
        m_editor = (JTextField) comboBox.getEditor().getEditorComponent();
        m_editor.addKeyListener(this);
    }

    public void keyReleased(KeyEvent e) {
        char ch = e.getKeyChar();
        if (ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch))
            return;
        int pos = m_editor.getCaretPosition();
        String str = m_editor.getText();
        if (str.length() == 0)
            return;

        for (int k = 0; k < m_comboBox.getItemCount(); k++) {
            String item = m_comboBox.getItemAt(k).toString();
            if (item.startsWith(str)) {
                m_editor.setText(item);
                m_editor.setCaretPosition(item.length());
                m_editor.moveCaretPosition(pos);
                break;
            }
        }
    }
}

class MemComboBox extends JComboBox {
    public static final int MAX_MEM_LEN = 30;

    public MemComboBox() {
        super();
        setEditable(true);
    }

    public void add(String item) {
        removeItem(item);
        insertItemAt(item, 0);
        setSelectedItem(item);
        if (getItemCount() > MAX_MEM_LEN)
            removeItemAt(getItemCount() - 1);
    }

    public void load(String fName) {
        try {
            if (getItemCount() > 0)
                removeAllItems();
            File f = new File(fName);
            if (!f.exists())
                return;
            FileInputStream fStream = new FileInputStream(f);
            ObjectInput stream = new ObjectInputStream(fStream);

            Object obj = stream.readObject();
            if (obj instanceof ComboBoxModel)
                setModel((ComboBoxModel) obj);

            stream.close();
            fStream.close();
        } catch (Exception e) {
            System.err.println("Serialization error: " + e.toString());
        }
    }

    public void save(String fName) {
        try {
            FileOutputStream fStream = new FileOutputStream(fName);
            ObjectOutput stream = new ObjectOutputStream(fStream);

            stream.writeObject(getModel());

            stream.flush();
            stream.close();
            fStream.close();
        } catch (Exception e) {
            System.err.println("Serialization error: " + e.toString());
        }
    }
}