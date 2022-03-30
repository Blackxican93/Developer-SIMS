import java.io.FileNotFoundException;
import java.io.FileReader;
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

        try (FileReader reader = new FileReader("location.json")) {
            Object obj = jsonparser.parse(reader);

            JSONArray locationsList = (JSONArray) obj;
            System.out.println(locationsList);

            locationsList.forEach(loc -> parseLocationsObject( (JSONObject) loc) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private static void parseLocationsObject(JSONObject locations)
    {
        JSONObject locationsObject= (JSONObject) locations.get("location");

        String readLocationName = (String) locationsObject.get("locationName");
        System.out.println("locationName: " + readLocationName);

        String readLocationDescription = (String) locationsObject.get("locationDescription");
        System.out.println("locationDescription: " + readLocationDescription);

        String readLocationItems = (String) locationsObject.get("locationItems");
        System.out.println("locationItems: " + readLocationItems);

        String readLocationDirections = (String) locationsObject.get("locationDirections");
        System.out.println("locationDirections: " + readLocationDirections);

    }
}
