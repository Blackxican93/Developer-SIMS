import java.io.*;



public class Questions {

    public static void main(String[] args) throws Exception {
        File file = new File(
                "/Users/jr/Desktop/House of Madness/Questions.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        while ((st = br.readLine()) != null)
            System.out.println(st);
    }

}
