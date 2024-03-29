package com.example.isp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class viewholderForNottification extends RecyclerView.ViewHolder {
    View  mView ;

    public viewholderForNottification(@NonNull View itemView) {




        super(itemView);

        mView = itemView ;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });


    }

    //set details to recycler view row
    public void setDataToView(Context ctx, String title, String description ,String date){
        //Views
        TextView mTitleTv = mView.findViewById(R.id.title_row);
        TextView mDetailTv = mView.findViewById(R.id.desc_row);
        TextView mdate = mView.findViewById(R.id.time_row);


        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(description);
        mdate.setText("তারিখ :"+date );




    }

    private viewholderForNottification.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewholderForNottification.ClickListener clickListener){
        mClickListener = clickListener;
    }


}
