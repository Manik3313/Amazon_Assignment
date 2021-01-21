package sample;

import org.testng.annotations.Test;

import POJO.Location;
import POJO.object;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.taskdefs.Length;
import static io.restassured.RestAssured.*;

public class PracticeExcel
{
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public static int get_row_count() 
    {
        return sheet.getPhysicalNumberOfRows();
    }

    public static int get_column_count()
    {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }


    public static String get_data_String(int row, int column) 
    {
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    public static double get_data_numeric(int row, int column)
    {
        return sheet.getRow(row).getCell(column).getNumericCellValue();
    }

    public static Object[][] testData() throws IOException 
    {
         int row_count = get_row_count();
         int column_count = get_column_count();
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

  public static void main(String[] args) throws IOException
  {

	  try {
            workbook = new XSSFWorkbook("C:\\Users\\91998\\Documents\\POC_TestProject\\src\\main\\resources\\DataApi.xlsx");
          //  System.out.println(workbook.getSheetName(0));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sheet = workbook.getSheet("Sheet1");
        Object[][] data=testData();
        Map<String, String> mapdata=(Map<String, String>) data[1][0];
        ArrayList<String> types=new ArrayList<String>();
        Location location=new Location();
	     object obj=new object(); 

			/*
			 * types.add("shoe park"); types.add("shop"); obj.setAccuracy(50);
			 * obj.setAddress("29, side layout, cohen 09"); obj.setLanguage("French-IN");
			 * obj.setName("Frontline house"); obj.setPhone_number("(+91) 983 893 3937");
			 * obj.setWebsite("http://google.com"); obj.setLocation(location);
			 * obj.setTypes(types);
			 */  
        if (mapdata.get("type").contains(":"))
        {
        	Scanner scan = new Scanner(mapdata.get("type"));
            //Initialize the string delimiter
            scan.useDelimiter(":");
            //Printing the tokenized Strings
            while (scan.hasNext()) {
                types.add(scan.next());
                
       }
           
        }
        if (mapdata.get("Request Type").equalsIgnoreCase("Post_Location"))
        {
        	float f=Float.parseFloat(mapdata.get("Accuracy"));
        	int accuracy=(int)f;
        	//System.out.println(accuracy);
        	location.setLat(Double.parseDouble(mapdata.get("Lat")));
        	location.setLng(Double.parseDouble(mapdata.get("Lng")));
        	obj.setAccuracy(accuracy);
        	obj.setAddress(mapdata.get("Address"));
        	obj.setLanguage(mapdata.get("Language"));
        	obj.setName(mapdata.get("Name"));
        	obj.setPhone_number(mapdata.get("Phone_Number"));
        	obj.setWebsite(mapdata.get("Website"));
        	obj.setLocation(location);
        	obj.setTypes(types);
        	
        	String response=given()
        	.baseUri("https://rahulshettyacademy.com")
        	.queryParam("key" , "qaclick123") 
        	.when()
        	.body(obj)
        	.post("/maps/api/place/add/json")
        	.then()
        	//.log()
        	//.all()
        	.extract()
        	.response()
        	.asString();
        	JsonPath jsonPath=new JsonPath(response);
        	System.out.println(jsonPath.getString("place_id"));
        }
			/*
			 * System.out.println(mapdata.get("Request Type"));
			 * System.out.println(mapdata.get("Accuracy"));
			 * System.out.println(mapdata.get("Address"));
			 * System.out.println(mapdata.get("Lat"));
			 * System.out.println(mapdata.get("Lng"));
			 * System.out.println(mapdata.get("Name"));
			 * System.out.println(mapdata.get("Phone_Number"));
			 * System.out.println(mapdata.get("Website"));
			 * System.out.println(mapdata.get("type"));
			 * System.out.println(mapdata.get("Language"));
			 */ 
    }





}




