package com.example.isp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeDialogBuilder;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class makePayment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner selectMonth ;
    ArrayAdapter<CharSequence> adapter ;
    String month  , bill , TRIX ,  uid , name ,ip , status = "Pending" ,DATE ,Time ;
    EditText billamount , trixID ;
    Button submit ;
    DatabaseReference mref , userRef  ;
    FirebaseAuth mauth ;
String NotificationId ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid() ;

        // getting notiification id l

        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        NotificationId = status.getSubscriptionStatus().getUserId();

        userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
        mref = FirebaseDatabase.getInstance().getReference("paymentList");

        selectMonth = findViewById(R.id.spinnerMonth);
        billamount  = findViewById(R.id.billEidt);
        trixID = (EditText)  findViewById(R.id.trxidEidt);
        submit = findViewById(R.id.submitButton);


        adapter = ArrayAdapter.createFromResource(this, R.array.monthList, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        selectMonth.setAdapter(adapter);

        loadDataFromFireBase();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String delegate = "hh:mm aaa";
                Time = String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime()));

                DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                Time = Time + " "+DATE ;


                bill = billamount.getText().toString() ;
              TRIX = trixID.getText().toString() ;


              if (!TextUtils.isEmpty(bill) || !TextUtils.isEmpty(TRIX) || !month.equals("মাস নির্বাচন করুন"))
              {


                        sendData(bill , TRIX , month) ;


              }
              else {

                  Toast.makeText(getApplicationContext() , " Fill It UP properly !! " , Toast.LENGTH_LONG).show();
              }

            }
        });




    }

    private void loadDataFromFireBase() {

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                modelForProfile post = dataSnapshot.getValue(modelForProfile.class);
              try {
                  name = post.getName() ;
                  ip = post.getIp();

              }
             catch (NullPointerException e ){

                  Toast.makeText(getApplicationContext() , " ERROR !! " , Toast.LENGTH_LONG).show();

             }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext() , "Error : " + databaseError.getMessage() , Toast.LENGTH_LONG).show(); ;
            }
        };
        userRef.addValueEventListener(postListener);

    }

    private void sendData(String bill, String trix, String month) {

        String postid = mref.push().getKey();


        modelForPaymentRow model = new modelForPaymentRow(postid  , uid , bill , name , ip , month , trix , Time , "Pending" ,NotificationId ) ;



        mref.child(postid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                // uploiaded
                sendNotification();
                TriggerDialogue();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(getApplicationContext() , "Error : " + e.getMessage() , Toast.LENGTH_LONG).show();

            }
        }) ;




    }


    private void TriggerDialogue() {


        new AwesomeSuccessDialog(this)
                .setTitle("পেমেন্সট টি ফল হয়েছে !!" )
                .setMessage("আমরা কিছু সময়ের মাঝে আপনাকে কনফার্ম করে দেওয়া হবে")
                .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(true)
                .setPositiveButtonText(getString(R.string.dialog_yes_button))
                .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        //click

                        new AwesomeSuccessDialog(getApplicationContext()).hide();
                        Intent o = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(o);
                        finish();

                    }

                })

            .show();



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    month = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void sendNotification() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //    String send_email;

                    //This is a Simple Logic to Send Notification different Device Programmatically....
                    //   send_email = "admin@gmail.com";


                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic MzdjN2Q1YjEtYjhiZC00YTg0LTg2M2QtMmQ2MGI3MzVmMjc5");
                        con.setRequestMethod("POST");


                        String strJsonBody = "{"
                                + "\"app_id\": \"398ea9d8-53e3-406a-b7da-f727bc37905c\","
                                + "\"included_segments\": [\"Admin\"],"
                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"New Payment Found!!\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }





}
