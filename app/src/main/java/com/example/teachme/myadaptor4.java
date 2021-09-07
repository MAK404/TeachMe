package com.example.teachme;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class myadaptor4 extends RecyclerView.Adapter<myadaptor4.myviewholder> {


    ArrayList<model4> datalist;
    public myadaptor4(ArrayList<model4> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow6,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.sname.setText(datalist.get(position).getStname());
        holder.smail.setText(datalist.get(position).getStmail());
        holder.smobile.setText(datalist.get(position).getStmobile());
        holder.scity.setText(datalist.get(position).getStcity());
        holder.showdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.showdetails.getContext(),waitingstudent.class);
                intent.putExtra("sname",datalist.get(position).getStname().toString());
                intent.putExtra("scity",datalist.get(position).getStcity().toString());
                intent.putExtra("spin",datalist.get(position).getStpin().toString());
                intent.putExtra("sstate",datalist.get(position).getStstate().toString());
                intent.putExtra("sclass",datalist.get(position).getStclass().toString());
                intent.putExtra("sage",datalist.get(position).getStage().toString());
                intent.putExtra("smode",datalist.get(position).getStmode().toString());
                intent.putExtra("smail",datalist.get(position).getStmail().toString());
                intent.putExtra("smobile",datalist.get(position).getStmobile().toString());
                intent.putExtra("sgender",datalist.get(position).getStgender().toString());
                intent.putExtra("saddress",datalist.get(position).getStaddress().toString());
                intent.putExtra("sid",datalist.get(position).getSid().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.showdetails.getContext().startActivity(intent);

//


            }
        });


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {  TextView sname,smobile,smail,scity,showdetails;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            sname=itemView.findViewById(R.id.sname);
            smail=itemView.findViewById(R.id.smail);
            scity=itemView.findViewById(R.id.scity);
            smobile=itemView.findViewById(R.id.smobile);
            showdetails=itemView.findViewById(R.id.showdetails);

        }
    }

}
