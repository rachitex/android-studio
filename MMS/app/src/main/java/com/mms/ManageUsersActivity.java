package com.mms;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ManageUsersActivity extends Activity implements View.OnClickListener{

    DatabaseHelper helper;
    EditText userName,userPassword,userId;
    Spinner spinUserType;
    String spinValue;
    Button btnAddNewUser, btnEditUser, btnDelUser, btnViewAllUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        helper=new DatabaseHelper(this);

        userId=(EditText)findViewById(R.id.userId);
        userName=(EditText)findViewById(R.id.userName);
        userPassword=(EditText)findViewById(R.id.userPassword);
        btnAddNewUser=(Button)findViewById(R.id.btnAddNewUser);
        btnEditUser=(Button)findViewById(R.id.btnEditUser);
        btnDelUser=(Button)findViewById(R.id.btnDelUser);
        btnViewAllUser=(Button)findViewById(R.id.btnViewAllUser);
        spinUserType=(Spinner)findViewById(R.id.spinUserType);

        spinUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                if(spinUserType.getSelectedItem().equals("Rector")){
                    spinValue="Rector";
                }
                else if(spinUserType.getSelectedItem().equals("Student")){
                    spinValue="Student";
                }
            }

            public void onNothingSelected(AdapterView<?> arg0){
                spinValue=null;
            }
        });

        btnAddNewUser.setOnClickListener(this);
        btnEditUser.setOnClickListener(this);
        btnDelUser.setOnClickListener(this);
        btnViewAllUser.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == btnAddNewUser) {
            if (userName.getText().toString().trim().length() == 0 ||
                    userPassword.getText().toString().trim().length() == 0 ||
                    spinValue == null) {
                showMessage("Error", "Please enter all values");
                return;
            }


            boolean isInserted = helper.insertUserValue(userName.getText().toString(),
                    userPassword.getText().toString(), spinValue);

            if (isInserted) {
                Toast.makeText(ManageUsersActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ManageUsersActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
            clearText();
        }

        if(v == btnViewAllUser){
            Cursor res=helper.getAllUserValues();

            if(res.getCount()==0){
                showMessage("Error","No Values Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()) {
                buffer.append("\nUserId.: " + res.getString(0) + "\n");
                buffer.append("Name: " + res.getString(1) + "\n");
                buffer.append("Password: " + res.getString(2) + "\n");
                buffer.append("Type: " + res.getString(3) + "\n");
            }
            showMessage("Data",buffer.toString());
        }

        if(v == btnDelUser){
            Integer deletedRows = helper.deleteUserValues(userId.getText().toString());

            if(deletedRows > 0){
                Toast.makeText(ManageUsersActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ManageUsersActivity.this,"Record Not Deleted",Toast.LENGTH_LONG).show();
            }
        }

        if(v == btnEditUser){
            boolean isModified = helper.updateUserValues(userId.getText().toString(),
                    userName.getText().toString(),
                    userPassword.getText().toString(), spinValue);

            if(isModified){
                Toast.makeText(ManageUsersActivity.this,"Record Updated",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ManageUsersActivity.this,"Record Not Updated",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void showMessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        userName.setText("");
        userPassword.setText("");
        userName.requestFocus();
    }
}
