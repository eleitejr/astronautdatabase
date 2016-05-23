package utility;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkListener;

import static javax.swing.JFrame.*;

class EditorPaneSample {
    public static void main(String args[]) {
        JFrame frame = new JFrame("EditorPane Example");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            JEditorPane editorPane = new JEditorPane("http://www.google.com");
            editorPane.setEditable(false);

            HyperlinkListener hyperlinkListener = new ActivatedHyperlinkListener(editorPane);
            editorPane.addHyperlinkListener(hyperlinkListener);
            JScrollPane scrollPane = new JScrollPane(editorPane);
            frame.add(scrollPane);
        } catch (IOException e) {
            System.err.println("Unable to load: " + e);
        }

        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}
