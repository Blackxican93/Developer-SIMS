package minigame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MiniGame {

    private QUESTION question;
    private List<String> words;
    private String actualPassword;
    private int livesLeft;

    public MiniGame(QUESTION question, int lives) {
        this.question = question;
        this.livesLeft = lives;
        this.words = new ArrayList<>();

        loadQuestion();
        chooseAnswer();
    }

    public int play(int index) {

        if (!isAlive() || index < 0 || index >= this.words.size()) {
            return -2;
        }

        int answer = computeAnswer(this.words.get(index));
        if (answer == -1) {
            return -1;
        }

        this.livesLeft--;
        return answer;
    }

    public boolean isAlive() {
        return this.livesLeft > 0;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public List<String> getWords() {
        return words;
    }

    public String revealPassword() {
        this.livesLeft = 0;
        return this.actualPassword;
    }

    private int computeAnswer(String guess) {
        if (this.actualPassword.equals(guess)) {
            return -1;
        }
        return -2;
    }

    private void chooseAnswer(){
        this.actualPassword = this.words.get(2);
    }

    private void loadQuestion(){
        String filename = "resources/" + this.question.getFilename();
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        try {
            File file = new File(resource.toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}