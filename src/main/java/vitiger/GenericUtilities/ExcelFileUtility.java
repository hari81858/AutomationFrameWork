package vitiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of Generic methods related to Excel file
 * @author hari
 *
 */
public class ExcelFileUtility {
	
	/**
	 * This method is use to read the data from the excel 
	 * @param Sheet
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	
	public String getDataFromExcelFile(String Sheet,int rowNo,int cellNo) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.excelFilePath);
	    //FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheet);
		Row rw=sh.getRow(rowNo);
		Cell ce=rw.getCell(cellNo);
		String value=ce.getStringCellValue();
		wb.close();
		System.out.println(value);
		return value;
		
	}

	/**
	 * This method is use to write the data into the excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws Throwable
	 */
	
	public void writeDataIntoExeclSheet(String sheetName,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.excelFilePath);
		//FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.createSheet();
		Row rw=sh.createRow(rowNum);
		Cell ce=rw.createCell(cellNum);
		ce.setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		wb.write(fos);
		wb.close();
			
	}
	
	/**
	 * This method will read the all data inside a sheet used used in the data provider
	 * @param Sheeat
	 * @return
	 * @throws Throwable
	 */
	public Object[][] readMultipleData(String Sheeat) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.excelFilePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheeat);
		
		int LastRow=sh.getLastRowNum();//captures no of rows
		
		int LastCell=sh.getRow(0).getLastCellNum();//captures the no of cells
		
		Object[][] data=new Object[LastRow][LastCell];
		for(int i=0;i<LastRow;i++)
		{
			for(int j=0;j<LastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
			
		}
		return data;
	
	}
}
