package com.mms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class RectorHomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rector_home);
    }

    public void onbtnViewScheduleRectorClick(View v){

        Intent i = new Intent(RectorHomeActivity.this, LoginAdminActivity.class);
        startActivity(i);
    }

    public void onbtnViewTotalUtensilsClick(View v){

        Intent i = new Intent(RectorHomeActivity.this, LoginAdminActivity.class);
        startActivity(i);
    }

    public void onbtnViewTotalStudentsClick(View v){

        Intent i = new Intent(RectorHomeActivity.this, LoginAdminActivity.class);
        startActivity(i);
    }
}
