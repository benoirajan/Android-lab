package ben.app.bquiz;

public class Question {
    private final String[] options;
    private final int answer;
    int uAnswer = -1;

    public String[] getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    private final String question;

    /**
     * Default Constructor for Question class.
     * @param q   is Question
     * @param opt options
     */
    public Question(String q, int correct, String... opt) {
        this.question = q;
        this.options = opt;
        this.answer = correct-1;
    }
}
