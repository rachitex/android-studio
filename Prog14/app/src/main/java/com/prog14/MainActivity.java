package com.prog14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button displaybtn;
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displaybtn = (Button)findViewById(R.id.button);
        iv1 = (ImageView) findViewById(R.id.imageView);

        displaybtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                iv1.setImageResource(R.drawable.blue_car_hancock_wallpaper);
            }
        });
    }
}
