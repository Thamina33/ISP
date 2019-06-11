package com.example.isp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signIn extends AppCompatActivity {
    EditText email,pass;
    TextView sign_up;
    Button login;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        sign_up = (TextView) findViewById(R.id.signup);
        login = (Button)findViewById(R.id.signinbtn);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(getApplicationContext() , SignUp.class);
                startActivity(o);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sign_mail , sign_pass;
                sign_mail = email.getText().toString();
                sign_pass = pass.getText().toString();


                if(!TextUtils.isEmpty(sign_mail)&& !TextUtils.isEmpty(sign_pass)){

                    mAuth.signInWithEmailAndPassword(sign_mail,sign_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                finish();

                            }

                            else{
                                String error = task.getException().getMessage() ;
                                Toast.makeText(getApplicationContext(),"Error"+error , Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

                else{

                    Toast.makeText(getApplicationContext() , "please Enter some value" , Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser muser  = FirebaseAuth.getInstance().getCurrentUser();
        if (muser != null){

            Intent i = new Intent(getApplicationContext() , MainActivity.class);
            startActivity(i);
        }
    }
}
