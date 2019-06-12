package com.example.isp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class nottiFicationPage extends AppCompatActivity {


    RecyclerView mrecyclerview ;

    LinearLayoutManager mLayoutManager; //for sorting
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter<modelForNottfication, viewholderForNottification> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<modelForNottfication> options ;
TextView mtex ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notti_fication_page);

        getSupportActionBar().setTitle("Notification Page");

        mrecyclerview = findViewById(R.id.recyclerViewNottification) ;
        mtex = findViewById(R.id.texviewOnNottification);


        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerview.setHasFixedSize(true);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("notifications");
        mRef.keepSynced(true);

        loadData();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(mLayoutManager.getItemCount()==0){


                    mtex.setVisibility(View.VISIBLE);
                }

            }
        }, 2000);



    }

    private  void loadData(){

        options = new FirebaseRecyclerOptions.Builder<modelForNottfication>().setQuery(mRef , modelForNottfication.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForNottfication, viewholderForNottification>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final   viewholderForNottification holder, final int position, @NonNull modelForNottfication model) {
                holder.setDataToView(getApplicationContext(), model.getTitle(), model.getDesc() , model.getDate());


            }

            @NonNull
            @Override
            public viewholderForNottification onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_nottfication, viewGroup, false);

                final viewholderForNottification viewHolder = new viewholderForNottification(itemVIew);
                //itemClicklistener
                viewHolder.setOnClickListener(new viewholderForNottification.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);

                        String Title = getItem(position).getTitle() ;
                        String body = getItem(position).getDesc();


                        new AwesomeInfoDialog(nottiFicationPage.this)
                                .setTitle(Title)
                                .setMessage(body)
                                .setColoredCircle(R.color.dialogInfoBackgroundColor)
                                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                .setCancelable(false)
                                .setPositiveButtonText(getString(R.string.dialog_yes_button))
                                .setPositiveButtonbackgroundColor(R.color.dialogInfoBackgroundColor)
                                .setPositiveButtonTextColor(R.color.white)

                                .setPositiveButtonClick(new Closure() {
                                    @Override
                                    public void exec() {
                                        //click
                                   new  AwesomeInfoDialog(nottiFicationPage.this).hide() ;

                                    }
                                })
                                .show();



                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });


                return viewHolder;
            }

    };

        mrecyclerview.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mrecyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
