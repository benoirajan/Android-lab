package ben.app.oi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        tv.append("onCreate\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv.append("onResume\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv.append("onStart\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        tv.append("onPause\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        tv.append("onStop\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        tv.append("onDestroy\n");
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }
}