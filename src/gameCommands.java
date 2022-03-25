import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;



public class gameCommands
{
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, NullPointerException
    {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("/Users/jr/Desktop/House of Madness/gameCommands.json"));

       for (Object o : jsonArray)
       {
           JSONObject command = (JSONObject) o;

           String gameCommands = (String) command.get("gameCommand1").toString();
           System.out.println(gameCommands);

           String commandName = (String) command.get("commandName1").toString();
           System.out.println(commandName);

           String commandDescription = (String) command.get("commandDescription1").toString();
           System.out.println(commandDescription);

           String commandExamples = (String) command.get("commandExamples1").toString();
           System.out.println(commandExamples);

       }
    }


}