package gui;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
    super("Custom AboutDialog Demo");    
    setLayout(new FlowLayout());
    
    JButton button = new JButton("Open Custom AboutDialog");
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        try
        {
          AboutDialog ad = new AboutDialog(SwingUtilities.getWindowAncestor((Component)evt.getSource()), false, null, false);
          ad.setTitle("My Custom About Dialog");
          ad.setBackgroundImage("/de/javasoft/synthetica/democenter/examples/aboutdialog/aboutDialogBackground.png", new Insets(0, 0, 1, 0));
          ad.setDescription("<html><b>About CustomAboutDialog</b><p>Click on the tabs below to get informations about the software and the system.</html>");
          ad.setAboutText(this.getClass().getResource("about.html"));
          ad.showDialog();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    });
    add(button);
    
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(320, 240);
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
      public void run()
      {
        try
        {
          UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");
          new CustomAboutDialog();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
}
