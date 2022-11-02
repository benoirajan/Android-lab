package ben.app.bquiz;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button prev, next, finish;
    private TextView question, answerT;
    View layout;
    private RadioGroup rg;
    private int current = 0;
    private int previous = 0;

    private Question[] questions = {
            new Question(
                    "What is the theme of the ‘International Translation Day 2022’?",
                    1,
                    "A world without barriers",
                    "Accessibility and Translation",
                    "Translation Includes All",
                    "Strengthening peace"
            ),
            new Question(
                    "Which businessperson topped the ‘IIFL Wealth Hurun India 40 & Under Self-Made Rich List 2022’?",
                    1,
                    "Nikhil Kamath",
                    "Bhavish Aggarwal",
                    "Divyank Turakhia",
                    "Kaivalya Vohra"
            ),
            new Question(
                    "Which state is set to set up world’s largest jungle safari park across 10,000 acres?",
                    3,
                    "Tamil Nadu",
                    "Madhya Pradesh",
                    "Haryana",
                    "Nagaland"
            ),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next_btn);
        finish = findViewById(R.id.finish_btn);
        question = findViewById(R.id.question);
        answerT = findViewById(R.id.answer);
        layout = findViewById(R.id.layout);
        rg = findViewById(R.id.ans_radio);

        setQuestion(questions[current]);

        next.setOnClickListener(l -> {
            prev.setEnabled(true);
            setQuestion(questions[++current]);
            if (current == questions.length - 1) {
                next.setEnabled(false);
                finish.setVisibility(View.VISIBLE);
            }
            ObjectAnimator animation = ObjectAnimator.ofFloat(layout, "translationX", 800f,0f);
            animation.setDuration(500);
            animation.start();
        });
        prev.setOnClickListener(l -> {
            next.setEnabled(true);
            finish.setVisibility(View.GONE);
            setQuestion(questions[--current]);
            if (current == 0)
                prev.setEnabled(false);
            ObjectAnimator animation = ObjectAnimator.ofFloat(layout, "translationX", -700f,0f);
            animation.setDuration(500);
            animation.start();
        });

        finish.setOnClickListener(l -> {
            setQuestion(questions[current]);
            rg.setEnabled(false);
            answerT.setVisibility(View.VISIBLE);
            int m = 0;
            for (Question q : questions)
                if (q.getAnswer() == q.uAnswer)
                    m++;

            Toast.makeText(this, "Right Answers:" + m, Toast.LENGTH_SHORT).show();
        });
    }

    private void setQuestion(Question q) {
        final int[] ids = {R.id.r1, R.id.r2, R.id.r3, R.id.r4};

        final int cId = rg.getCheckedRadioButtonId();
        if (cId != -1)
            for (int i = 0; i < 4; i++)
                if (cId == ids[i])
                    questions[previous].uAnswer = i;
        previous = current;

        question.setText((current+1)+". "+q.getQuestion());
        answerT.setText("Right answer: "+q.getOptions()[q.getAnswer()]);
        for (int i = 0; i < 4; i++)
            setOption(rg.getChildAt(i), q.getOptions()[i]);

        rg.clearCheck();
        if (q.uAnswer != -1)
            rg.check(ids[q.uAnswer]);
    }

    private void setOption(View childAt, String option) {
        RadioButton rb = (RadioButton) childAt;
        rb.setText(option);
    }

}