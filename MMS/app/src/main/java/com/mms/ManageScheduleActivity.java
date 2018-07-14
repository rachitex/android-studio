package com.mms;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ManageScheduleActivity extends Activity implements View.OnClickListener{

    DatabaseHelper helper;
    EditText scheduleId;
    Spinner scheduleTypeList,scheduleFoodList,scheduleFoodTypeList;
    String spinTypeValue,spinFoodValue,spinFoodTypeValue;
    Button btnAddNewSchedule, btnEditSchedule, btnDelSchedule, btnViewAllSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_schedule);

        helper=new DatabaseHelper(this);

        scheduleId=(EditText)findViewById(R.id.scheduleId);
        scheduleTypeList=(Spinner)findViewById(R.id.scheduleTypeList);
        scheduleFoodList=(Spinner)findViewById(R.id.scheduleFoodList);
        scheduleFoodList=(Spinner)findViewById(R.id.scheduleFoodTypeList);

        scheduleTypeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                if(scheduleTypeList.getSelectedItem().equals("Monday")){
                    spinTypeValue="Monday";
                }
                else if(scheduleTypeList.getSelectedItem().equals("Tuesday")){
                    spinTypeValue="Tuesday";
                }
                else if(scheduleTypeList.getSelectedItem().equals("Wednesday")){
                    spinTypeValue="Wednesday";
                }
                else if(scheduleTypeList.getSelectedItem().equals("Thursday")){
                    spinTypeValue="Thursday";
                }
                else if(scheduleTypeList.getSelectedItem().equals("Friday")){
                    spinTypeValue="Friday";
                }
                else if(scheduleTypeList.getSelectedItem().equals("Saturday")){
                    spinTypeValue="Saturday";
                }
                else if(scheduleTypeList.getSelectedItem().equals("Sunday")){
                    spinTypeValue="Sunday";
                }
            }

            public void onNothingSelected(AdapterView<?> arg0){
                spinTypeValue=null;
            }
        });

        btnAddNewSchedule.setOnClickListener(this);
        btnEditSchedule.setOnClickListener(this);
        btnDelSchedule.setOnClickListener(this);
        btnViewAllSchedule.setOnClickListener(this);
    }


    public void onClick(View v){
        if(v == btnViewAllSchedule){
            Cursor res=helper.getAllDayValues();

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
    }

    public void showMessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
