import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Location2 {
    private String locationName2;
    private String locationDescription2;
    private List<LocationItem2> locationItems2;
    private List<LocationDirections2> locationDirections2;


    public Location2(String locationName2, String locationDescription2, List<LocationItem2> locationItems2, List<LocationDirections2> locationDirections2) {
        this.locationName2 = locationName2;
        this.locationDescription2 = locationDescription2;
        this.locationItems2 = locationItems2;
        this.locationDirections2 = locationDirections2;
    }

    public Location2() {
    }


    public String getLocationName2() {
        return locationName2;
    }

    public void setLocationName2(String locationName2) {
        this.locationName2 = locationName2;
    }

    public String getLocationDescription2() {
        return locationDescription2;
    }

    public void setLocationDescription2(String locationDescription2) {
        this.locationDescription2 = locationDescription2;
    }

    public List<LocationItem2> getLocationItems2() {
        return locationItems2;
    }

    public void setLocationItems2(List<LocationItem2> locationItems2) {
        this.locationItems2 = locationItems2;
    }

    public List<LocationDirections2> getLocationDirections2() {
        return locationDirections2;
    }

    public void setLocationDirections2(List<LocationDirections2> locationDirections2) {
        this.locationDirections2 = locationDirections2;
    }
}