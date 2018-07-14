package com.mms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends Activity {

    Button btnAdmin, btnRector, btnStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAdmin = (Button)findViewById(R.id.btnAdmin);
        btnRector = (Button)findViewById(R.id.btnRector);
        btnStudent = (Button)findViewById(R.id.btnStudent);

    }

    public void onbtnAdminClick(View v){

        Intent i = new Intent(LoginActivity.this, LoginAdminActivity.class);
        startActivity(i);
    }

    public void onbtnRectorClick(View v){
        Intent i = new Intent(LoginActivity.this, LoginRectorActivity.class);
        startActivity(i);
    }

    public void onbtnStudentClick(View v){
        Intent i = new Intent(LoginActivity.this, LoginStudentActivity.class);
        startActivity(i);
    }
}
