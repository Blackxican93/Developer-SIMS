import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;
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

        // Game intro
        System.out.println(introArt);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Hello " + name + "! Choose your character: \n" +
                "1) Senior Developer\n" +
                "2) Junior Developer");
        choice = sc.nextLine();
        if (choice.equals("1")) {
            System.out.println("Hello " + name + "! Welcome to the House of Madness.\n" +
                    "You have chosen to be a Senior Developer!");
        }

        else if(choice.equals("2")) {
            System.out.println("Hello " + name + "! Welcome to the House of Madness.\n" +
                    "You have chosen to be a Junior Developer!");
        }
        else if(choice.equals("exit")) {
            System.out.println("\nExiting game... ");
            System.exit(0);

        } else {
            System.out.println("Invalid command. Please try again.");
        }

    }
}
