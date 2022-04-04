import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Scanner;

public class Main {

    static Game game;
    //add extends to every class necessary
    private static void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("Adv.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game); // game
            oos.flush(); // write out any buffered bytes
            oos.close();
            System.out.print("Game saved\n");
        } catch (Exception e) {
            System.out.print("Can't save data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    private static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("Adv.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.print("\n---Game loaded---\n");
        } catch (Exception e) {
            System.out.print("Can't load data.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        BufferedReader in;
        String input;
        String output = "";
        game = new Game();
        in = new BufferedReader(new InputStreamReader(System.in));
        game.showIntro();
        do {
            game.look();
            game.detailedLook();
            System.out.println("What would you like to do? ");
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            game.runCommand(input);

            System.out.print("> ");

            switch (input) {
                case "save":
                    saveGame();
                    break;
                case "load":
                    loadGame();
                    break;
                default:
                    output = game.runCommand(input);
                    break;
            }
            if (!output.trim().isEmpty()) {
                game.showStr(output);
            }
        } while (!"n".equals(input));
    }

}