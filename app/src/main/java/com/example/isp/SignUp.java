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

public class SignUp extends AppCompatActivity {

    EditText signup_email,signup_pass,con_pass;
    Button signup_btn;
    TextView signIn;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mauth = FirebaseAuth.getInstance();

        signup_email= (EditText)findViewById(R.id.email);
        signup_pass = (EditText)findViewById(R.id.pass);
        signup_btn = (Button)findViewById(R.id.signUp);
        con_pass = (EditText)findViewById(R.id.con_pass);
        signIn = (TextView)findViewById(R.id.con_pass);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(getApplicationContext() , signIn.class);
                startActivity(o);
                finish();
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email ,pass,confirm_pass;

                email =signup_email.getText().toString();
                pass = signup_pass.getText().toString();
                confirm_pass = con_pass.getText().toString();




                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirm_pass)){



                    if(pass.contains(confirm_pass)){

                    mauth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                                finish();
                            }

                            else{
                                String e = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"Error"+e, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Both Password Field must be same Value" , Toast.LENGTH_SHORT).show();
                    }

                }
                else{

                    Toast.makeText(getApplicationContext(),"Please Enter Some Value" , Toast.LENGTH_SHORT).show();


                }
                }


        });

    }
}