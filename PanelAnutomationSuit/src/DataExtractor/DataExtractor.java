package DataExtractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataExtractor {
	
	static String path;

	public static ArrayList extractor(String content_path) throws IOException {
		// TODO Auto-generated method stub
		ArrayList data1= new ArrayList();
		path=content_path;
		System.out.println("Inside dataextractor");
		FileInputStream file= new FileInputStream(content_path);
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator itr = sheet.iterator();
		while(itr.hasNext())
		{
			Row RowItr = (Row)itr.next();
			Iterator cellItr=RowItr.cellIterator();
			while(cellItr.hasNext())
			{
				Cell cells= (Cell)cellItr.next();
				switch(cells.getCellType())
				{
				case Cell.CELL_TYPE_STRING:data1.add(cells.getStringCellValue());
				break;
				case Cell.CELL_TYPE_NUMERIC:data1.add(cells.getNumericCellValue());
				break;
				case Cell.CELL_TYPE_BOOLEAN:data1.add(cells.getBooleanCellValue());
				break;
				case Cell.CELL_TYPE_BLANK:
				break;
				}
			}
		}
		System.out.println(data1);
		return data1;
		
	}

}
