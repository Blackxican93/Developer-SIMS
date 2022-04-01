import org.json.simple.parser.ParseException;
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
        s = "You are an aspiring Software Developer. You just landed an interview\n"+
                "with Amazon. Despite any unexpected encounters you may face, you MUST\n" +
                "complete the given assessment in time.\n" +
                "WHEN YOU ARE READY TO PLAY THE GAME PLEASE SELECT [y]?\n" +
                "REMEMBER: AT ANY POINT YOU CAN PRESS [n] TO SEE QUIT\n" +
                "REMEMBER: AT ANY POINT YOU CAN PRESS [x] TO SEE MENU OPTIONS";
        showStr(s);
        look();
    }
    private void showMenuToPlayer() {
        String s;
        s = "Choose your action: \n"+
                "1. View verbs\n" +
                "2. View nouns\n" +
                "3. View directions\n" +
                "4. View Game introduction\n" +
                "";
        System.out.println(s);
    }

    public String runCommand(String inputstr) {
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
                        showIntro();
                        break;
                    default:
                        System.out.println("Please enter 1, 2, 3");
                        break;
                }
            } else {
                wordlist = wordList(lowstr);
                s = parseCommand(wordlist);
            }
        }
        return s;
    }

}