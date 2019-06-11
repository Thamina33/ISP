package com.example.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {


    CardView Ftpicon ,paymentIcon, nottificaionIcon ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .autoPromptLocation(true)
                .init();


        Ftpicon = findViewById(R.id.ftpicon) ;
        paymentIcon = findViewById(R.id.paymnetCARD) ;
        nottificaionIcon = findViewById(R.id.nottificaionIcon);

        nottificaionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , nottiFicationPage.class);
                startActivity(i);


            }
        });

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
