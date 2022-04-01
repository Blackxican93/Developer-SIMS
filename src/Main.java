import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static Game game;

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader in;
        String input;
        String output;
        game = new Game();
        in = new BufferedReader(new InputStreamReader(System.in));
        game.showIntro();
        do {
            System.out.println("What would you like to do?");
            System.out.print("> ");
            input = in.readLine();
            if ("n".equals(input)) {
                System.out.println("See you later ...");
            }else if ("x".equals(input)){
                output = game.runCommand(input);
                System.out.println(output);
            }else {
                output = game.runCommand(input);
                System.out.println(output);
            }
        } while (!"n".equals(input));
    }
}
