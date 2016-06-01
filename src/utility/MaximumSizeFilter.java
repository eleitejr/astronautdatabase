package utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;

final class MaximumSizeFilter extends DocumentFilter {

    private final int maxRows;
    private final int maxCols;

    public MaximumSizeFilter(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
    }

    public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, str, attr);
    }

    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attrs) throws BadLocationException {
        Document doc = fb.getDocument();
        fb.replace(offset, length, str, attrs);
        Element root = doc.getDefaultRootElement();
        for (int index = 0; index < root.getElementCount(); index++) {
            Element e = root.getElement(index);
            int lineLength = e.getEndOffset() - e.getStartOffset() - 1;
            if (lineLength > this.maxCols) {
                fb.remove(e.getStartOffset() + this.maxCols, lineLength - this.maxCols);
            }
        }
        if (root.getElementCount() > this.maxRows) {
            int offsetInvalidRow = root.getElement(this.maxRows).getStartOffset();
            fb.remove(offsetInvalidRow - 1, root.getEndOffset() - offsetInvalidRow);
        }
    }
}