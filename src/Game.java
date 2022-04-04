import minigame.QUESTION;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game implements java.io.Serializable {

    private ArrayList<Location> map;
    private Player player;
    private List<Item> items;
    private List<Location> locations;

    public Game() throws IOException, ParseException {
        this.map = new ArrayList<Location>();
        this.locations = new ArrayList<>();
        this.items = new ArrayList<>();

        JsonHelper jsonhelper = new JsonHelper();
        locations = jsonhelper.readLocationsFromJson();
        items = jsonhelper.populateItemsFromJson();

        Location oneLocation = locations.get(0);
        Item oneItem = items.get(0);
        ThingList bedroomList = new ThingList();
        bedroomList.add(new Treasure(oneItem.getItemName(), oneItem.getItemDescription(), 1));
        map.add(new Location(oneLocation.getLocationName(), oneLocation.getLocationDescription(), DIRECTION.NOEXIT, 3, DIRECTION.NOEXIT, 1,bedroomList ));

        Location twoLocation = locations.get(1);
        Item twoItem = items.get(1);
        ThingList officeList = new ThingList();
        officeList.add(new Treasure(twoItem.getItemName(), twoItem.getItemDescription(), 1));
        map.add(new Location(twoLocation.getLocationName(), twoLocation.getLocationDescription(), DIRECTION.NOEXIT, 2, 0, DIRECTION.NOEXIT, officeList));

        Location threeLocation = locations.get(2);
        Item threeItem = items.get(2);
        ThingList kitchenList = new ThingList();
        kitchenList.add(new Treasure(threeItem.getItemName(), threeItem.getItemDescription(), 1));
        map.add(new Location(threeLocation.getLocationName(), threeLocation.getLocationDescription(), 1, 3, DIRECTION.NOEXIT, 4, kitchenList));

        Location fourLocation = locations.get(3);
        Item fourItem = items.get(3);
        ThingList bathroomList = new ThingList();
        bathroomList.add(new Treasure(fourItem.getItemName(), fourItem.getItemDescription(), 1));
        map.add(new Location(fourLocation.getLocationName(), fourLocation.getLocationDescription(), 0, DIRECTION.NOEXIT, DIRECTION.NOEXIT, 2, bathroomList));

        Location fiveLocation = locations.get(4);
        Item fiveItem = items.get(4);
        ThingList livingRoomList = new ThingList();
        livingRoomList.add(new Treasure(fiveItem.getItemName(), fiveItem.getItemDescription(), 1));
        map.add(new Location(fiveLocation.getLocationName(), fiveLocation.getLocationDescription(), DIRECTION.NOEXIT, DIRECTION.NOEXIT, 2, DIRECTION.NOEXIT, livingRoomList));

        ThingList playerlist = new ThingList();

        player = new Player("player", "resident in the House of Maddness", playerlist, map.get(0));
    }

    private ArrayList getMap() {
        return map;
    }

    private void setMap(ArrayList aMap) {
        map = aMap;
    }

    private Player getPlayer() {
        return player;
    }

    private void setPlayer(Player aPlayer) {
        player = aPlayer;
    }

    private void transferOb(Thing t, ThingList fromlist, ThingList tolist) {
        fromlist.remove(t);
        tolist.add(t);
    }

    String takeOb(String obname) {
        String retStr = "";
        Thing t = player.getLocation().getThings().thisOb(obname);

        if (obname.equals("")) {
            obname = "nameless object";
        }
        if (t == null) {
            retStr = "There is no " + obname + " here!";
        } else {
            transferOb(t, player.getLocation().getThings(), player.getThings());
            retStr = obname + " taken!";
        }
        return retStr;
    }

    String dropOb(String obname) {
        String retStr = "";
        Thing t = player.getThings().thisOb(obname);

        if (obname.equals("")) {
            retStr = "You'll have to tell me which object you want to drop!";
        } else if (t == null) {
            retStr = "You haven't got one of those!";
        } else {
            transferOb(t, player.getThings(), player.getLocation().getThings());
            retStr = obname + " dropped!";
        }
        return retStr;
    }

    private void moveActorTo(Player p, Location aLocation) {
        p.setLocation(aLocation);
    }

    private int moveTo(Player anActor, DIRECTION dir) {
        Location r = anActor.getLocation();
        int exit;

        switch (dir) {
            case NORTH:
                exit = r.getN();
                break;
            case SOUTH:
                exit = r.getS();
                break;
            case EAST:
                exit = r.getE();
                break;
            case WEST:
                exit = r.getW();
                break;
            default:
                exit = DIRECTION.NOEXIT;
                break;
        }
        if (exit != DIRECTION.NOEXIT) {
            moveActorTo(anActor, map.get(exit));
        }
        return exit;
    }

    void movePlayerTo(DIRECTION dir) {
        if (moveTo(player, dir) == DIRECTION.NOEXIT) {
            showStr("No Exit!");
        } else {
            look();
        }
    }

    void goN() {
        movePlayerTo(DIRECTION.NORTH);
    }

    void goS() {
        movePlayerTo(DIRECTION.SOUTH);
    }

    void goW() {
        movePlayerTo(DIRECTION.WEST);
    }

    void goE() {
        movePlayerTo(DIRECTION.EAST);
    }

    void look() {
        showStr("You are in the " + getPlayer().getLocation().describe());
    }

    void showStr(String s) {
        System.out.println(s);
    }

    void showInventory() {
        showStr("You have " + getPlayer().getThings().describeThings());
    }


    private String parseCommand(List<String> wordlist) {
        String msg;
        if (wordlist.size() == 1) {
            msg = Parser.processVerb(wordlist);
        } else if (wordlist.size() == 2) {
            msg = Parser.processVerbNoun(wordlist);
        } else {
            msg = "Only 2 word commands allowed!";
        }
        return msg;
    }

    private static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }

    void showIntro() {
        System.out.println(TextImages.getIntroArt());
        String s = "";
        s =
                "WHEN YOU ARE READY TO PLAY THE GAME PLEASE SELECT [y]?\n" +
                "REMEMBER: AT ANY POINT YOU CAN PRESS [n] TO SEE QUIT\n" +
                "REMEMBER: AT ANY POINT YOU CAN PRESS [x] TO SEE MENU OPTIONS\n" +
                "Type save or load to save game progress or load previous progress";
        showStr(s);
        look();
    }
    void InterviewQuestions() throws IOException, ParseException {
        Scanner myObj = new Scanner(System.in);
        String answer;

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("Questions.json"));

        System.out.println("Congratulations on making the first step to becoming an Amazon Software Developer! You will be prompted with 6 multiple answer questions on Java.");
        System.out.println("Good luck and avoid any interruptions! ");
        System.out.println("Plot Twist! ");
        System.out.println("____________");
        System.out.println("There is a Java monster named Gosling lurking. He does not like incorrect answers on Java and demands perfection. ");
        System.out.println("You need a perfect score to pass the interview. Oh and....the monster will kill you if you don't get every question right.");
        System.out.println("___________________________________________________________________________________________________________________________");
        Integer scoreCount = 0;

        for (Object o : jsonArray) {
            JSONObject question = (JSONObject) o;
            //Q1
            String Q1 = (String) question.get("Q1");
            String A1 = (String) question.get("A1");
            String B1 = (String) question.get("B1");
            String C1 = (String) question.get("C1");
            String D1 = (String) question.get("D1");
            System.out.println("Q1: " + Q1);
            System.out.println("A: " + A1);
            System.out.println("B: " + B1);
            System.out.println("C: " + C1);
            System.out.println("D: " + D1);

            //Q1 Prompt

            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            if (answer.equals("A")) {
                scoreCount += 1;
                System.out.println("Correct! You now have " + scoreCount + "points");
            }
            else {
                System.out.println("Wrong answer Yo! The Java monster is getting closer! Keep going, maybe he'll let you live. ");
            }

            //Q2
            String Q2 = (String) question.get("Q2");
            String A2 = (String) question.get("A2");
            String B2 = (String) question.get("B2");
            String C2 = (String) question.get("C2");
            String D2 = (String) question.get("D2");
            System.out.println("Q2: " + Q2);
            System.out.println("A: " + A2);
            System.out.println("B: " + B2);
            System.out.println("C: " + C2);
            System.out.println("D: " + D2);

            //Q2 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            if (answer.equals("B")) {
                scoreCount += 1;
                System.out.println("Correct! You now have " + scoreCount + "points");
            }
            else {
                System.out.println("Wrong answer Yo! The Java monster is getting closer! Keep going, maybe he'll let you live. ");
            }

            //Q3
            String Q3 = (String) question.get("Q3");
            String A3 = (String) question.get("A3");
            String B3 = (String) question.get("B3");
            String C3 = (String) question.get("C3");
            String D3 = (String) question.get("D3");
            System.out.println("Q3: " + Q3);
            System.out.println("A: " + A3);
            System.out.println("B: " + B3);
            System.out.println("C: " + C3);
            System.out.println("D: " + D3);

            //Q3 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            if (answer.equals("B")) {
                scoreCount += 1;
                System.out.println("Correct! You now have " + scoreCount + "points");
            }
            else {
                System.out.println("Wrong answer Yo! The Java monster is getting closer! Keep going, maybe he'll let you live. ");
            }

            //Q4
            String Q4 = (String) question.get("Q4");
            String A4 = (String) question.get("A4");
            String B4 = (String) question.get("B4");
            String C4 = (String) question.get("C4");
            String D4 = (String) question.get("D4");
            System.out.println("Q4: " + Q4);
            System.out.println("A: " + A4);
            System.out.println("B: " + B4);
            System.out.println("C: " + C4);
            System.out.println("D: " + D4);

            //Q4 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            if (answer.equals("B")) {
                scoreCount += 1;
                System.out.println("Correct! You now have " + scoreCount + "points");
            }
            else {
                System.out.println("Wrong answer Yo! The Java monster is getting closer! Keep going, maybe he'll let you live. ");
            }

            //Q5
            String Q5 = (String) question.get("Q5");
            String A5 = (String) question.get("A5");
            String B5 = (String) question.get("B5");
            String C5 = (String) question.get("C5");
            String D5 = (String) question.get("D5");
            System.out.println("Q5: " + Q5);
            System.out.println("A: " + A5);
            System.out.println("B: " + B5);
            System.out.println("C: " + C5);
            System.out.println("D: " + D5);

            //Q5 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            if (answer.equals("A")) {
                scoreCount += 1;
                System.out.println("Correct! You now have " + scoreCount + "points");
            }
            else {
                System.out.println("Wrong answer Yo! The Java monster is getting closer! Keep going, maybe he'll let you live. ");
            }

            //Q6
            String Q6 = (String) question.get("Q6");
            String A6 = (String) question.get("A6");
            String B6 = (String) question.get("B6");
            String C6 = (String) question.get("C6");
            String D6 = (String) question.get("D6");
            System.out.println("Q6: " + Q6);
            System.out.println("A: " + A6);
            System.out.println("B: " + B6);
            System.out.println("C: " + C6);
            System.out.println("D: " + D6);

            //Q6 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            if (answer.equals("A")) {
                scoreCount += 1;
                System.out.println("Correct! You now have " + scoreCount + "points");
            }
            else {
                System.out.println("Wrong answer Yo! The Java monster is getting closer! Keep going, maybe he'll let you live. ");
            }
        }

        if(scoreCount == 6){
            System.out.println("Congratulations on getting a perfect score! Your Amazon recruiter Kevin Greene will be reaching out to you shortly. Pending any paperwork issues, welcome to the Amazon Team! ");
    }
        else {
            System.out.println("Better luck next time! Unfortunately you did not pass the mandatory requirement. Oh ya...and you died by the hands of the Java monster.");
        }
    }
    private void showMenuToPlayer() {
        String s;
        s = "Choose your action: \n"+
                "1. View verbs\n" +
                "2. View nouns\n" +
                "3. View directions\n" +
                "4. Interview Questions\n" +
                "5. Add Game Commands\n" +
                "6. Add Items\n" +
                "7. Add Locations\n" +
                "8. Start background music\n" +
                "9. Stop background music\n" +
                "10. View Game introduction\n" +
                "";
        System.out.println(s);
    }

    public String runCommand(String inputstr) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        List<String> wordlist;
        String s = "\n";
        String lowstr = inputstr.trim().toLowerCase();
        if (!lowstr.equals("n")) {
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else if( lowstr.equals("y")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter your name: ");
                String name = sc.nextLine();
                player.setName(name);
                System.out.println("          " + player.getName() + ", Welcome to House of Maddness ");
            } else if( lowstr.equals("x")) {
                showMenuToPlayer();
                Scanner sc = new Scanner(System.in);
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        System.out.println(Parser.verbs);
                        break;
                    case 2:
                        System.out.println(Parser.nouns);
                        break;
                    case 3:
                        System.out.println(Parser.directions);
                        break;
                    case 4:
                        InterviewQuestions();
                        break;
                    case 5:
                        AddGameCommands();
                        break;
                    case 6:
                        AddItems();
                        break;
                    case 7:
                        AddLocations();
                        break;
                    case 8:
                        playAudio();
                        break;
                    case 9:
                        stopAudio();
                        break;
                    case 10:
                        showIntro();
                        break;
                    default:
                        System.out.println("Please enter 1, 2, 3, 4, 5, 6, 7, 8, 9, or 10");
                        break;
                }
            } else {
                wordlist = wordList(lowstr);
                s = parseCommand(wordlist);
            }
        }
        return s;
    }

    void AddGameCommands () {
        String input = "";
        String commandNameOption = "";
        String commandDescriptionOption = "";
        String commandExampleOption = "";

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

    void AddItems () {
        String input = "";
        String itemNameOption = "";
        String itemDescriptionOption = "";
        String itemAttributeOption = "";
        String itemLocationOption = "";

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

    void AddLocations(){
        String input = "";
        String locationNameOption = "";
        String locationDescriptionOption = "";
        String locationItemsOption = "";
        String locationDirectionsOption = "";

        JSONParser jsonParser = new JSONParser();

        JSONObject addLocation = new JSONObject();
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to add a location to the game? Type Y or N ");

        input = sc.nextLine();
        System.out.println("You have entered: " + input);

        if (input.equals("Y")) {
            try {
                Object obj = jsonParser.parse(new FileReader("location.json"));
                JSONArray jsonArray = (JSONArray)obj;

                System.out.println(jsonArray);


                System.out.println("You have the option of creating a location inside of the house. Simply type in your location name using one word. ");
                locationNameOption = sc.nextLine();
                System.out.println("You have entered: " + locationNameOption);
                addLocation.put("locationName", locationNameOption);

                System.out.println("You have the option of creating a location direction inside of the house. Simply type in your location direction. ");
                locationDirectionsOption = sc.nextLine();
                System.out.println("You have entered: " + locationDirectionsOption);
                addLocation.put("locationDirections", locationDirectionsOption);

                System.out.println("You have the option of creating a location description. Simply type in your location description. ");
                locationDescriptionOption = sc.nextLine();
                System.out.println("You have entered: " + locationDescriptionOption);
                addLocation.put("locationDescription", locationDescriptionOption);

                System.out.println("You have the option of creating a location item. Simply type in your location item. ");
                locationItemsOption = sc.nextLine();
                System.out.println("You have entered: " + locationItemsOption);
                addLocation.put("locationItems", locationItemsOption);


                jsonArray.add(addLocation);
                System.out.println("Your options have been added to the list ");
                System.out.println(jsonArray);

                FileWriter file = new FileWriter("location.json");
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

    Clip clip;



    public void playAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File song = new File("bgmusic.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.flush();
    }



    public void stopAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        clip.stop();
        clip.flush();
        clip.close();
    }

}