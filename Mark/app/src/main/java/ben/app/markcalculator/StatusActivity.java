package ben.app.markcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        TextView status = findViewById(R.id.status);
        TextView percentage = findViewById(R.id.percentage);
            int mark = getIntent().getIntExtra("marks", 0);
                int p = mark * 100 / 300;
                percentage.setText("Percentage: " + p + "%");
                if (p > 50)
                    status.setText("Passed");
                else {
                    status.setText("Failed");
                }
            }
        }

