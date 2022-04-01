import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Parser {

    static List<String> verbs = new ArrayList<>(Arrays.asList(
            "go", "take", "drop", "look", "l", "i", "inventory"));
    static List<String> nouns = new ArrayList<>(Arrays.asList("bed", "letter-opener",
            "coffee", "plunger", "tv", "north", "south", "west", "east"));
    static List<String> directions = new ArrayList<>(Arrays.asList("north", "south", "west", "east"));

    static String processVerb(List<String> wordlist) {
        String verb;
        String msg = "";
        verb = wordlist.get(0);
        if (!verbs.contains(verb)) {
            msg = verb + " is not a known verb! ";
        } else {
            switch (verb) {
                case "l":
                case "look":
                    Main.game.look();
                    break;
                case "inventory":
                case "i":
                    Main.game.showInventory();
                    break;
                case "go":
                    msg = "enter [go] and a direction";
                    break;
                case "take":
                    msg = "enter [take] and an item";
                    break;
                case "drop":
                    msg = "enter [drop] and an item";
                    break;
                default:
                    msg = verb + " is not a command in this game";
                    break;
            }
        }
        return msg;
    }

    static String processDirection(List<String> wordlist) {
        String verb;
        String direction;
        String msg = "";
        verb = wordlist.get(0);
        direction = wordlist.get(1);
        if (!directions.contains(direction)) {
            msg = verb + " is not a known direction! ";
        }
        switch (direction) {
            case "north":
                Main.game.goN();
                break;
            case "south":
                Main.game.goS();
                break;
            case "west":
                Main.game.goW();
                break;
            case "east":
                Main.game.goE();
                break;
            default:
                msg = verb + " (not yet implemented)";
                break;
        }
        return msg;
    }

    static String processVerbNoun(List<String> wordlist) {
        String verb;
        String noun;
        String msg = "";
        boolean error = false;
        verb = wordlist.get(0);
        noun = wordlist.get(1);
        if (Objects.equals(verb, "go")){
            System.out.println("You are going " + noun);
            msg = processDirection(wordlist);
            error = true;
        }
        if (!verbs.contains(verb)) {
            msg = verb + " is not a known verb! ";
            error = true;
        }
        if (!nouns.contains(noun)) {
            msg += (noun + " is not a known noun!");
            error = true;
        }
        if (!error) {
            switch (verb) {
                case "take":
                    msg = Main.game.takeOb(noun);
                    break;
                case "drop":
                    msg = Main.game.dropOb(noun);
                    break;
                default:
                    msg += " (not yet implemented)";
                    break;
            }
        }
        return msg;
    }
}
