package com.example.isp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class viewhodlerForpaymentList extends RecyclerView.ViewHolder {

    View mView ;

    public viewhodlerForpaymentList(@NonNull View itemView) {




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
    public void setDataToView(Context ctx, String bill, String name, String ip, String month, String trxID, String date ,String status){
        //Views
        TextView mNameTv = mView.findViewById(R.id.nameOfRow);
        TextView mIpTv = mView.findViewById(R.id.ipOfRow);
        TextView mDate = mView.findViewById(R.id.dateOfRow);
        TextView mMontTv = mView.findViewById(R.id.monthOfRow);
        TextView mBillTv = mView.findViewById(R.id.billOfRow);
        TextView mstatusTv = mView.findViewById(R.id.stautsOfRow);
        TextView mTrxId = mView.findViewById(R.id.TrxidOfRow);



        //set data to views
        mNameTv.setText(""+ name);
        mIpTv.setText(""+ip);
        mDate.setText("তারিখ :"+date );
        mMontTv.setText(""+ month);
        mBillTv.setText(""+bill);
        mstatusTv.setText(""+status);
        mTrxId.setText("TrxId : "+trxID);


    }

    private viewhodlerForpaymentList.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewhodlerForpaymentList.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
