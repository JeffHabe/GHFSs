package com.lfs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class URLConnection
{
  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
  private ArrayList<String> aDvalues;
  
  public URLConnection() {}
  
  public void readWeb(String strURL)
  {
    try {
      URL pageUrl = new URL(strURL);
      BufferedReader br = new BufferedReader(new java.io.InputStreamReader(pageUrl.openStream()));
      
      new outputService().outputFile(br, null);
      br.close();
      System.out.println("All done");
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public ArrayList<String> connection(String urlText, String port, double picByte)
  {
    aDvalues = null;
    HttpURLConnection conn = null;
    
    try
    {
      URL url = new URL(urlText);
      java.net.URLConnection urlConn = url.openConnection();
      

      conn = (HttpURLConnection)urlConn;
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(5000);
      conn.setReadTimeout(5000);
      conn.connect();
      
      BufferedReader br = new BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
      aDvalues = new GetData().ADdata(br, port, picByte);
      br.close();
      conn.disconnect();
<<<<<<< HEAD
    }
    catch (java.net.ProtocolException e)
    {
=======








    }
    catch (java.net.ProtocolException e)
    {







>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
      System.err.println(sdf.format(new Date()) + " " + port + " ProtocolException " + e);
    }
    catch (IOException e)
    {
      System.err.println(sdf.format(new Date()) + " " + port + " IOException " + e);
    }
    finally {
      if (conn != null)
      {
        conn.disconnect();
      }
    }
    return aDvalues;
  }
}
