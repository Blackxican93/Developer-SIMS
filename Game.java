import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        // System variables
        Scanner myScanner = new Scanner(System.in);

        // Game intro
        String introArt = """
                               )
                             ( _   _._              WELCOME TO THE HOUSE OF MADNESS!
                              |_|-'_~_`-._
                           _.-'-_~_-~_-~-_`-._                 Authors:
                       _.-'_~-_~-_-~-_~_~-_~-_`-._          Justin Peebles
                      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         Vernon Stephens
                        |  []       []       [] |            Maria Nieves
                        |           __    ___   |
                      ._|  []      | .|  [___]  |_._._._._._._._._._._._._._._._._.
                      |=|________()|__|()_______|=|=|=|=|=|=|=|=|=|=|=|=|=|=|=|=|=|
                    ^^^^^^^^^^^^^^^ === ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 You are an aspiring Software Developer. You just landed an interview
                 with Amazon. Despite any unexpected encounters you may face, you MUST
                 complete the given assessment in time.
                 
                ======================================================================
                """;



        String gameStart;

        System.out.println(introArt);
        System.out.println("Do you wish to continue? y/n");
        gameStart = myScanner.nextLine();
        if (gameStart.equals("y")) {
            System.out.println("Please choose your character: ");
        } else if (gameStart.equals("")) {
            System.out.println("Invalid entry. Please choose y/n");
        } else {
            System.out.println("\nExiting game... ");
            System.exit(0);
        }

    }
}
