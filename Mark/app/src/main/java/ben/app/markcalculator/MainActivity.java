package ben.app.markcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.calc).setOnClickListener(v -> {
            int m1 = Integer.parseInt(((EditText) findViewById(R.id.m1)).getText().toString());
            int m2 = Integer.parseInt(((EditText) findViewById(R.id.m2)).getText().toString());
            int m3 = Integer.parseInt(((EditText) findViewById(R.id.m3)).getText().toString());
            Intent intent = new Intent(this,StatusActivity.class);
            intent.putExtra("marks", m1 + m2 + m3);
            startActivity(intent);
        });
    }
}