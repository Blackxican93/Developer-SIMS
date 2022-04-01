import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonHelper implements java.io.Serializable{

    public List<Location> readLocationsFromJson() throws IOException, ParseException {

        List<Location> locations = new ArrayList<>();

        JSONParser parser = new JSONParser();

        JSONArray a = (JSONArray) parser.parse(new FileReader("location.json"));

        for (Object o : a) {

            JSONObject locationObj = (JSONObject) o;
            Map<String, String> location = (Map<String, String>) locationObj.get("location");
            String locationName = location.get("locationName");
            String locationDescription = location.get("locationDescription");

//            JSONArray locationItemsJSONArr = (JSONArray) ((Map<?, ?>) locationObj.get("location")).get("locationItems");
////            List<LocationItem> locationItems = new ArrayList<>();
//            for (int i = 0; i < locationItemsJSONArr.size(); i++) {
//                String currentItemName = locationItemsJSONArr.get(i).toString();
//                int startIndex = currentItemName.indexOf(":") + 2;
//                int endIndex = currentItemName.length() - 2;
//                String itemName = currentItemName.substring(startIndex, endIndex);
////                locationItems.add(new LocationItem(itemName));
//            }


//            JSONObject locationDirectionsJsonObj = (JSONObject) ((Map<?, ?>) locationObj.get("location")).get("locationDirections");
////            List<LocationDirection> locationDirections = new ArrayList<>();
//
//
//            for (Iterator iterator = locationDirectionsJsonObj.keySet().iterator(); iterator.hasNext(); ) {
//                String key = (String) iterator.next();
//                String val = (String) locationDirectionsJsonObj.get(key);
////                locationDirections.add(new LocationDirection(key.trim(), val.trim()));
//            }
//            locations.add(new Location(locationName, locationDescription, locationItems, locationDirections));
            locations.add(new Location(locationName, locationDescription));

        }

        return locations;
    }

    public List<Item> populateItemsFromJson() throws IOException, ParseException {

        List<Item> items = new ArrayList<>();

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

        return items;
    }


}

