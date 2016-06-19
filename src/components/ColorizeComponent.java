package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


/**
 * A demo how to colorize JButtons.  
 */
@SuppressWarnings("serial")
public class ColorizeComponent extends JFrame
{

	public ColorizeComponent()
	{
		super("ColorizeComponent");

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(5,5,5,5);
		gbc.fill = GridBagConstraints.HORIZONTAL;


		for (float i = 0.0f; i < 0.5f; i += 0.05f)
		{  
			gbc.gridx = 0;
			JLabel label = new JLabel("Alpha: " + String.format("%.2f", i));
			panel.add(label, gbc);

			gbc.gridx = 1;
			JButton button = new JButton("Red Button");
			button.putClientProperty("Synthetica.background", Color.RED);
			button.putClientProperty("Synthetica.background.alpha", i);
			panel.add(button, gbc);

			gbc.gridx = 2;
			JButton button2 = new JButton("Green Button");
			button2.putClientProperty("Synthetica.background", new Color(0x00CC00));
			button2.putClientProperty("Synthetica.background.alpha", i);
			panel.add(button2, gbc);

			gbc.gridx = 3;
			JButton button3 = new JButton("Blue Button");
			button3.putClientProperty("Synthetica.background", new Color(0x0099FF));
			button3.putClientProperty("Synthetica.background.alpha", i);
			panel.add(button3, gbc);

			gbc.gridx = 4;
			//JButton button4 = new JButton("Orange Button");
			JButton button4 = new JButton("Orange Button");
			button4.putClientProperty("Synthetica.background", new Color(0xFF9900));
			button4.putClientProperty("Synthetica.background.alpha", i);
			panel.add(button4, gbc);

			gbc.gridy += 1;
		}

		add(panel, BorderLayout.CENTER);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);        //center
		setVisible(true);
	}

	/**
	 * Static main method for application startup. 
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");
					new ColorizeComponent();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}
