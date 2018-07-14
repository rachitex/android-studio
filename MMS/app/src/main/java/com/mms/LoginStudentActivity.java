package com.mms;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginStudentActivity extends Activity implements View.OnClickListener{

    DatabaseHelper helper;
    EditText userStudent,passwordStudent;
    Button btnStudentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        helper=new DatabaseHelper(this);

        userStudent=(EditText)findViewById(R.id.userRector);
        passwordStudent=(EditText)findViewById(R.id.passwordRector);
        btnStudentLogin=(Button)findViewById(R.id.btnRectorLogin);

        btnStudentLogin.setOnClickListener(this);
        helper.close();
    }

    public void onClick(View v) {

        Cursor res = helper.getAllStudentValues();

        String uname = userStudent.getText().toString();
        String pass = passwordStudent.getText().toString();

        while(res.moveToNext()){
            if(uname.equals(res.getString(1))&&pass.equals(res.getString(2))){
                Intent intent=new Intent(LoginStudentActivity.this, StudentHomeActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(LoginStudentActivity.this,"Password Doesn't Match",Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
