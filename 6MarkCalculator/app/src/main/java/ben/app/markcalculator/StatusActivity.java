package ben.app.markcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        TextView status = findViewById(R.id.status);
        TextView percentage = findViewById(R.id.percentage);
        TextView markView = findViewById(R.id.mark);

        if (getIntent() != null) {
            int mark = getIntent().getIntExtra("marks", -1);
            String name = getIntent().getStringExtra("name");
            if (mark > -1) {
                int p = mark * 100 / 300;

                percentage.setText("Percentage: " + p + "%");
                markView.setText("Mark: " + mark);

                if (p > 50)
                    status.setText(name + " Congrats");

                else {
                    status.setTextColor(Color.RED);
                    status.setText(name + " you failed");
                }
            }
        }
    }
}