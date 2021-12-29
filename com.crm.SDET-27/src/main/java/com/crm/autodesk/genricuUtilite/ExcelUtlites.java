package com.crm.autodesk.genricuUtilite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Shailesh
 *
 */

public class ExcelUtlites
{
	/**
	 * It iS Used To Get Data From The Excel Sheet
	 * @param sheetName by Using This We Call The Sheet From Which We have To get The Data
	 * @param rowNumber Specified The Row Number
	 * @param CellNumber Specified the Cell Number
	 * @return String Value From The File
	 * @throws Throwable Exception
	 */
	public String getDataFromExcelSheet(String sheetName,int rowNumber,int CellNumber) throws Throwable
	{
		FileInputStream fis= new FileInputStream("./Data/Organisation.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		String data = row.getCell(CellNumber).getStringCellValue();
		
		
		return data;
		
	}
	/**
	 * It is Used  To get The Last Row Count Of The Sheet 
	 * @param sheetName
	 * @return Integer Value OF the last Row Count
	 * @throws Throwable
	 */
	public int getRowCountOfSheet(String sheetName) throws Throwable 
	{
		FileInputStream fis= new FileInputStream("./Data/Organisation.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		wb.close();
		return sheet.getLastRowNum();
		
	}
	/**
	 * It Is Used To Set The Data In The Excel Sheet
	 * @param sheetName
	 * @param rowNumber
	 * @param cellnumber
	 * @param Data Which is To Be Entered
	 * @throws Throwable
	 */
	public void setDataInExcelSheet(String sheetName,int rowNumber,int cellnumber,String Data) throws Throwable
	{
		FileInputStream fis= new FileInputStream("./Data/Organisation.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(cellnumber);
		cell.setCellValue(Data);
		FileOutputStream fos= new FileOutputStream("./Data/organisation.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
