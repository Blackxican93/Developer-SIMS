package minigame;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the mini game!");
        System.out.println("Input number of attempts you would like: ");
        int lives = 3;
        int level = getGameLevel(in);

        MiniGame minigame = new MiniGame(QUESTION.getEnumFromNum(level), lives);
        List<String> words = minigame.getWords();

        boolean won = false;

        while(minigame.isAlive()) {
            System.out.println("You have " + minigame.getLivesLeft() + " lives left!");
            System.out.println("Presented is a quiz. Enter the corresponding index");
            System.out.println(words.get(0));
            printWords(words);

            int guessedIndex = Integer.parseInt(in.nextLine());
            if (guessedIndex < 0 || guessedIndex >= words.size()) {
                System.out.println("Incorrect index, please try again!");
            } else {
                int answer = minigame.play(guessedIndex);

                if (answer == -2) {
                    System.out.println("Incorrect index, please try again!");
                } else if (answer == -1) {
                    won = true;
                    break;
                }
            }
        }

        if (won) {
            System.out.println("Hurray! You won!");
        } else {
            System.out.println("There are not lives left, better hacking next time");
            System.out.println("The correct word is: " + minigame.revealPassword());
        }
    }

    private static void printWords(List<String> words) {
        StringBuilder sb = new StringBuilder("");

        for(int i = 1; i < words.size(); i++) {
            sb.append(words.get(i));
            sb.append(" : ");
            sb.append(i);

            if (i != words.size() - 1) {
                sb.append(" , ");
            }
        }

        System.out.println(sb);
    }

    private static int getGameLevel(Scanner in) {
        int level = 0;
        while(true) {
            System.out.println("Please select a question: 1, 2, 3, 4, 5, 6");
            level = Integer.parseInt(in.nextLine());
            //if level is not in this range
            if (level < 1 || level > 7) {
                System.out.println("Oops! Incorrect level chosen, try again!");
            } else {
                return level;
            }
        }
    }
}
