package com.mms;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginRectorActivity extends Activity implements View.OnClickListener{

    DatabaseHelper helper;
    EditText userRector,passwordRector;
    Button btnRectorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rector_login);

        helper=new DatabaseHelper(this);

        userRector=(EditText)findViewById(R.id.userRector);
        passwordRector=(EditText)findViewById(R.id.passwordRector);
        btnRectorLogin=(Button)findViewById(R.id.btnRectorLogin);

        btnRectorLogin.setOnClickListener(this);
        helper.close();
    }

    public void onClick(View v) {

        Cursor res = helper.getAllRectorValues();

        String uname = userRector.getText().toString();
        String pass = passwordRector.getText().toString();

        //String uname = res.getString(res.getColumnIndex(helper.USER_NAME));
        //String pass = res.getString(res.getColumnIndex(helper.USER_PASSWORD));

        while(res.moveToNext()){
            if(uname.equals(res.getString(1)) && pass.equals(res.getString(2))){
                Intent intent=new Intent(LoginRectorActivity.this, RectorHomeActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(LoginRectorActivity.this,"Password Doesn't Match",Toast.LENGTH_SHORT).show();
            }
        }

        /*while(res.moveToNext()){
            Toast.makeText(LoginRectorActivity.this,uname+" "+pass,Toast.LENGTH_LONG).show();
        }*/

    }
}
