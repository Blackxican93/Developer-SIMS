import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class AddGameCommands {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader("gameCommands.json"));
            JSONArray jsonArray = (JSONArray) obj;

            System.out.println(jsonArray);

            JSONObject addGameCommands = new JSONObject();
            addGameCommands.put("commandName", "run");
            addGameCommands.put("commandDescription", "The monster is on to you so you better run! ");
            addGameCommands.put("commandExample", "run office");


            jsonArray.add(addGameCommands);

            System.out.println(jsonArray);

            FileWriter file = new FileWriter("gameCommands.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
}
