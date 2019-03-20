package com.shashank.platform.schoolcollegeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategorySelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
    }

    public void staffclick(View view) {

        startActivity(new Intent(getApplicationContext(),Stafflogin.class));

    }

    public void studentclick(View view) {

        startActivity(new Intent(getApplicationContext(),Main3Activity.class));

    }
}
