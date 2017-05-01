package com.example.fazlay.hackathon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ActivityEight extends AppCompatActivity
{

    private LineChart lineChart;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_eight);

        lineChart = (LineChart) findViewById(R.id.line_chart);


        float yValues[]={55, 62, 68, 77, 89, 92 };
        String xValues[]={"first","second","three","four","five","six"};

        drawLineChart(yValues,xValues);
    }

    private void drawLineChart(float  yData[], String[] xData)
    {
        //lineChart.setDescription("Attendance Rate");
        ArrayList<Entry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new Entry(yData[i] , i));
        }

        for(int i = 0; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        LineDataSet lineDataSet = new LineDataSet(yEntrys,"Attendance Rate");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        LineData lineData = new LineData(lineDataSet);
        lineData.setValueTextSize(13f);
        lineData.setValueTextColor(Color.BLACK);

        lineChart.setData(lineData);

        lineChart.invalidate();

    }
}