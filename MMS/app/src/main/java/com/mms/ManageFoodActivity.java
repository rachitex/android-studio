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
import android.widget.Toast;

public class ManageFoodActivity extends Activity implements View.OnClickListener {

    DatabaseHelper helper;
    EditText foodId,foodName,foodUtensils,foodDay;
    Spinner spinFoodType;
    String spinValue;
    Button btnAddNewFood, btnEditFood, btnDelFood, btnViewAllFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_food);

        helper=new DatabaseHelper(this);

        foodId=(EditText)findViewById(R.id.foodId);
        foodName=(EditText)findViewById(R.id.foodName);
        foodUtensils=(EditText)findViewById(R.id.foodUtensils);
        foodDay=(EditText)findViewById(R.id.foodDay);
        btnAddNewFood=(Button)findViewById(R.id.btnAddNewFood);
        btnEditFood=(Button)findViewById(R.id.btnEditFood);
        btnDelFood=(Button)findViewById(R.id.btnDelFood);
        btnViewAllFood=(Button)findViewById(R.id.btnViewAllFood);
        spinFoodType=(Spinner)findViewById(R.id.spinFoodType);

        spinFoodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                if(spinFoodType.getSelectedItem().equals("Breakfast")){
                    spinValue="Breakfast";
                }
                else if(spinFoodType.getSelectedItem().equals("Lunch")){
                    spinValue="Lunch";
                }
                else if(spinFoodType.getSelectedItem().equals("Dinner")){
                    spinValue="Dinner";
                }
            }

            public void onNothingSelected(AdapterView<?> arg0){
                spinValue=null;
            }
        });

        btnAddNewFood.setOnClickListener(this);
        btnEditFood.setOnClickListener(this);
        btnDelFood.setOnClickListener(this);
        btnViewAllFood.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == btnAddNewFood) {
            if (foodName.getText().toString().trim().length() == 0 ||
                    spinValue == null||
                    foodUtensils.getText().toString().trim().length() == 0 ||
                    foodDay.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            }


            boolean isInserted = helper.insertFoodValue(foodName.getText().toString(),
                    spinValue, foodUtensils.getText().toString(),foodDay.getText().toString());

            if (isInserted) {
                Toast.makeText(ManageFoodActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ManageFoodActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
            clearText();
        }

        if(v == btnViewAllFood){
            Cursor res=helper.getAllFoodValues();

            if(res.getCount()==0){
                showMessage("Error","No Values Found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()) {
                buffer.append("\nFoodId.: " + res.getString(0) + "\n");
                buffer.append("Food Name: " + res.getString(1) + "\n");
                buffer.append("Food Type: " + res.getString(2) + "\n");
                buffer.append("Food Utensils: " + res.getString(3) + "\n");
                buffer.append("Food Day: " + res.getString(4) + "\n");
            }
            showMessage("Data",buffer.toString());
        }

        if(v == btnEditFood){
            boolean isModified = helper.updateFoodValues(foodId.getText().toString(),
                    foodName.getText().toString(),
                    spinValue,
                    foodUtensils.getText().toString(),
                    foodDay.getText().toString());

            if(isModified){
                Toast.makeText(ManageFoodActivity.this,"Record Updated",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ManageFoodActivity.this,"Record Not Updated",Toast.LENGTH_LONG).show();
            }
        }

        if(v == btnDelFood){
            Integer deletedRows = helper.deleteFoodValues(foodId.getText().toString());

            if(deletedRows > 0){
                Toast.makeText(ManageFoodActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ManageFoodActivity.this,"Record Not Deleted",Toast.LENGTH_LONG).show();
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
    {   foodName.setText("");
        foodUtensils.setText("");
        foodName.requestFocus();
    }
}
