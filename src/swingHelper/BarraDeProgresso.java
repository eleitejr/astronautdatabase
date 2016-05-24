package swingHelper;

/**
 * Created by erasmo.leite on 21/05/2016.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class BarraDeProgresso extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JProgressBar pbar;

    static int min = 0;

    static int max = 100;

    public BarraDeProgresso(String titulo) {
        pbar = new JProgressBar();
        pbar.setMinimum(min);
        pbar.setMaximum(max);
        add(pbar);

        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);

        for (int i = min; i <= max; i++) {
            final int percent = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        updateBar(percent);
                    }
                });
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void updateBar(int newValue) {
        pbar.setValue(newValue);
    }

    public static void main(String args[]) {
        new BarraDeProgresso("Acessando Banco de Dados...");
    }
}
