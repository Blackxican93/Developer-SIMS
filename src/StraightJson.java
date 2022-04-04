import java.util.List;


public class StraightJson {

    public static void showLocationItems(List<LocationItem> locationItems, Location currentRoom) {
        System.out.println("Items here in: " + currentRoom.getLocationName());
        for (int i = 0; i < locationItems.size(); i++) {
            LocationItem locationItem = locationItems.get(i);
            System.out.println("    " + (i + 1) + ". " + locationItem);
        }
        System.out.println();
    }

    public static Item getItem(List<Item> items, String name) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean hasLocationItem(List<LocationItem> locationItems, String name) {
        for (LocationItem locationItem : locationItems) {
            if (locationItem.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Location getLocation(List<Location> locations, String name) {
        for (Location location : locations) {
            if (location.getLocationName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    public boolean isReachable(List<LocationDirection> locationDirections, String locName) {
        for (LocationDirection locationDirection : locationDirections) {
            if (locationDirection.getValue().equalsIgnoreCase(locName)) {
                return true;
            }
        }
        return false;
    }

    public static void showLocationDirection(List<LocationDirection> locationDirections, Location currentLocation) {
        System.out.println("You can go below places from " + currentLocation.getLocationName() + ": ");
        for (int i = 0; i < locationDirections.size(); i++) {
            LocationDirection locationDirection = locationDirections.get(i);
            System.out.println("    " + (i + 1) + ". " + locationDirection);
        }
        System.out.println();
    }

}