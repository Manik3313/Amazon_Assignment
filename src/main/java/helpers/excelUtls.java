package helpers;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class excelUtls {


    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public excelUtls(String excelpath, String sheetname) {
        try {
            workbook = new XSSFWorkbook(excelpath);
          //  System.out.println(workbook.getSheetName(0));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sheet = workbook.getSheet(sheetname);
    }


    public static int get_row_count() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static int get_column_count() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }


    public static String get_data_String(int row, int column) {
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    public static double get_data_numeric(int row, int column) {
        return sheet.getRow(row).getCell(column).getNumericCellValue();
    }

//////////////Updated on   
    public static Object[][] testData(excelUtls excel) throws IOException 
    {
    	 int row_count = excel.get_row_count();
         int column_count = excel.get_column_count();
         Object data[][] = new Object[row_count - 1][1];
        for (int i = 1; i < row_count; i++)
         {
             Map<String, String> hashmap = new HashMap<String, String>();
             for (int j = 0; j < column_count; j++) 
             { 
            	 String type=null;
            	 try
            	 {
            	  type=sheet.getRow(i).getCell(j).getCellType().name();
            	 }
            	 catch (NullPointerException e)
            	 {
            		 type="";
					// TODO: handle exception
				}
            	 String key = sheet.getRow(0).getCell(j).getStringCellValue();
            
             	if (type.equals("NUMERIC"))
             		hashmap.put(key, Double.toString(sheet.getRow(i).getCell(j).getNumericCellValue()));
             	else if (type.equals("STRING"))
 				 	hashmap.put(key, sheet.getRow(i).getCell(j).getStringCellValue());
 				else
 				   	hashmap.put(key, null );
 				
             }
             data[i - 1][0] = hashmap;
             //System.out.println("in hashmap");
         }

         
         return data;
     }
    
   ///old one
    /*public static Object[][] testData(excelUtls excel) throws IOException {
        int row_count = excel.get_row_count();
        int column_count = excel.get_column_count();
        Object data[][] = new Object[row_count - 1][1];
        for (int i = 1; i < row_count; i++) {

            Map<String, String> hashmap = new HashMap<String, String>();
            for (int j = 0; j < column_count; j++) 
            {
                String key = sheet.getRow(0).getCell(j).getStringCellValue();
                hashmap.put(key, sheet.getRow(i).getCell(j).getStringCellValue());
			}
            data[i - 1][0] = hashmap;
        }

        
        return data;
    }*/


}




