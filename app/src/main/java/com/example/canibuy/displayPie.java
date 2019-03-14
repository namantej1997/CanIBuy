package com.example.canibuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class displayPie extends AppCompatActivity {


    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent display;
                    display = new Intent(getApplicationContext(), displayPie.class);
                    startActivity(display);
                    return true;
                case R.id.navigation_dashboard:
                    Intent scan;
                    scan = new Intent(getApplicationContext(), Scanactivity.class);
                    startActivity(scan);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_settting:
                    return true;
            }
            return false;
        }
    };

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


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
