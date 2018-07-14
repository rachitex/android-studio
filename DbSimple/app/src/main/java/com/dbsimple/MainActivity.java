package com.dbsimple;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends Activity{

    EditText roll,name,marks;
    Button addBtn,modifyBtn,delBtn,viewBtn,viewAllBtn;
    DatabaseHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dh = new DatabaseHandler(this);

        roll=(EditText)findViewById(R.id.sRoll);
        name=(EditText)findViewById(R.id.sName);
        marks=(EditText)findViewById(R.id.sMarks);
        addBtn=(Button)findViewById(R.id.btnAdd);
        modifyBtn=(Button)findViewById(R.id.btnModify);
        delBtn=(Button)findViewById(R.id.btnDel);
        viewBtn=(Button)findViewById(R.id.btnView);
        viewAllBtn=(Button)findViewById(R.id.btnViewAll);

        //addBtn.setOnClickListener(this);
        //delBtn.setOnClickListener(this);
        //modifyBtn.setOnClickListener(this);
       // iewBtn.setOnClickListener(this);
        //viewAllBtn.setOnClickListener(this);
    }


    public void onAddBtnClick(View v){
        if(roll.getText().toString().trim().length()==0||
                name.getText().toString().trim().length()==0||
                marks.getText().toString().trim().length()==0)
        {
            showMessage("Error", "Please enter all values");
            return;
        }

        boolean isInserted = dh.insertValue(roll.getText().toString(),
                name.getText().toString(),
                marks.getText().toString());

        if(isInserted==true){
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
        }
        clearText();
    }

    public void onBtnViewAllClick(View v){
        Cursor res = dh.getAllValues();

        if(res.getCount()==0){
            showMessage("Error","No Values Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("\n Roll No.: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Marks" + res.getString(2) + "\n");
        }
        showMessage("Data",buffer.toString());
    }

    public void onBtnModifyClick(View v){
        boolean isModified = dh.updateValues(roll.getText().toString(),
                name.getText().toString(),
                marks.getText().toString());

        if(isModified == true){
            Toast.makeText(MainActivity.this,"Record Updated",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Record Not Updated",Toast.LENGTH_LONG).show();
        }
    }

    public void onBtnDelClick(View v){
        Integer deletedRows = dh.deleteValues(roll.getText().toString());

        if(deletedRows > 0){
            Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Record Not Deleted",Toast.LENGTH_LONG).show();
        }
    }

  /*  public void onClick(View view){
        // Adding a record
        if(view==addBtn)
        {
            // Checking empty fields
            if(roll.getText().toString().trim().length()==0||
                    name.getText().toString().trim().length()==0||
                    marks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            // Inserting record
            db.execSQL("INSERT INTO student VALUES('"+roll.getText()+"','"+name.getText()+
                    "','"+marks.getText()+"');");
            dh.insertValue(roll.getText().toString(),
                    name.getText().toString(),
                    marks.getText().toString());
            showMessage("Success", "Record added");
            clearText();
        }
            // Deleting a record
            if(view==delBtn)
            {
                // Checking empty roll number
                if(roll.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                // Searching roll number
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+roll.getText()+"'", null);
                if(c.moveToFirst())
                {
                    // Deleting record if found
                    db.execSQL("DELETE FROM student WHERE rollno='"+roll.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
    // Modifying a record
            if(view==modifyBtn)
            {
                // Checking empty roll number
                if(roll.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                // Searching roll number
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+roll.getText()+"'", null);
                if(c.moveToFirst())
                {
                    // Modifying record if found
                    db.execSQL("UPDATE student SET name='"+name.getText()+"',marks='"+marks.getText()+
                            "' WHERE rollno='"+roll.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
    // Viewing a record
            if(view==viewBtn)
            {
                // Checking empty roll number
                if(roll.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                // Searching roll number
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+roll.getText()+"'", null);
                if(c.moveToFirst())
                {
                    // Displaying record if found
                    name.setText(c.getString(1));
                    marks.setText(c.getString(2));
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
            }
            // Viewing all records
            if(view==viewAllBtn)
            {
                // Retrieving all records
                Cursor c=db.rawQuery("SELECT * FROM student", null);
                // Checking if no records found
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                // Appending records to a string buffer
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Rollno: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Marks: "+c.getString(2)+"\n\n");
                }
                // Displaying all records
                showMessage("Student Details", buffer.toString());
            }
     } */

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        roll.setText("");
        name.setText("");
        marks.setText("");
        roll.requestFocus();
    }
}
