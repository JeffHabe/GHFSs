package com.lfs.service;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;










public class WindowHandler
  extends WindowAdapter
{
  public WindowHandler() {}
  
  public void windowActivated(WindowEvent e) {}
  
  public void windowClosed(WindowEvent e) {}
  
  public void windowClosing(WindowEvent e)
  {
    int result = JOptionPane.showConfirmDialog(null, "Are you sure to close the program", "Confirming", 
      0, 2);
    if (result == 0)
    {
      System.exit(0);
    }
  }
  
  public void windowDeactivated(WindowEvent e) {}
  
  public void windowDeiconified(WindowEvent e) {}
  
  public void windowIconified(WindowEvent e) {}
  
  public void windowOpened(WindowEvent e) {}
}
