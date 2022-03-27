import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class AddLocations
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("/Users/jr/Desktop/House of Madness/location.json"));
            JSONArray jsonArray = (JSONArray)obj;

            System.out.println(jsonArray);

            JSONObject newLocation = new JSONObject();
            newLocation.put("locationName", "manCave");
            newLocation.put("locationDescription", "a place to chill in peace");
            newLocation.put("locationItems", "hookah");


            jsonArray.add(newLocation);

            System.out.println(jsonArray);

            FileWriter file = new FileWriter("/Users/jr/Desktop/House of Madness/location.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
}