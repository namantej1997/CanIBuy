package com.example.canibuy;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class displayPie extends AppCompatActivity {

    DatabaseReference db;
    FireBaseHelper helper;
    CustomAdapter adapter;
    ListView lv;
    Switch debitedBool;
    EditText itemTxt, ammountTxt, categoryTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaypie);

        //List View
        //Firebase
        lv = (ListView) findViewById(R.id.lv);

        //Init Firebase db
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FireBaseHelper(db);

        //Adapter
        adapter = new CustomAdapter(this, helper.retrieveLedger());
        lv.setAdapter(adapter);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                displayInputDialog();
//            }
//        });

        //Pie Chart
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



    //Display Ledger input
    public void displayInputDialog(View view) {
        Dialog d = new Dialog(this);
        d.setTitle("Manual expense Input");
        d.setContentView(R.layout.ledger_input);

        debitedBool = (Switch) d.findViewById(R.id.debitedBool);
        itemTxt = (EditText) d.findViewById(R.id.Item);
        ammountTxt = (EditText) d.findViewById(R.id.amount);
        categoryTxt = (EditText) d.findViewById(R.id.category);

        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);

        //Save
        saveBtn.setOnClickListener((v) -> {

            //Get data
            Boolean debited = !debitedBool.isChecked();
            String item = itemTxt.getText().toString();
            String amount = ammountTxt.getText().toString();
            String category = categoryTxt.getText().toString();

            if (item != null && amount != null && category != null){
                Ledger ledger = new Ledger(category, amount, item, debited);
                if(helper.saveLedger(ledger)){

                    debitedBool.setChecked(false);
                    itemTxt.setText("");
                    ammountTxt.setText("");
                    categoryTxt.setText("");

                    adapter = new CustomAdapter(displayPie.this,helper.retrieveLedger());
                    lv.setAdapter(adapter);
                }else{
                    Toast.makeText(displayPie.this,"Fields are empty", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
