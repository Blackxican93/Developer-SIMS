import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Location {
    private String locationName;
    private String locationDescription;
    private List<LocationItem> locationItems;
    private List<LocationDirections> locationDirections;


    public Location(String locationName, String locationDescription, List<LocationItem> locationItems, List<LocationDirections> locationDirections) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationItems = locationItems;
        this.locationDirections = locationDirections;
    }

    public Location() {
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public List<LocationItem> getLocationItems() {
        return locationItems;
    }

    public void setLocationItems(List<LocationItem> locationItems) {
        this.locationItems = locationItems;
    }

    public List<LocationDirections> getLocationDirections() {
        return locationDirections;
    }

    public void setLocationDirections(List<LocationDirections> locationDirections) {
        this.locationDirections = locationDirections;
    }
}