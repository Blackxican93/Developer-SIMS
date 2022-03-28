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

        try (FileReader reader = new FileReader("/Users/jr/Desktop/House of Madness/location.json")) {
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

        String locationName = (String) locationsObject.get("locationName");
        System.out.println("locationName: " + locationName);

        String locationDescription = (String) locationsObject.get("locationDescription");
        System.out.println("locationDescription: " + locationDescription);

        String locationItems = (String) locationsObject.get("locationItems");
        System.out.println("locationItems: " + locationItems);

        String locationDirections = (String) locationsObject.get("locationDirections");
        System.out.println("locationDirections: " + locationDirections);

    }
}
