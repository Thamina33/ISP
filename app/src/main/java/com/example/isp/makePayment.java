package com.example.isp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class makePayment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner selectMonth ;
    ArrayAdapter<CharSequence> adapter ;
String month ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        selectMonth = findViewById(R.id.spinnerMonth);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.monthList, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        selectMonth.setAdapter(adapter);




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    month = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
