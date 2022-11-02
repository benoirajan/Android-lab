package ben.app.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.im1).setOnClickListener(this);
        imageView = findViewById(R.id.im2);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(imageView.getVisibility() == View.VISIBLE)
            imageView.setVisibility(View.GONE);
        else
            imageView.setVisibility(View.VISIBLE);
    }
}