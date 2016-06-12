package utility;

import java.awt.Frame;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

class ActivatedHyperlinkListener implements HyperlinkListener{

	Frame frame;

	JEditorPane editorPane;

	ActivatedHyperlinkListener(Frame frame, JEditorPane editorPane) {
		this.frame = frame;
		this.editorPane = editorPane;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
		HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
		final URL url = hyperlinkEvent.getURL();
		if (type == HyperlinkEvent.EventType.ENTERED) {
			System.out.println("URL: " + url);
		} else if (type == HyperlinkEvent.EventType.ACTIVATED) {
			System.out.println("Activated");
			Runnable runner = new Runnable() {
				@Override
				public void run() {

					Document doc = editorPane.getDocument();
					try {
						editorPane.setPage(url);
					} catch (IOException ioException) {
						JOptionPane.showMessageDialog(frame,
								"Error following link", "Invalid link",
								JOptionPane.ERROR_MESSAGE);
						editorPane.setDocument(doc);
					}
				}
			};
			SwingUtilities.invokeLater(runner);
		}
	}


}
