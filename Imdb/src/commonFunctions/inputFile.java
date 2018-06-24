package commonFunctions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import objectRepository.SQLVariables;


public class inputFile 
{
	
	public static void testData() throws SQLException, IOException, InvalidFormatException
	{
	    
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sh1= wb.createSheet("Movies");
		SQLVariables.sQuery="Select * from Top250Movies";
    	SQLVariables.stmt=SQLVariables.con.createStatement();
    	SQLVariables.resultSet = SQLVariables.stmt.executeQuery(SQLVariables.sQuery);
    	
  
    	int iRowIndex=0;
    	while (SQLVariables.resultSet.next()) 
    	{
    		
    		String movieName=SQLVariables.resultSet.getString(1);
    		String movieRating=SQLVariables.resultSet.getString(2);
    		String movieYear=SQLVariables.resultSet.getString(3);	
			
    		Row row = sh1.createRow(iRowIndex);
    		row.createCell(0).setCellValue(movieName);
    		row.createCell(1).setCellValue(movieRating);
    		row.createCell(2).setCellValue(movieYear);
    		iRowIndex++;
		}
    	
    	SQLVariables.resultSet.close();
    	SQLVariables.stmt.close(); 
    	

    	FileOutputStream fout=new FileOutputStream("C:\\Users\\Shashank S Shetty\\eclipse-workspace\\Imdb\\IMDB_Top250.xlsx");
    	wb.write(fout);
		fout.close();
		wb.close();
	
	
	}
	
}

