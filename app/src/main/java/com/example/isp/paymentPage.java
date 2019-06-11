package com.example.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class paymentPage extends AppCompatActivity {


    Button paymentBtn ,  payumentListBtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);
            paymentBtn = findViewById(R.id.payBillBtn);

            payumentListBtn = findViewById(R.id.paymentListBtn);

            paymentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i  = new Intent(getApplicationContext() , makePayment.class);
                    startActivity(i);


                }
            });


            payumentListBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i  = new Intent(getApplicationContext() , paymentListViewer.class);
                    startActivity(i);

                }
            });


    }
}
