package com.mms;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAdminActivity extends Activity implements View.OnClickListener{

    DatabaseHelper helper;
    EditText userAdmin,passwordAdmin;
    Button btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        helper = new DatabaseHelper(this);

        userAdmin=(EditText)findViewById(R.id.userAdmin);
        passwordAdmin=(EditText)findViewById(R.id.passwordAdmin);
        btnAdminLogin=(Button)findViewById(R.id.btnAdminLogin);

        btnAdminLogin.setOnClickListener(this);
        helper.close();
    }


    public void onClick(View v){
        Cursor res = helper.getAllAdminValues();

        String uname = userAdmin.getText().toString();
        String pass = passwordAdmin.getText().toString();

        while(res.moveToNext()){
            if(uname.equals(res.getString(1))&&pass.equals(res.getString(2))){
                Intent intent=new Intent(LoginAdminActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(LoginAdminActivity.this,"Password Doesn't Match",Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}
