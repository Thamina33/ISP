package com.example.isp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setUpProfile extends AppCompatActivity {


    EditText name , phonenumber , ipAdress ;
    Button submit ;
    String NAME  , PH , IP , UId  ;
    FirebaseAuth mauth ;
    DatabaseReference mref ;
    ProgressBar progressBar ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mauth = FirebaseAuth.getInstance() ;
        mref = FirebaseDatabase.getInstance().getReference("users");
        UId = mauth.getUid();



         name = (EditText)  findViewById(R.id.nameInRegister);
         phonenumber = (EditText)  findViewById(R.id.phoneInRegister);
         ipAdress = (EditText)  findViewById(R.id.ipInRegister);
         submit =findViewById(R.id.submitButton);
         progressBar = findViewById(R.id.progressBarsetUPprofile) ;

         progressBar.setVisibility(View.INVISIBLE);


         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressBar.setVisibility(View.VISIBLE);

                 NAME = name.getText().toString() ;
                 IP = ipAdress.getText().toString() ;
                 PH = phonenumber.getText().toString() ;


                 if (!TextUtils.isEmpty(NAME) || !TextUtils.isEmpty(IP) || !TextUtils.isEmpty(PH))
                 {


                     uploadDataToFireBase(NAME , IP  , PH , UId);


                 }
                 else {
                     progressBar.setVisibility(View.INVISIBLE);
                     Toast.makeText(getApplicationContext() , "Please Fill it Properly !!" , Toast.LENGTH_LONG).show(); ;
                 }




             }
         });






    }

    private void uploadDataToFireBase(String name, String ip, String ph, String uId) {

        modelForProfile upladmodel = new modelForProfile(name , ip , ph , uId) ;

        mref.child(uId).setValue(upladmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressBar.setVisibility(View.INVISIBLE);

                Intent o  = new Intent(getApplicationContext()   , MainActivity.class);
                startActivity(o);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext() , "Error : " + e.getMessage() , Toast.LENGTH_LONG).show();

            }
        }) ;




    }
}
