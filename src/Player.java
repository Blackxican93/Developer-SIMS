public class Player extends ThingHolder implements java.io.Serializable {

    private Location location;

    public Player(String aName, String aDescription, ThingList tl, Location aLocation) {
        super(aName, aDescription, tl);
        this.location = aLocation;
    }

    public void setLocation(Location aRoom) {
        this.location = aRoom;
    }

    public Location getLocation() {
        return this.location;
    }
}
