import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ReadLocations
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonparser = new JSONParser();

        try (FileReader reader = new FileReader("/Users/jr/Desktop/House of Madness/location.json")) {
            Object obj = jsonparser.parse(reader);

            JSONArray readLocationsList = (JSONArray) obj;
            System.out.println(readLocationsList );

            readLocationsList .forEach(loc -> parseReadLocationsObject((JSONObject) loc));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private static void parseReadLocationsObject(JSONObject readLocations)
    {
        JSONObject readLocationsObject= (JSONObject) readLocations.get("location");

        String locationName = (String) readLocationsObject.get("locationName");
        System.out.println("locationName: " + locationName);

        String locationDescription = (String) readLocationsObject.get("locationDescription");
        System.out.println("locationDescription: " + locationDescription);

        //String locationItems = (String) modifyLocations.get("locationItems");
        //System.out.println("locationItems: " + locationItems);


    }


}