import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;



public class InterviewQuestions {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

        JSONParser parser = new JSONParser();
       JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("/Users/jr/Desktop/House of Madness/Questions.json"));

       for (Object o : jsonArray)
        {
            JSONObject question = (JSONObject) o;
            //Q1
            String Q1 = (String) question.get("Q1");
            String A1 = (String) question.get("A1");
            String B1 = (String) question.get("B1");
            String C1 = (String) question.get("C1");
            String D1 = (String) question.get("D1");
            System.out.println("Q1: " + Q1);
            System.out.println("A: " + A1);
            System.out.println("B: " + B1);
            System.out.println("C: " + C1);
            System.out.println("D: " + D1);

            //Q1 Prompt
            Scanner myObj = new Scanner(System.in);
            String answer;

            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            //Q2
            String Q2 = (String) question.get("Q2");
            String A2 = (String) question.get("A2");
            String B2 = (String) question.get("B2");
            String C2 = (String) question.get("C2");
            String D2 = (String) question.get("D2");
            System.out.println("Q2: " + Q2);
            System.out.println("A: " + A2);
            System.out.println("B: " + B2);
            System.out.println("C: " + C2);
            System.out.println("D: " + D2);

            //Q2 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            //Q3
            String Q3 = (String) question.get("Q3");
            String A3 = (String) question.get("A3");
            String B3 = (String) question.get("B3");
            String C3 = (String) question.get("C3");
            String D3 = (String) question.get("D3");
            System.out.println("Q3: " + Q3);
            System.out.println("A: " + A3);
            System.out.println("B: " + B3);
            System.out.println("C: " + C3);
            System.out.println("D: " + D3);

            //Q3 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            //Q4
            String Q4 = (String) question.get("Q4");
            String A4 = (String) question.get("A4");
            String B4 = (String) question.get("B4");
            String C4 = (String) question.get("C4");
            String D4 = (String) question.get("D4");
            System.out.println("Q4: " + Q4);
            System.out.println("A: " + A4);
            System.out.println("B: " + B4);
            System.out.println("C: " + C4);
            System.out.println("D: " + D4);

            //Q4 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            //Q5
            String Q5 = (String) question.get("Q5");
            String A5 = (String) question.get("A5");
            String B5 = (String) question.get("B5");
            String C5 = (String) question.get("C5");
            String D5 = (String) question.get("D5");
            System.out.println("Q5: " + Q5);
            System.out.println("A: " + A5);
            System.out.println("B: " + B5);
            System.out.println("C: " + C5);
            System.out.println("D: " + D5);

            //Q5 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);

            //Q6
            String Q6 = (String) question.get("Q6");
            String A6 = (String) question.get("A6");
            String B6 = (String) question.get("B6");
            String C6 = (String) question.get("C6");
            String D6 = (String) question.get("D6");
            System.out.println("Q6: " + Q6);
            System.out.println("A: " + A6);
            System.out.println("B: " + B6);
            System.out.println("C: " + C6);
            System.out.println("D: " + D6);

            //Q6 Prompt
            System.out.println("Enter A, B, C, or D ");
            answer = myObj.nextLine();

            System.out.println("You've entered: " + answer);
        }



    }

}
