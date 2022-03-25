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



        String choice;
        String name;

        System.out.println(introArt);
        System.out.println("Enter your name: ");
        name = sc.nextLine();
        System.out.println("Hello " + name + "! Welcome to the House of Madness.\n" +
                "Are you ready to enter? [y/n]");
        choice = sc.nextLine();
        if (choice.equals("y")) {
            System.out.println("Insert intro story here.");
        } else if (choice.equals("n")) {
            System.out.println("\nExiting game... ");
            System.exit(0);
        } else {
            System.out.println("Invalid entry. [y/n]");
        }

    }
}
