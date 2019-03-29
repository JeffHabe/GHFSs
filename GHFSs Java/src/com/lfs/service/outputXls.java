package com.lfs.service;

import com.jeff.util.Print;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
<<<<<<< HEAD

=======
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
<<<<<<< HEAD
import org.apache.poi.xssf.usermodel.XSSFWorkbook ;
=======
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
>>>>>>> 12462d21d3bdc6bc83b63b2eaeeac364a66be749



public class outputXls
{
  SimpleDateFormat dfYear = new SimpleDateFormat("yyyy");
  SimpleDateFormat dfDay = new SimpleDateFormat("MM-dd");
  SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
  String fileName = dfYear.format(new Date()).toString() + " LFS Sensor Data.xlsx";
  String filePath = "outputData/";
  File fileXls = new File(filePath + fileName);
  String webPort = null;
  
  public outputXls(String port)
  {
    File fPath = new File(port + " " + filePath);
    webPort = port;
    fPath.mkdirs();
  }
  
  public void createXls()
  {
    try
    {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet rsSheet = workbook.createSheet(dfDay.format(new Date()).toString());
      XSSFRow row = rsSheet.createRow(0);
      XSSFCell cell = row.createCell(1);
      cell.setCellValue(dfDay.format(new Date()) + " LFS Sensor Data");
      workbook.setActiveSheet(workbook.getSheetIndex(dfDay.format(new Date()).toString()));
      FileOutputStream out = new FileOutputStream(fileXls);
      workbook.write(out);
      workbook.close();
      out.close();
      System.err.println(dfYear.format(new Date()).toString() + " Port" + webPort + " : Xlsx Created ");
      Thread.sleep(1000L);
      System.gc();
    }
    catch (FileNotFoundException e)
    {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
    catch (IOException e)
    {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
    catch (InterruptedException e)
    {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
  }
  

  public boolean sheetIsToday()
  {
    boolean notIsToday = false;
    try
    {
      FileInputStream fis = new FileInputStream(fileXls);
      
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      

      String sheetName = workbook.getSheetName(workbook.getActiveSheetIndex());
      if (!sheetName.equals(dfDay.format(new Date()).toString()))
      {
        notIsToday = true;
      }
      workbook.close();
      fis.close();
      System.gc();

    }
    catch (FileNotFoundException e)
    {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
    catch (IOException e)
    {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
    return !notIsToday;
  }
  

  public void createTodaySheet()
  {
    try
    {
      FileInputStream fis = new FileInputStream(fileXls);
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      XSSFSheet rSheet = workbook.createSheet(dfDay.format(new Date()).toString());
      XSSFRow row = rSheet.createRow(0);
      Cell cell = row.createCell(1);
      cell.setCellValue(dfDay.format(new Date()) + " LFS Sensor Data");
      workbook.setActiveSheet(workbook.getSheetIndex(dfDay.format(new Date()).toString()));
      FileOutputStream out = new FileOutputStream(fileXls);
      workbook.write(out);
      workbook.close();
      fis.close();
      out.close();
      System.gc();
      Print.printErr(dfDay.format(new Date()).toString() + ": Writed Sheet successful");
    }
    catch (FileNotFoundException e)
    {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
    catch (IOException e) {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
  }
  
  public void insertData(Map<String, Double> ssrData)
  {
    try
    {
      FileInputStream fis = new FileInputStream(fileXls);
      OPCPackage oPackage = OPCPackage.open(fis);
      

      XSSFWorkbook workbook = new XSSFWorkbook(oPackage);
      XSSFSheet rsSheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
      int rowNum = rsSheet.getLastRowNum();
      XSSFRow row = rsSheet.createRow(++rowNum);
      
      int count = 0;
      XSSFCell cell = row.createCell(count++);
      
      XSSFCellStyle style = workbook.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      cell.setCellValue(dfTime.format(new Date()));
      for (String id : ssrData.keySet())
      {
        cell = row.createCell(count++);
        cell.setCellValue(id);
        cell.setCellStyle(style);
        cell = row.createCell(count++);
        cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(((Double)ssrData.get(id)).doubleValue());
        cell.setCellStyle(style);
      }
      


      FileOutputStream out = new FileOutputStream(fileXls);
      workbook.write(out);
      workbook.close();
      fis.close();
      out.close();
      oPackage.close();
      style = null;
      row = null;
      rsSheet = null;
      System.gc();
    }
    catch (InvalidFormatException e) {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
    catch (IOException e) {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    } catch (Exception e) {
      System.err.println(dfTime.format(new Date()) + " " + webPort + " " + e);
    }
  }
  

  public boolean isFindFiles()
  {
    fileName = (dfYear.format(new Date()).toString() + " LFS Sensor Data.xlsx");
    fileXls = new File(webPort + " " + filePath + fileName);
    return fileXls.exists();
  }
}
