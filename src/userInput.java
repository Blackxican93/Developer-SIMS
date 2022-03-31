import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class userInput {
    public static void parseCommand(List<String> wordlist) {
        String verb;
        String noun;

        List<String> verbs = new ArrayList<>(Arrays.asList(
                "take", "drop", "look", "run", "go",
                "grasp", "fall", "glance", "sprint", "move",
                "extract", "deposit", "survey", "race", "travel",
                "draw", "set", "down", "scurry", "leave"));
        List<String> nouns = new ArrayList<>(Arrays.asList("north", "south", "east", "west", "sword", "laptop"));


        if (wordlist.size() != 2) {
            System.out.println("Only 2 word commands allowed!");
        } else {
            verb = wordlist.get(0);
            noun = wordlist.get(1);
            if (!verbs.contains(verb)) {
                System.out.println(verb + " is not a known verb!");
            }
            if (!nouns.contains(noun)) {
                System.out.println(noun + " is not a known noun!");
            }

        }
    }

    public static List<String> wordList(String input) {
        String delimiters = " \t,.:;?!\"'";
        List<String> stringList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delimiters);
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            stringList.add(t);
        }
        return stringList;
    }

    public static String runCommand(String inputString) {
        List<String> wl;
        String s = "next command";
        String lowString = inputString.trim().toLowerCase();

        if (!lowString.equals("q")) {
            if (lowString.equals("")) {
                s = "You must enter a command";
            } else {
                wl = wordList(lowString);
                wl.forEach((run) -> System.out.println(run));
                parseCommand(wl);
            }
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in;
        String input;
        String output;

        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("> ");
            input = in.readLine();
            output = runCommand(input);
            System.out.println(output);
        } while (!"q".equals(input));
    }
}