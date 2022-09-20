package ben.app.oi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sp = getSharedPreferences("s",MODE_PRIVATE);
        ((TextView)findViewById(R.id.name)).setText(sp.getString("uname",""));
        ((TextView)findViewById(R.id.pass)).setText(sp.getString("pwd",""));
    }
}