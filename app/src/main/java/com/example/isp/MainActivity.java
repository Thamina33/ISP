package com.example.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    CardView Ftpicon ,paymentIcon ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ftpicon = findViewById(R.id.ftpicon) ;
        paymentIcon = findViewById(R.id.paymnetCARD) ;


        paymentIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , paymentPage.class);
                startActivity(i);

            }
        });


        Ftpicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext() , WebViewActivity.class);
                startActivity(i);


            }
        });

    }
}
