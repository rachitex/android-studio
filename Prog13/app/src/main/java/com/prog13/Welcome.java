package com.prog13;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by moon on 30/12/16.
 */

public class Welcome extends Activity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        tv1 = (TextView)findViewById(R.id.welcome);

        String s = getIntent().getSerializableExtra("email").toString();

        tv1.setText("Welcome " + s);
    }
}
