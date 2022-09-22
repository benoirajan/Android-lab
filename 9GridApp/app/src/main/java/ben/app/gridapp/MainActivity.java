package ben.app.gridapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView op1Text, op2Text, resultTxt, opr;
    private int opId = R.id.add;
    private double op1, op2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opr = op1Text = findViewById(R.id.op_text);
        op2Text = findViewById(R.id.op2);
        resultTxt = findViewById(R.id.result);
    }

    public void num(View v) {
        Button b = (Button) v;
        if (b.getText().toString().equals(".") && opr.getText().toString().contains("."))
            return;
        opr.append(b.getText());
    }

    public void operator(View v) {
        switch (v.getId()) {
            case R.id.ac:
                clear();
                break;
            case R.id.equal:
                findResult();
                break;
            case R.id.add:
            case R.id.sub:
            case R.id.division:
            case R.id.multiply:
                opId = v.getId();
                performCalc(v.getId());
                break;
        }
    }

    private void performCalc(int id) {
        if (op1Text.getText().toString() == null || op1Text.getText().toString().isEmpty())
            return;

        if (op2Text.getText().toString() == null || op2Text.getText().toString().isEmpty())
            op1 = Double.parseDouble(op1Text.getText().toString());
        switch (id) {
            case R.id.add:
                op1Text.append(" +");
                op1Text.setText(findResult()+" +");
                break;
            case R.id.sub:
                op1Text.append(" -");
                break;
            case R.id.division:
                op1Text.append(" ÷");
                break;
            case R.id.multiply:
                op1Text.append(" ×");
                break;
        }
        op2Text.setText("");
        opr = op2Text;
    }

    private double findResult() {
        if (op2Text.getText().toString() == null || op2Text.getText().toString().isEmpty())
            return op1;
        op2 = Double.parseDouble(op2Text.getText().toString());
        double res = 0;
        switch (opId) {
            case R.id.add:
                res = op1 + op2;
                break;
            case R.id.sub:
                res = op1 - op2;
                break;
            case R.id.division:
                res = op1 / op2;
                break;
            case R.id.multiply:
                res = op1 * op2;
                break;
        }
        resultTxt.setText("= " + res);
        opr = op1Text;
        return res;
    }

    private void clear() {
        op1Text.setText("");
        op2Text.setText("");
        resultTxt.setText("");
        opr = op1Text;
    }
}