package com.dbconnectivity;

import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    EditText editRollno, editName, editMarks;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShowInfo;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Initializing controls

        editRollno=(EditText)findViewById(R.id.sRoll);
        editName=(EditText)findViewById(R.id.sName);
        editMarks=(EditText)findViewById(R.id.sMarks);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDel);
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById(R.id.btnView);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        btnShowInfo=(Button)findViewById(R.id.btnShow);

// Registering event handlers

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);

// Creating database and table

        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    public void onClick(View view)
    {
// Adding a record
        if(view==btnAdd)
        {
            // Checking empty fields
            if(editRollno.getText().toString().trim().length()==0||
                    editName.getText().toString().trim().length()==0||
                    editMarks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            // Inserting record
            db.execSQL("INSERT INTO student VALUES('"+editRollno.getText()+"','"+editName.getText()+
                    "','"+editMarks.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
// Deleting a record
        if(view==btnDelete)
        {
            // Checking empty roll number
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            // Searching roll number
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                // Deleting record if found
                db.execSQL("DELETE FROM student WHERE rollno='"+editRollno.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
// Modifying a record
        if(view==btnModify)
        {
            // Checking empty roll number
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            // Searching roll number
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                // Modifying record if found
                db.execSQL("UPDATE student SET name='"+editName.getText()+"',marks='"+editMarks.getText()+
                        "' WHERE rollno='"+editRollno.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
// Viewing a record
        if(view==btnView)
        {
            // Checking empty roll number
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            // Searching roll number
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                // Displaying record if found
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
        }
// Viewing all records
        if(view==btnViewAll)
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
// Displaying info
        if(view==btnShowInfo)
        {
            showMessage("Student Management Application", "Developed By Rachit");
        }
    }

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
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }
}
