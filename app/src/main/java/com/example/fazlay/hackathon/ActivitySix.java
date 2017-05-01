package com.example.fazlay.hackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitySix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_six);

        TextView scl = (TextView) findViewById(R.id.sclName);
        ImageView imgView = (ImageView) findViewById(R.id.imageView7);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String value = extras.getString("schoolName");
            scl.setText(value);

            String idString =extras.getString("id");
            int id = Integer.valueOf(idString);

            if(id==1) imgView.setImageResource(R.drawable.scl1);
            else if (id==2) imgView.setImageResource(R.drawable.scl2);
            else if (id==3) imgView.setImageResource(R.drawable.scl3);
            else if (id==4) imgView.setImageResource(R.drawable.scl4);
            else if (id==5) imgView.setImageResource(R.drawable.scl5);
            else if (id==6) imgView.setImageResource(R.drawable.scl6);
        }
    }
}
