package ben.app.gridapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView operand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operand =findViewById(R.id.op_text);
    }

    public void num(View v){
        Button b = (Button) v;
        operand.append(b.getText());
    }
}