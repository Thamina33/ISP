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

public class paymentListViewer extends AppCompatActivity {


    RecyclerView mrecyclerview ;

    LinearLayoutManager mLayoutManager; //for sorting
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter<modelForPaymentRow, viewhodlerForpaymentList> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<modelForPaymentRow> options ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list_viewer);

        mrecyclerview = findViewById(R.id.recylcerViewINpaymentList) ;

        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerview.setHasFixedSize(true);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("paymentList");
        mRef.keepSynced(true);

        loadData();

    }

    private  void loadData(){

        options = new FirebaseRecyclerOptions.Builder<modelForPaymentRow>().setQuery(mRef , modelForPaymentRow.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForPaymentRow, viewhodlerForpaymentList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final   viewhodlerForpaymentList holder, final int position, @NonNull modelForPaymentRow model) {
                holder.setDataToView(getApplicationContext(), model.getBill(), model.getName(), model.getIp() , model.getMonth() , model.getTrxID()
                , model.getDate() , model.getStatus());



            }

            @NonNull
            @Override
            public viewhodlerForpaymentList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_nottfication, viewGroup, false);

                final viewhodlerForpaymentList viewHolder = new viewhodlerForpaymentList(itemVIew);
                //itemClicklistener
                viewHolder.setOnClickListener(new viewhodlerForpaymentList.ClickListener() {
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
