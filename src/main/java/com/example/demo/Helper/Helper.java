package com.example.demo.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Mobile;


@Component
public class Helper {
	
	public  boolean checkContentType(MultipartFile file) {
		
		String contentType = file.getContentType();	
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Mobile> convertFileToDatabase(InputStream is){
		
		List<Mobile> mobiles=new ArrayList<>();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("Sheet0");
			int rowNumber=0;
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				
				if(rowNumber==0) {
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cellIterator = row.iterator();
				int cellNumber=0;
				Mobile mobile=new Mobile();
				
				while(cellIterator.hasNext()){
					
					Cell cell = cellIterator.next();
					 
					switch(cellNumber) {
					case 0: mobile.setMobileId((int)cell.getNumericCellValue());
					break;
					
					case 1: mobile.setHardDisk(cell.getStringCellValue());
					break;
					
					case 2: mobile.setMobileModel(cell.getStringCellValue());
					break;
					
					case 3: mobile.setPrice(cell.getStringCellValue());
					break;
					
					case 4: mobile.setRam(cell.getStringCellValue());
					break;
					
					default:
					break;
					
					}
					cellNumber++;
				}
				
				mobiles.add(mobile);
			}
		}catch (IOException e) {
			e.printStackTrace();
			
		}
		return mobiles;
	}

}
