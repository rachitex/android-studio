package com.mvcmultiple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import java.util.List;

import static android.widget.Toast.makeText;
import static java.lang.Integer.parseInt;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText editRollno, editName, editMarks;
    Button btnAdd,btnDelete,btnModify,btnView;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editRollno=(EditText)findViewById(R.id.sRoll);
        editName=(EditText)findViewById(R.id.sName);
        editMarks=(EditText)findViewById(R.id.sMarks);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDel);
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById(R.id.btnView);


        btnAdd.setOnClickListener(this);

        db.close();

    }


    public void onClick(View v){

        MyTable mytbl = new MyTable();

        if(v == btnAdd){
            String rollno = editRollno.getText().toString();
            String name = editName.getText().toString();
            String marks=editMarks.getText().toString();

            mytbl.setRoll(parseInt(rollno));
            mytbl.setName(name);
            mytbl.setMarks(parseInt(marks));

            db.addItems(mytbl);

            Toast.makeText(this, "Hogya Kaam", Toast.LENGTH_LONG).show();
        }

        if(v==btnView) {
            List<MyTable> list = db.getAllItems();
            String log="";
            for (MyTable tbl : list) {
                log = "Roll: " + tbl.getRoll() + " ,Name: " + tbl.getName() + " ,Phone: " +
                        tbl.getMarks();
            }

            Builder builder = new Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Dekh le");
            builder.setMessage(log);
            builder.show();
        }
    }
}
