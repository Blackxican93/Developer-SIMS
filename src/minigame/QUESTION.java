package minigame;

public enum QUESTION {

    QUESTION_1("question_1.txt", 1),
    QUESTION_2("question_2.txt", 2),
    QUESTION_3("question_3.txt", 3),
    QUESTION_4("question_4.txt", 4),
    QUESTION_5("question_5.txt", 5),
    QUESTION_6("question_6.txt", 6);

    private String filename;
    private int num;

    QUESTION(String filename, int num) {
        this.filename = filename;
        this.num = num;
    }

    public String getFilename() {
        return filename;
    }

    public int getNum() {
        return num;
    }

    public static QUESTION getEnumFromNum(int num){
        for(QUESTION question: QUESTION.values()){
            if(question.getNum() == num) {
                return question;
            }
        }
        return null;
    }

}
