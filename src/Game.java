import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private List<Item> items;
    private List<Location> locations;

    private Player player;
    private List<Item> inventory;

    public Game() throws IOException, ParseException {
        this.locations = new ArrayList<>();
        this.items = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.player = new Player();

        JsonHelper jsonhelper = new JsonHelper();
        locations = jsonhelper.readLocationsFromJson();
        items = jsonhelper.populateItemsFromJson();
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        if (!shouldPlay(sc)) {
            return;
        }

        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        player.setName(name);

        Random random = new Random();
        int locationsSize = this.locations.size();
        Location currentLocation = locations.get(random.nextInt(locationsSize));

        while (true) {

            System.out.println("________________________");
            List<String> verbs = new ArrayList<>(Arrays.asList(
                    "take", "drop", "look", "run", "go"));
            List<String> nouns = new ArrayList<>(Arrays.asList("north", "south", "east", "west","sword", "laptop", "away"));
            System.out.println("Here are some examples of verbs you can use for the game: ");
            System.out.println(verbs);
            System.out.println("Here are some examples of nouns you can use for the game: ");
            System.out.println(nouns);
            System.out.println("IMPORTANT! Use a combination of a noun and a verb to interact in the game. For example (go north) ");
            System.out.println("_______________________________________________________________________________________");

            List<LocationItem> locationItems = currentLocation.getLocationItems();
            List<LocationDirection> locationDirections = currentLocation.getLocationDirections();

            System.out.println("--------------------------------------------");
            System.out.println("          " + player.getName() + ", you are in: " + currentLocation.getLocationName());
            System.out.println("--------------------------------------------");

            showMenuToPlayer(currentLocation);

            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                // view items
                showLocationItems(locationItems, currentLocation);
            } else if (option == 2) {
                // view locations reachable from here
                showLocationDirections(locationDirections, currentLocation);
            } else if (option == 3) {
                // Show item details
                System.out.println("Which item you want to see in detail?");
                String itemName = sc.nextLine();
                Item item = getItem(items, itemName);
                if (item == null) {
                    System.out.println(itemName + " is not here.");
                } else {
                    System.out.println(item);
                }
                System.out.println();
            } else if (option == 4) {
                //Show location details
                System.out.println("Which location you want to see in detail?");
                String locationName = sc.nextLine();
                Location location = getLocation(locations, locationName);
                if (location == null) {
                    System.out.println(locationName + " is not here.");
                } else {
                    System.out.println(locationName + " details:");
                    System.out.println("Description: " + location.getLocationDescription());
                    showLocationItems(location.getLocationItems(), location);
                    showLocationDirections(location.getLocationDirections(), location);
                }
                System.out.println();
            } else if (option == 5) {
                // pick item
                System.out.println("Which item you want to pick please?");
                String itemName = sc.nextLine();
                if (hasLocationItem(locationItems, itemName)) {
                    Item item = getItem(items, itemName);
                    if (inventory.contains(item)) {
                        System.out.println("You already picked it up.");
                    } else {
                        inventory.add(item);
                    }
                } else {
                    System.out.println(itemName + " not found here");
                }
            } else if (option == 6) {
                // Show inventory
                if (inventory.isEmpty()) {
                    System.out.println("Inventory is empty");
                } else {
                    for (Item item : inventory) {
                        System.out.println(item);
                    }
                }
                System.out.println();
            } else if (option == 7) {
                // Go to a new location
                System.out.println("Which location you want to go from here?");
                String locName = sc.nextLine();
                if (isReachable(locationDirections, locName)) {
                    currentLocation = getLocation(locations, locName);
                } else {
                    System.out.println("That location is not reachable from here");
                }
            } else if (option == 8) {
                System.out.println("Bye....");
                return;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private boolean shouldPlay(Scanner sc) {
        System.out.println(TextImages.getIntroArt());
        System.out.println("Are you ready to enter? [y/n]");

        while (true) {
            String choice = sc.nextLine();
            if (choice.equals("n")) {
                System.out.println("Exiting game...");
                return false;
            } else if (choice.equals("y")) {
                return true;
            } else {
                System.out.println("Invalid entry. [y/n]");
            }
        }
    }

    private Item getItem(List<Item> items, String name) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    private boolean hasLocationItem(List<LocationItem> locationItems, String name) {
        for (LocationItem locationItem : locationItems) {
            if (locationItem.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private Location getLocation(List<Location> locations, String name) {
        for (Location location : locations) {
            if (location.getLocationName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    private boolean isReachable(List<LocationDirection> locationDirections, String locName) {
        for (LocationDirection locationDirection : locationDirections) {
            if (locationDirection.getValue().equalsIgnoreCase(locName)) {
                return true;
            }
        }
        return false;
    }

    private void showLocationDirections(List<LocationDirection> locationDirections, Location currentLocation) {
        System.out.println("You can go below places from " + currentLocation.getLocationName() + ": ");
        for (int i = 0; i < locationDirections.size(); i++) {
            LocationDirection locationDirection = locationDirections.get(i);
            System.out.println("    " + (i + 1) + ". " + locationDirection);
        }
        System.out.println();
    }

    private void showLocationItems(List<LocationItem> locationItems, Location currentLocation) {
        System.out.println("Items here in: " + currentLocation.getLocationName());
        for (int i = 0; i < locationItems.size(); i++) {
            LocationItem locationItem = locationItems.get(i);
            System.out.println("    " + (i + 1) + ". " + locationItem);
        }
        System.out.println();
    }

    private void showMenuToPlayer(Location currentLocation) {
        System.out.println("Choose your action: ");
        System.out.println("    1. View items at " + currentLocation.getLocationName());
        System.out.println("    2. View neighbour locations for " + currentLocation.getLocationName());
        System.out.println("    3. Show item details");
        System.out.println("    4. Show location details");
        System.out.println("    5. Pick item");
        System.out.println("    6. Show inventory");
        System.out.println("    7. Go to a new location");
        System.out.println("    8. Exit");
    }
}
