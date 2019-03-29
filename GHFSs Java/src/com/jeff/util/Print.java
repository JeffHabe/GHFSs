package com.jeff.util;

import java.io.PrintStream;

public class Print { public Print() {}
  
  public static void println(Object obj) { System.out.println(obj); }
  
  public static void println()
  {
    System.out.println();
  }
  
  public static void print(Object obj) {
    System.out.print(obj);
  }
  
  public static void printErr(Object obj) { System.err.println(obj); }
}
