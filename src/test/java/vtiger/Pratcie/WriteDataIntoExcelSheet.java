package vtiger.Pratcie;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelSheet {

	public static void main(String[] args) throws Throwable {

		//Step 1:Load the file into java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 2:Create a workbook for file loaded
		Workbook wb=WorkbookFactory.create(fis);
		
		//Step 3:Create sheet
		Sheet sh=wb.createSheet("Trial");
		
		//Step 4:Create row
		Row rw=sh.createRow(4); 
		
		//Step 5:Create cell
		Cell ce=rw.createCell(3);
		
		//Step 6:Set the value into cell
		ce.setCellValue("Spider Man");
		
		//Step 7:open the file in java write format
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 8: Call the write method
		wb.write(fos);
		System.out.println("Data added");
		
		//Step 9:Close the workbook
		wb.close();
		System.out.println("Workbook closed");
		
	}

}
