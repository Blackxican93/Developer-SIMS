import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class AddGameCommands {
    static String input = "";
    static String commandNameOption = "";
    static String commandDescriptionOption = "";
    static String commandExampleOption = "";


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();

        JSONObject addGameCommands = new JSONObject();
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to add a command to the game? Type Y or N ");

        input = in.nextLine();
        System.out.println("You have entered: " + input);

        if (input.equals("Y")) {
            try {
                Object obj = jsonParser.parse(new FileReader("gameCommands.json"));
                JSONArray jsonArray = (JSONArray) obj;

                System.out.println(jsonArray);


                System.out.println("You have the option of creating a command. Simply type in your command name using one word. ");
                commandNameOption = in.nextLine();
                System.out.println("You have entered: " + commandNameOption);
                addGameCommands.put("commandName", commandNameOption);

                System.out.println("You have the option of adding in a command description. Simply type in your command description phrase. ");
                commandDescriptionOption = in.nextLine();
                System.out.println("You have entered: " + commandDescriptionOption);
                addGameCommands.put("commandDescription", commandDescriptionOption);

                System.out.println("You have the option of adding in a command example. Simply type in your command example. ");
                commandExampleOption = in.nextLine();
                System.out.println("You have entered: " + commandExampleOption);
                addGameCommands.put("commandExample", commandExampleOption);


                jsonArray.add(addGameCommands);
                System.out.println("Your options have been added to the list ");
                System.out.println(jsonArray);

                FileWriter file = new FileWriter("gameCommands.json");
                file.write(jsonArray.toJSONString());
                file.flush();
                file.close();

            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }

        } else if (input.equals("N")) {
            System.out.println("No additions were made. ");
        } else {
            System.out.println("Invalid command. ");
        }
    }
}
