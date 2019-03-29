package com.lfs.service;

import com.jeff.util.Print;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;



public class MSAccessConnection
{
  Connection conn;
  Statement smt;
  
  public MSAccessConnection()
    throws SQLException
  {
    try
    {
      Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    }
    catch (ClassNotFoundException e) {
      Print.println("連線失敗");
    }
  }
  


  public void querySQL()
    throws SQLException
  {
    Connection conn = DriverManager.getConnection("jdbc:ucanaccess://D:/KengWoNCHUFile/AccessDB/Temp.accdb");
    Statement smt = conn.createStatement();
    
    ResultSet rSet = smt.executeQuery("SELECT * from Orders");
    ResultSetMetaData rsmd = rSet.getMetaData();
    Print.println(rsmd.getColumnName(1) + " \t " + rsmd.getColumnName(2) + " \t " + rsmd.getColumnName(3) + 
      " \t ");
    boolean running = true;
    int i = 0;
    while (running)
    {
      if ((rSet.next()) && (i < 100))
      {
        running = true;
      }
      else {
        running = false;
      }
      Print.println(rSet.getString(1) + "\t" + rSet.getString(2) + "\t" + rSet.getString(3));
      
      i++;
    }
    
    conn.close();
  }
  
  public void insertSQL(String i) throws SQLException
  {
    conn = DriverManager.getConnection("jdbc:ucanaccess://D:/KengWoNCHUFile/AccessDB/LFS.accdb");
    smt = conn.createStatement();
    


    Calendar date = Calendar.getInstance();
    String pattern = "yyMMddHHmm";
    
    SimpleDateFormat sDFormat = new SimpleDateFormat(pattern);
    String fDate = sDFormat.format(date.getTime());
    
    try
    {
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
      Print.println(dateFormat.format(date.getTime()));
      Date castDate = dateFormat.parse(dateFormat.format(date.getTime()));
      long time = castDate.getTime();
      Print.println(new Timestamp(time));
      Timestamp tDate = new Timestamp(time);
      







      Print.println(fDate);
      String q = "INSERT INTO Temp (日期流水號 , 日期  , 溫度, 位置) VALUES (?, ?, ?, ?)";
      PreparedStatement st = conn.prepareStatement(q);
      st.setString(1, fDate + "-" + i);
      st.setTimestamp(2, tDate);
      int rndLong = new Random().nextInt(30);
      Print.println(Integer.valueOf(rndLong));
      st.setInt(3, rndLong);
      st.setString(4, "LFS-213");
      st.executeUpdate();
      



      conn.close();
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
  }
}
