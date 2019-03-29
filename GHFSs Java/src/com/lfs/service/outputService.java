package com.lfs.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class outputService
{
  SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm:ss");
  byte[] b;
  
  public outputService() {}
  
  public void outputFile(BufferedReader br, String port) {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("HttpURLLFS.txt", false));
      String inputLine;
      while ((inputLine = br.readLine()) != null) {
        bw.write(inputLine);
      }
      bw.flush();
      bw.close();
    }
    catch (FileNotFoundException e)
    {
      System.err.println(sdFormat.format(new Date()) + " " + e);
    }
    catch (IOException e)
    {
      System.err.println(sdFormat.format(new Date()) + " " + e);
    }
  }
  
  public StringBuffer outputString(BufferedReader br, String port)
  {
    StringBuffer str = null;
    try
    {
      str = new StringBuffer();
      
      String inputLine;
      while ((inputLine = br.readLine()) != null) {
        str = str.append(inputLine);
      }
      
    }
    catch (FileNotFoundException e)
    {
      System.err.println(sdFormat.format(new Date()) + " " + port + " FileNotFoundException " + e);

    }
    catch (IOException e)
    {
      System.err.println(sdFormat.format(new Date()) + " " + port + " IOException " + e);
    }
    
    return str;
  }
}
