package ben.app.oi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void next(View v){
        EditText m1 = findViewById(R.id.m1);
        EditText m2 = findViewById(R.id.m2);
        EditText m3 = findViewById(R.id.m3);
        getSharedPreferences("s",MODE_PRIVATE)
                .edit()
                .putString("uname",uname.getText().toString())
                .putString("pwd",pwd.getText().toString()).apply();

        startActivity(new Intent(this,Second.class));
    }
}