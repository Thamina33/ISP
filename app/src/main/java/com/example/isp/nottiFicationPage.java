package com.example.isp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notti_fication_page);

        getSupportActionBar().setTitle("Notification Page");

        mrecyclerview = findViewById(R.id.recyclerViewNottification) ;

        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerview.setHasFixedSize(true);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("notification");
        mRef.keepSynced(true);

        loadData(); 
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
