package com.example.fazlay.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void btn1Clicked(View v)
    {
        Intent i = new Intent(this, ActivitySeven.class);
        startActivity(i);
    }

    public void btn2Clicked(View v)
    {
        Intent i = new Intent(this, ActivityTwo.class);
        startActivity(i);
    }



}
