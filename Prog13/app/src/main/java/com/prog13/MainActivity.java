package com.prog13;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements  OnClickListener{

    Button b;
    EditText eid,pw;
    Pattern p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = Pattern.compile(".+@.+\\.[a-z]+");
        b = (Button) findViewById(R.id.login_btn);
        eid = (EditText)findViewById(R.id.email);
        b.setOnClickListener(this);
        eid.setOnFocusChangeListener(new OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                eid = (EditText)findViewById(R.id.email);
                String email = eid.getText().toString();
                Matcher m = p.matcher(email);
                if(m.matches())

                {

                    b.setEnabled(true);

                }

                else

                {

                    Toast.makeText(getApplicationContext(), "Invalid EmailId", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        eid = (EditText)findViewById(R.id.email);
        pw = (EditText)findViewById(R.id.password);
        String email = eid.getText().toString();
        String pass = pw.getText().toString();

        if(!email.equals("") && !pass.equals(""))

        {

            Intent i = new Intent(getApplicationContext(),Welcome.class);

            i.putExtra("email", email);

            startActivity(i);

        }

        else

        {

            Toast.makeText(getApplicationContext(), "EmailId & Password must not be empty", Toast.LENGTH_LONG).show();

        }
    }
}
