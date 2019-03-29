package com.lfs.GUI;

import java.util.Enumeration;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class manageGUI
{
  public manageGUI() {}
  
  public static void setUIFont(FontUIResource fui)
  {
    Enumeration<?> keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements())
    {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if ((value != null) && ((value instanceof FontUIResource)))
      {
        UIManager.put(key, fui);
      }
    }
  }
}
