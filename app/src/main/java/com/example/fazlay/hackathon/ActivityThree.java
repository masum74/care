package com.example.fazlay.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityThree extends AppCompatActivity {

    ImageView imgView;
    String value="";
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_three);

        TextView scName = (TextView) findViewById(R.id.schoolName);
        imgView = (ImageView) findViewById(R.id.imgView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("schoolName");
            //The key argument here must match that used in the other activity
            scName.setText(value);

            String idString =extras.getString("id");
            id = Integer.valueOf(idString);
            System.out.println("********************************************"+id);

            if(id==1) imgView.setImageResource(R.drawable.img1);
            else if (id==2) imgView.setImageResource(R.drawable.img2);
            else if (id==3) imgView.setImageResource(R.drawable.img3);
            else if (id==4) imgView.setImageResource(R.drawable.img4);
            else if (id==5) imgView.setImageResource(R.drawable.img5);
            else if (id==6) imgView.setImageResource(R.drawable.img6);

        }


    }

    public void btnImagePressed(View v)
    {
        Intent i = new Intent(this, ActivityFour.class);
        startActivity(i);

    }

    public void btnReportPressed(View v)
    {
        Intent i = new Intent(this, ActivityEight.class);
        startActivity(i);
    }

    public void btnCommentPressed(View v)
    {
        Intent i = new Intent(this, ActivitySix.class);
        i.putExtra("schoolName",value);
        String s = Integer.toString(id);
        i.putExtra("id",s);
        startActivity(i);
    }

}
