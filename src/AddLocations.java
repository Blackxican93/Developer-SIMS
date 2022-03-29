import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class AddLocations {
    static String input = "";
    static String locationNameOption = "";
    static String locationDescriptionOption = "";
    static String locationItemsOption = "";
    static String locationDirectionsOption = "";


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("location.json"));
            JSONArray jsonArray = (JSONArray)obj;

            System.out.println(jsonArray);

            JSONObject addLocation = new JSONObject();
            Scanner sc = new Scanner(System.in);
            System.out.println("Would you like to add a location to the game? Type Y or N ");

            input = sc.nextLine();
            System.out.println("You have entered: " + input);

            System.out.println("You have the option of creating a location inside of the house. Simply type in your location name using one word. ");
            locationNameOption = sc.nextLine();
            System.out.println("You have entered: " + locationNameOption);
            addLocation.put("locationName", locationNameOption);
=======
            JSONObject newLocation = new JSONObject();
            Scanner sc = new Scanner(System.in);
            System.out.println("Would you like to add a location to the game? Type Y or N ");

            String inputY = sc.nextLine();
            System.out.println("You have entered: " + inputY);

            System.out.println("You have the option of adding in one of two locations. You can add a gym or man cave. Type 1 for the man cave or 2 for the gym. ");
            String locationNameOption = sc.nextLine();
            newLocation.put("locationName", "man cave");

            System.out.println("You have the option of adding in one of two location items. You can add a hookah or beer. Type 1 for the hookah or 2 for the beer. ");
            String locationItemOption = sc.nextLine();
            newLocation.put("locationItems", "beer");
>>>>>>> 984984dbe46819fa61bb5c03b939e25eeb6f0a05

            System.out.println("You have the option of creating a location direction inside of the house. Simply type in your location direction. ");
            locationDirectionsOption = sc.nextLine();
            System.out.println("You have entered: " + locationDirectionsOption);
            addLocation.put("locationDirections", locationDirectionsOption);

            System.out.println("You have the option of creating a location description. Simply type in your location description. ");
            locationDescriptionOption = sc.nextLine();
            System.out.println("You have entered: " + locationDescriptionOption);
            addLocation.put("locationDescription", locationDescriptionOption);

            System.out.println("You have the option of creating a location item. Simply type in your location item. ");
            locationItemsOption = sc.nextLine();
            System.out.println("You have entered: " + locationItemsOption);
            addLocation.put("locationItems", locationItemsOption);


            jsonArray.add(addLocation);
            System.out.println("Your options have been added to the list ");
            System.out.println(jsonArray);

            FileWriter file = new FileWriter("location.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

}