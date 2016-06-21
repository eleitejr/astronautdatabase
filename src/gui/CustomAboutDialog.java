package gui;


import java.io.IOException;

import javax.swing.JFrame;

import de.javasoft.swing.AboutDialog;

/**
 * A simple AboutDialog application used to demonstrate and explain  
 * basic functionality.
 */
@SuppressWarnings("serial")
public class CustomAboutDialog extends JFrame
{  
	/**
	 * Constructor for the main application.  
	 */
	public CustomAboutDialog()
	{


		try
		{
			AboutDialog ad = new AboutDialog(this.getOwner(), false, null, false);
			ad.setTitle("Viajantes Espaciais");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ad.setSize(320, 240);
			setLocationRelativeTo(null);        //center
			ad.setVisible(true);
			//ad.setBackgroundImage("/imagens/vetor/Astronaut-50.png", new Insets(0, 0, 1, 0));
			ad.setDescription("<html><b>Sobre Viajantes Espaciais</b><p>Clique nas abas abaixo para obter informações sobre o software e o sistema.</html>");
			ad.setAboutText(this.getClass().getResource("about.html"));
			ad.showDialog();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	};

}

/**
 * Static main method for application startup. 
 */

/*
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel");
					new CustomAboutDialog();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}*/

