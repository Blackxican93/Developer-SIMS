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
        boolean isOver = false;
        System.out.println("Hello " + player.getName());
        Random random = new Random();
        int locationsSize = this.locations.size();
        Location location = locations.get(random.nextInt(locationsSize));
        Scanner sc = new Scanner(System.in);
        String choice;
        String name;

        Scanner myScanner = new Scanner(System.in);
        String introArt = """
                               )
                             ( _   _._              WELCOME TO THE HOUSE OF MADNESS!
                              |_|-'_~_`-._
                           _.-'-_~_-~_-~-_`-._                 Authors:
                       _.-'_~-_~-_-~-_~_~-_~-_`-._          Justin Peebles
                      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         Vernon Stephens
                        |  []       []       [] |            Maria Nieves
                        |           __    ___   |
                      ._|  []      | .|  [___]  |_._._._._._._._._._._._._._._._._.
                      |=|________()|__|()_______|=|=|=|=|=|=|=|=|=|=|=|=|=|=|=|=|=|
                    ^^^^^^^^^^^^^^^ === ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 You are an aspiring Software Developer. You just landed an interview
                 with Amazon. Despite any unexpected encounters you may face, you MUST
                 complete the given assessment in time.
                 
                ======================================================================
                """;

        while(!isOver) {
            System.out.println(introArt);
            System.out.println("Enter your name: ");
            name = sc.nextLine();
            System.out.println("Hello " + name + "! Welcome to the House of Madness.\n" +
                    "Are you ready to enter? [y/n]");
            choice = sc.nextLine();
            if (choice.equals("y")) {
                System.out.println("Insert story intro here.");

                System.out.println("You are standing in " + location.getLocationName());
                List<LocationItem> locationItems = location.getLocationItems();
                System.out.println("You see these items with these corresponding actions: [" + locationItems.toString() + "]");
                System.out.println("You have these movement options : [" + location.getLocationDirections().toString() + "]");
                System.out.println("Will you use an item, or change location? [Item/item | Location/location]");
                String typeOfAction = sc.next();
                if (typeOfAction.toLowerCase().equals("Item".toLowerCase())) {
                    System.out.println("Please pick one of the available items to use for the corresponding action: [" + locationItems.toString() + "]");
                    String itemPicked = sc.next();
                    for (int i = 0; i < locationItems.size(); i++) {
                        if (locationItems.get(i).getName().equals(itemPicked)) {
                            for (int j = 0; j < items.size(); j++) {
                                if (items.get(j).getItemName().toLowerCase().equals(itemPicked.toLowerCase())) {
                                    System.out.println("Action: " + items.get(j).getItemAttribute());
                                    break;
                                }
                            }
                        }
                    }
                } else if (typeOfAction.toLowerCase().equals("Location".toLowerCase())) {
                    List<LocationDirections> locationDirections = location.getLocationDirections();
                    System.out.println("Please pick one of the available location to go to: [" + locationDirections.toString() + "]");
                    String locationPicked = sc.next();
                    boolean locationFound = false;
                    for (int i = 0; i < locationDirections.size(); i++) {
                        if (locationPicked.toLowerCase().equals(locationDirections.get(i).getValue().toLowerCase()) ||
                                locationPicked.toLowerCase().equals(locationDirections.get(i).getKey().toLowerCase())) {
                            for (int k = 0; k < locations.size(); k++) {
                                if (locations.get(k).getLocationName().toLowerCase().equals(locationPicked.toLowerCase())) {
                                    location = locations.get(k);
                                    locationFound = true;
                                    break;
                                }
                                if (locationFound == true) {
                                    break;
                                }
                            }
                        }
                        if (locationFound == true) {
                            break;
                        }
                    }
                    if (!locationFound) {
                        System.out.println("I dont know where that is");
                    }
                } else if (typeOfAction.toLowerCase().equals("Exit".toLowerCase())) {
                    System.out.println("Bye");
                    return;
                } else {
                    System.out.println("I cant understand you");
                }
            } else if (choice.equals("n")) {
            System.out.println("\nExiting game... ");
            System.exit(0);
        } else {
            System.out.println("Invalid entry. [y/n]");
        }
        }
    }

    public void populateLocationsFromJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray a = (JSONArray) parser.parse(new FileReader("location.json"));

        for (Object o : a)
        {

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


            for (Iterator iterator = locationDirectionsJsonObj.keySet().iterator(); iterator.hasNext();) {
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
