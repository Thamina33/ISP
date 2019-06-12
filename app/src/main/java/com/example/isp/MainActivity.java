package com.example.isp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {


    CardView Ftpicon ,paymentIcon, nottificaionIcon ;
    DatabaseReference mref , userRef ;
    FirebaseAuth mauth  ;
    String  uid , name ="null" ,ip ,State   ;
    TextView stat ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth = FirebaseAuth.getInstance();

        uid = mauth.getUid();

        userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        mref = FirebaseDatabase.getInstance().getReference("netState");


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .autoPromptLocation(true)
                .init();





        Ftpicon = findViewById(R.id.ftpicon) ;
        paymentIcon = findViewById(R.id.paymnetCARD) ;
        nottificaionIcon = findViewById(R.id.nottificaionIcon);
        stat = findViewById(R.id.net_check);

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
        loadDataFromFirebase() ;
    }

    private void loadDataFromFirebase() {

           ValueEventListener  post  = new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   modelForProfile model = dataSnapshot.getValue(modelForProfile.class) ;
                   name = model.getName();
                   ip = model.getIp();

                   Toast.makeText(getApplicationContext() , ""+ name , Toast.LENGTH_LONG).show();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           };
           userRef.addValueEventListener(post) ;

                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        modelForState state = dataSnapshot.getValue(modelForState.class);

                        State = state.getState() ;

                        stat.setText(State);
                        Toast.makeText(getApplicationContext() , ""+ State , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





    }




}
