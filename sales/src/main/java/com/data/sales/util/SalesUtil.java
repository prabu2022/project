package com.data.sales.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.data.sales.entity.Sales;


/**
 * @author Prabu Ponnusamy
 *
 */
public class SalesUtil {
	
	private static String EMPTY_STRING = "";
	
	public static List<Sales> mapDetails(MultipartFile file) throws Exception{
		String fileName = file.getOriginalFilename();
		List<Sales> salesList = new ArrayList<>();
		int lineNum = 0;
		boolean updateOrDelete = false;
        if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
            try (InputStream excelIs = file.getInputStream()) {
                Workbook wb = WorkbookFactory.create(excelIs); 
                // get the first sheet of the Workbook
                Sheet sheet = wb.getSheetAt(0); 

                Iterator<Row> itr = sheet.rowIterator();
                while (itr.hasNext()) {
                	if(lineNum == 0) {
                		lineNum++;
                		Row currentRow = itr.next();
                		if(currentRow.getPhysicalNumberOfCells() == 5) {
                			updateOrDelete = true;
                		}
                	} else {
                		Sales sales = new Sales();
                        Row currentRow = itr.next();
                        
                        if(!updateOrDelete) {
                        	sales.setOrigin(currentRow.getCell(0)!= null ? currentRow.getCell(0).toString() : EMPTY_STRING);
                            sales.setDestination(currentRow.getCell(1)!= null ? currentRow.getCell(1).toString() : EMPTY_STRING);
                            sales.setProduct(currentRow.getCell(2)!= null ? currentRow.getCell(2).toString() : EMPTY_STRING);
                            sales.setCapacity(currentRow.getCell(3)!= null ? currentRow.getCell(3).toString() : EMPTY_STRING);
                            salesList.add(sales);
                        } else {
                        	sales.setSalesId((int) Double.parseDouble(currentRow.getCell(0).toString()));
                        	sales.setOrigin(currentRow.getCell(1)!= null ? currentRow.getCell(1).toString() : EMPTY_STRING);
                            sales.setDestination(currentRow.getCell(2)!= null ? currentRow.getCell(2).toString() : EMPTY_STRING);
                            sales.setProduct(currentRow.getCell(3)!= null ? currentRow.getCell(3).toString() : EMPTY_STRING);
                            sales.setCapacity(currentRow.getCell(4)!= null ? currentRow.getCell(4).toString() : EMPTY_STRING);
                            salesList.add(sales);
                        }
                        
                	}
                }
            } catch(IOException e) {
                throw new Exception("Failed to process");
            }
        } else {
            throw new Exception("The file should be a .xlsx");
        }
        return salesList;
	}

}
