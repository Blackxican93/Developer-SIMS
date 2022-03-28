import java.util.*;

public class Player {

    private Location location;
    private List<Item> items;
    private String name;

    public Player() {
        this.location = new Location();
        this.items = new ArrayList<>();

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}