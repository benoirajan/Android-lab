package ben.app.oi;

import android.app.Activity;
import android.content.Intent;
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
        EditText uname = findViewById(R.id.name);
        EditText pwd = findViewById(R.id.pass);
//        Intent intent = new Intent(this,SecondActivity.class);
//        intent.putExtra("name",uname.getText().toString());
//        intent.putExtra("pass",pwd.getText().toString());

        if(uname.getText().toString().equals("benoi") && pwd.getText().toString().equals("123")) {
            uname.setError(null);
            Toast.makeText(this, String.format("Welcome: %s",
                            uname.getText().toString()),
                    Toast.LENGTH_SHORT).show();
        }
        else uname.setError("Invalid username or password");
    }
}