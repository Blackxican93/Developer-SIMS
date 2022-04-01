import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class AddItems implements java.io.Serializable{
    static String input = "";
    static String itemNameOption = "";
    static String itemDescriptionOption = "";
    static String itemAttributeOption = "";
    static String itemLocationOption = "";



    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();


            JSONObject addItems = new JSONObject();
            Scanner in = new Scanner(System.in);
            System.out.println("Would you like to add an item to the game? Type Y or N ");

            input = in.nextLine();
            System.out.println("You have entered: " + input);

            if (input.equals("Y")) {
                try {
                    Object obj = jsonParser.parse(new FileReader("item.json"));
                    JSONArray jsonArray = (JSONArray) obj;

                    System.out.println(jsonArray);

                System.out.println("You have the option of creating an item. Simply type in your command name using one word. ");
                itemNameOption = in.nextLine();
                System.out.println("You have entered: " + itemNameOption);
                addItems.put("itemName", itemNameOption);

                System.out.println("You have the option of adding in an item description. Simply type in your command description phrase. ");
                itemDescriptionOption = in.nextLine();
                System.out.println("You have entered: " + itemDescriptionOption);
                addItems.put("itemDescription", itemDescriptionOption);

                System.out.println("You have the option of adding in an item attribute. Simply type in your command example. ");
                itemAttributeOption = in.nextLine();
                System.out.println("You have entered: " + itemAttributeOption);
                addItems.put("itemAttribute", itemAttributeOption);

                System.out.println("You have the option of adding in an item location. Simply type in your command example. ");
                itemLocationOption = in.nextLine();
                System.out.println("You have entered: " + itemLocationOption);
                addItems.put("itemLocation", itemLocationOption);

                jsonArray.add(addItems);
                System.out.println("Your options have been added to the list ");
                System.out.println(jsonArray);

                FileWriter file = new FileWriter("item.json");
                file.write(jsonArray.toJSONString());
                file.flush();
                file.close();

            } catch(ParseException | IOException e){
                e.printStackTrace();
            }

        }
        else if (input.equals("N")) {
            System.out.println("No additions were made. ");
        } else {
            System.out.println("Invalid command. ");
        }
    }
}
