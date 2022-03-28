package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {
    private List<Item> items;
    private List<Location> locations;
    private Player player;

    public Game(Location location, Player player) throws IOException, ParseException {
        this.locations = new ArrayList<>();
        this.player = player;
        this.items = new ArrayList<>();
        populateLocationsFromJson();
        populateItemsFromJson();
    }

    public Game() throws IOException, ParseException {
        this.locations = new ArrayList<>();
        this.items = new ArrayList<>();
        populateLocationsFromJson();
        populateItemsFromJson();
        this.player = new Player();
        this.playGame();
    }

    private void playGame() {
//        boolean isOver = false;
        System.out.println("Hello " + player.getName());
        Random random = new Random();
        int locationsSize = this.locations.size();
        Location location = locations.get(random.nextInt(locationsSize));
        Scanner sc = new Scanner(System.in);
//        while(!isOver) {}
        System.out.println("You are standing in " + location.getLocationName());
        List<LocationItem> locationItems = location.getLocationItems();
        System.out.println("You see these items with these corresponding actions: [" + locationItems.toString() + "]");

    }

    public void populateLocationsFromJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray a = (JSONArray) parser.parse(new FileReader("location.json"));

        for (Object o : a) {

            JSONObject locationObj = (JSONObject) o;
            Map<String, String> location = (Map<String, String>) locationObj.get("location");
            String locationName = location.get("locationName");
            String locationDescription = location.get("locationDescription");

            JSONArray locationItemsJSONArr = (JSONArray) ((Map<?, ?>) locationObj.get("location")).get("locationItems");
            List<LocationItem> locationItems = new ArrayList<>();
            for (int i = 0; i < locationItemsJSONArr.size(); i++) {
                String currentItemName = locationItemsJSONArr.get(i).toString();
                int startIndex = currentItemName.indexOf(":") + 2;
                int endIndex = currentItemName.length() - 2;
                String itemName = currentItemName.substring(startIndex, endIndex);
                locationItems.add(new LocationItem(itemName));
            }


            JSONObject locationDirectionsJsonObj = (JSONObject) ((Map<?, ?>) locationObj.get("location")).get("locationDirections");
            List<LocationDirections> locationDirections = new ArrayList<>();


            for (Iterator iterator = locationDirectionsJsonObj.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String val = (String) locationDirectionsJsonObj.get(key);
                locationDirections.add(new LocationDirections(key.trim(), val.trim()));
            }
            locations.add(new Location(locationName, locationDescription, locationItems, locationDirections));
        }
    }


    public void populateItemsFromJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray a = (JSONArray) parser.parse(new FileReader("item.json"));

        for (int i = 0; i < a.size(); i++) {
            JSONObject val = (JSONObject) a.get(i);
            for (Iterator iterator = val.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();

                String o = val.get(key).toString();
                int startIndex = o.indexOf(":");
                int endIndex = o.indexOf(",");
                String itemName = o.substring(startIndex + 2, endIndex - 1);

                o = o.substring(endIndex + 1);
                startIndex = o.indexOf(":");
                endIndex = o.indexOf(",");
                String itemDescription = o.substring(startIndex + 2, endIndex - 1);

                o = o.substring(endIndex + 1);
                startIndex = o.indexOf(":");
                endIndex = o.indexOf(",");
                String itemLocation = o.substring(startIndex + 2, endIndex - 1);

                o = o.substring(endIndex + 1);
                startIndex = o.indexOf(":");
                endIndex = o.length() - 2;
                o = o.substring(startIndex, endIndex);
                startIndex = 1;
                endIndex = o.length();
                String itemAttribute = o.substring(startIndex + 1, endIndex);

                items.add(new Item(itemName, itemDescription, itemAttribute, itemLocation));
            }
        }
    }


}