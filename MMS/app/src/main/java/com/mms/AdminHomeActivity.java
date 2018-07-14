package com.mms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AdminHomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void onbtnUserManagementClick(View v){

        Intent i = new Intent(AdminHomeActivity.this, ManageUsersActivity.class);
        startActivity(i);
    }

    public void onbtnFoodManagementClick(View v){

        Intent i = new Intent(AdminHomeActivity.this, ManageFoodActivity.class);
        startActivity(i);
    }

    public void onbtnScheduleManagementClick(View v){

        Intent i = new Intent(AdminHomeActivity.this, ManageScheduleActivity.class);
        startActivity(i);
    }
}
