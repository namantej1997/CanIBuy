package com.example.canibuy;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class displayPie extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaypie);

        PieChart pieChart = findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        Description desc = new Description();
        desc.setText("Estimated Graph");
        desc.setTextSize(10f);

        pieChart.setDescription(desc);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(20f);

        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(40f, "Jan"));
        value.add(new PieEntry(20f, "Feb"));
        value.add(new PieEntry(35f, "Mar"));
        value.add(new PieEntry(5f, "April"));

        PieDataSet pieDataSet = new PieDataSet(value, "Months");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart.animateXY(1400, 1400);

        PieChart pieChart1 = findViewById(R.id.piechart1);
        pieChart1.setUsePercentValues(true);

        Description desc1 = new Description();
        desc1.setText("Estimated Graph");
        desc1.setTextSize(5f);

        pieChart1.setDescription(desc1);
        pieChart1.setHoleRadius(20f);
        pieChart1.setTransparentCircleRadius(20f);

        List<PieEntry> value1 = new ArrayList<>();
        value1.add(new PieEntry(40f, "Jan"));
        value1.add(new PieEntry(8f, "Feb"));
        value1.add(new PieEntry(2f, "Mar"));
        value1.add(new PieEntry(40f, "April"));

        PieDataSet pieDataSet1 = new PieDataSet(value, "Months");
        PieData pieData1 = new PieData(pieDataSet1);
        pieChart1.setData(pieData1);

        pieDataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart1.animateXY(1400, 1400);

    }
}
