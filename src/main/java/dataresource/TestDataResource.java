/**
 *  This is the class is developed to read the data from Excel(Tetdata sheet) and read the config xml
 * @author elna.joseph
 */

package dataresource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.util.Strings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import listenerspackage.CustomListener;
public class TestDataResource extends CustomListener {
	
	public static HashMap<String, String> ENV_VARS=new HashMap<>();
	public static HashMap<String,ArrayList<String>> TEST_DATA;
	public static ArrayList<String> dispositionExcelData;
	
	public static void readTestdataExcel(String sheetName,String tcName)
	{
		TEST_DATA=new HashMap<>();
		int LABELROWNUM =0;
		System.out.println("Reading values from Test data sheet..");
		ArrayList<String>nameArray=new ArrayList<>();

		String testDataPath;
		try
		{
			
				testDataPath =  "testdata/Testdata.xlsx";
			
			FileInputStream inputStream = new FileInputStream(new File(testDataPath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			
			Sheet sheet = workbook.getSheet(sheetName);
			
			
			if(sheet==null)
			{
				System.err.println("Data could not be read, Invalid sheet name");
				System.out.println();
				workbook.close();
				inputStream.close();
				return;
			}
			
			Iterator<Row> rowIterator = sheet.iterator();
			int currentRowNum=-1;
			while(rowIterator.hasNext())
			{
				Row row=rowIterator.next();
				Cell first=row.getCell(0);
				String cellValue;
				if(first!=null)
				{
					cellValue=String.valueOf(first.getStringCellValue());
					if(cellValue!=null&& first.getStringCellValue().equals(tcName))
					{
						currentRowNum=row.getRowNum();
						break;
					}
				}
			}
			if(currentRowNum!=-1)
			{
				String val=null;
				Row currentRow=sheet.getRow(LABELROWNUM);
				for(int col=1;col<currentRow.getLastCellNum();col++)
				{
					Cell currentCell=currentRow.getCell(col);
					if(currentCell!=null)
					{
						switch (currentCell.getCellType()) {
						case STRING:
							val=currentCell.getStringCellValue();
							break;
						case BOOLEAN:
							val=String.valueOf(currentCell.getBooleanCellValue());
							break;
						case NUMERIC:
							val=String.valueOf(currentCell.getNumericCellValue());
							break;
							
						default:
							break;
						}
						nameArray.add(val);
						ArrayList<String> values=new ArrayList<String>();
						
						int rowno=currentRowNum;
						while(true)
						{
							String currentCellValue="";
							try
							{
								Cell valueCell=sheet.getRow(rowno).getCell(col);
								if(valueCell!=null)
								{
									
									switch (valueCell.getCellType()) {
									case STRING:
										currentCellValue=valueCell.getStringCellValue();
										break;
									case BOOLEAN:
										currentCellValue=String.valueOf(valueCell.getBooleanCellValue());
										break;
									case NUMERIC:
										currentCellValue=String.valueOf(valueCell.getNumericCellValue());
										break;
									default:
										break;
									}
									
									values.add(currentCellValue);
								}
								else if(rowno==currentRowNum) {
									values.add(currentCellValue);
								}
								else
								{
									TEST_DATA.put(val, values);
									break;
								}
							}
							catch(NullPointerException e)
							{
								TEST_DATA.put(val, values);
								break;
							}
							rowno++;
						}
					}
				}
			}
			else
			{
				System.err.println("Test Data could not be read, Test data not found for the test case: "+tcName);
				
			}



			workbook.close();
			inputStream.close();


		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


		System.out.println("---------------TEST DATA-----------------");
		for(int i=0;i<TEST_DATA.size();i++)
		{
			System.out.println();
			ArrayList<String> a=TEST_DATA.get(nameArray.get(i));
			System.out.println("Variable-"+nameArray.get(i));
			int j=0;
			System.out.println("values-");
			while(j<a.size())
			{
				System.out.println(a.get(j));
				j++;
			}


		}

		System.out.println("Number of test data variables read: "+TEST_DATA.size());


	}
}











