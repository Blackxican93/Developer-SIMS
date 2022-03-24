import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Item
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("item.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray itemList = (JSONArray) obj;
            System.out.println(itemList);

            //Iterate over item array
            itemList.forEach( loc -> parseItemObject( (JSONObject) loc ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseItemObject(JSONObject item)
    {
        //Get item object within list
        JSONObject itemObject = (JSONObject) item.get("item");

        //Get item name
        String itemName = (String) itemObject.get("itemName");
        System.out.println(itemName);

        //Get item location
        String itemLocation = (String) itemObject.get("itemLocation");
        System.out.println(itemLocation);
    }
}