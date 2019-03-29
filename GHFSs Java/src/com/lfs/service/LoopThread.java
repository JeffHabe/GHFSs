package com.lfs.service;

import com.jeff.util.Print;

public class LoopThread extends Thread { boolean btnSelected;
  public LoopThread() {}
  private Object lock = new Object();
  static boolean running = false;
  
  public void run() {
    synchronized (lock)
    {
      running = !running;
    }
    int i = 0;
    if (btnSelected)
    {
      for (;;)
      {
        i++;
        Print.println(Integer.valueOf(i));
        synchronized (lock)
        {
          if (!running) {
            break;
          }
        }
      }
    }
  }
}
