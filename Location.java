import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Location
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("location.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray locationList = (JSONArray) obj;
            System.out.println(locationList);

            //Iterate over location array
            locationList.forEach( loc -> parseLocationObject( (JSONObject) loc ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseLocationObject(JSONObject location)
    {
        //Get location object within list
        JSONObject locationObject = (JSONObject) location.get("location");

        //Get location name
        String locationName = (String) locationObject.get("locationName");
        System.out.println(locationName);

        //Get location description
        String locationDescription = (String) locationObject.get("locationDescription");
        System.out.println(locationDescription);
    }
}