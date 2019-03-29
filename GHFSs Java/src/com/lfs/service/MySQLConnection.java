package com.lfs.service;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection
{
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/pfs";
  static final String USER = "test123";
  static final String PASS = "test123";
  
  public MySQLConnection() {}
  
  MySQLConnection mySQLConnection = new MySQLConnection();
  
  public void SQLConnect() {
    Connection connection = null;
    Statement statement = null;
    
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      
      System.out.println("Connecting to database...");
      connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/pfs", "test123", "test123");
      

      System.out.println("Creating statement...");
      statement = connection.createStatement();
      mySQLConnection.insertSQL(connection);
      statement.close();
      connection.close();
    }
    catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC Driver not found !!");
      return;
    }
    catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      return;
    }
  }
  

  public void insertSQL(Connection connection)
  {
    PreparedStatement pstmt = null;
    
    String insertTableSQL = "INSERT INTO farm(temp, humid) VALUES(?,?)";
    Float inputHumidity = Float.valueOf(12.5F);
    System.out.println(inputHumidity);
    Float inputTemp = Float.valueOf(12.5F);
    System.out.println(inputTemp);
    try
    {
      pstmt = connection.prepareStatement(insertTableSQL);
      pstmt.setFloat(1, inputHumidity.floatValue());
      pstmt.setFloat(2, inputTemp.floatValue());
      pstmt.executeUpdate();
      
      pstmt.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
