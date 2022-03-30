import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ReadGameCommands
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonparser = new JSONParser();

        try (FileReader reader = new FileReader("gameCommands.json")) {
            Object obj = jsonparser.parse(reader);

            JSONArray commandsList = (JSONArray) obj;
            System.out.println(commandsList);

            commandsList.forEach(com -> parseCommandsObject( (JSONObject) com) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
       private static void parseCommandsObject(JSONObject commands)
       {
           JSONObject commandsObject= (JSONObject) commands.get("gameCommands");

           String commandName = (String) commandsObject.get("commandName");
           System.out.println("commandName: " + commandName);

           String commandDescription = (String) commandsObject.get("commandDescription");
           System.out.println("commandDescription: " + commandDescription);

           String commandExample = (String) commandsObject.get("commandExample");
           System.out.println("commandExample: " + commandExample);


       }
    }
