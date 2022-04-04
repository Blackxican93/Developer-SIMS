import java.util.List;

public class Location implements java.io.Serializable{

    private int n, s, w, e;
    private String locationName;
    private String locationDescription;
    private List<LocationItem> locationItems;
    private List<LocationDirection> locationDirection;

    private String name;
    private String description;
    private ThingList things = new ThingList( );

    public Location(String locationName, String locationDescription, List<LocationItem> locationItems, List<LocationDirection> locationDirection) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationItems = locationItems;
        this.locationDirection = locationDirection;
    }

    public Location(String aName, String aDescription, int aN, int aS, int aW, int aE, ThingList tl) {
        this.name = aName;
        this.description = aDescription;
        things = tl;
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;

    }

    public String getLocationName() {
        return locationName;
    }
    public String getLocationDescription() {
        return locationDescription;
    }


    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public int getN() {
        return n;
    }

    public void setN(int aN) {
        this.n = aN;
    }

    // s
    public int getS() {
        return s;
    }

    public void setS(int aS) {
        this.s = aS;
    }

    // e
    public int getE() {
        return e;
    }

    public void setE(int aE) {
        this.e = aE;
    }

    // w
    public int getW() {
        return w;
    }

    void setW(int aW) {
        this.w = aW;
    }

    public List<LocationItem> getLocationItems() {
        return locationItems;
    }
    public List<LocationDirection> getLocationDirection() {
        return locationDirection;
    }

    public ThingList getThings() {
        return things;
    }

    public String describe() {
        return String.format("%s. %s.",
                getName(), getDescription())
                + "\nThings here:\n" + getThings().describeThings();
    }
}