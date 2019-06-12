package com.example.isp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;


public class AdapterChat extends  RecyclerView.Adapter<AdapterChat.Myholder>  {


    private  static  final  int MSG_TYPE_LEFT = 0 ;
FirebaseUser firebaseUser ;
   String  link = "0" ;
    private  static  final  int MSG_TYPE_RIGHT = 1  ;
    Context context ;
    List <modelForChat> chatList ;
    String imageLink ;

    public AdapterChat(Context context, List<modelForChat> chatList) {
        this.context = context;
        this.chatList = chatList;

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_right_chat, parent, false);
            return new Myholder(view) ;

        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_left_chat, parent, false);
            return new Myholder(view) ;

        }




    }

    @Override
    public void onBindViewHolder(@NonNull final Myholder holder, int position) {

        //getting  Data
        String  msg = chatList.get(position).getMsg() ;
        String time = chatList.get(position).getTime() ;
        String senderName = chatList.get(position).getName() ;
        String uid =chatList.get(position).getUid() ;




        //setData

        holder.messageTv.setText(msg);
        holder.timeTv.setText(time);
        holder.senderNmae.setText(senderName);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }


    @Override
    public int getItemViewType(int position) {
        // getting the current  user ;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(chatList.get(position).getUid().equals(firebaseUser.getUid())){

            return  MSG_TYPE_RIGHT ;

        }
        else {
            return  MSG_TYPE_LEFT ;

        }


    }

    class  Myholder extends RecyclerView.ViewHolder{


        //views
        TextView messageTv , timeTv  , senderNmae;


        public Myholder(@NonNull View itemView) {
            super(itemView);


            //init View

            messageTv = itemView.findViewById(R.id.messageTV);
            timeTv = (TextView) itemView.findViewById(R.id.timeTV);
            senderNmae = (TextView) itemView.findViewById(R.id.text_message_name);


        }
    }


}
