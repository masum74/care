package com.example.fazlay.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two);
    }

    public void school1Clicked(View v)
    {


        Intent i = new Intent(this, ActivityThree.class);
        TextView a =(TextView) findViewById(R.id.schoolName1);
        i.putExtra("schoolName", a.getText());
        i.putExtra("id", "1");
        startActivity(i);
    }

    public void school2Clicked(View v)
    {
        Intent i = new Intent(this, ActivityThree.class);
        TextView a =(TextView) findViewById(R.id.schoolName2);
        i.putExtra("schoolName",a.getText());
        i.putExtra("id", "2");
        startActivity(i);
    }

    public void school3Clicked(View v)
    {
        Intent i = new Intent(this, ActivityThree.class);
        TextView a =(TextView) findViewById(R.id.schoolName3);
        i.putExtra("schoolName",a.getText());
        i.putExtra("id", "3");
        startActivity(i);
    }

    public void school4Clicked(View v)
    {
        Intent i = new Intent(this, ActivityThree.class);
        TextView a =(TextView) findViewById(R.id.schoolName4);
        i.putExtra("schoolName",a.getText());
        i.putExtra("id", "4");
        startActivity(i);
    }

    public void school5Clicked(View v)
    {
        Intent i = new Intent(this, ActivityThree.class);
        TextView a =(TextView) findViewById(R.id.schoolName5);
        i.putExtra("schoolName",a.getText());
        i.putExtra("id", "5");
        startActivity(i);
    }

    public void school6Clicked(View v)
    {
        Intent i = new Intent(this, ActivityThree.class);
        TextView a =(TextView) findViewById(R.id.schoolName6);
        i.putExtra("schoolName",a.getText());
        i.putExtra("id", "6");
        startActivity(i);
    }

    public void switchOn(View v)
    {
        Intent i = new Intent(this, ActivityFive.class);
        startActivity(i);
    }
}
