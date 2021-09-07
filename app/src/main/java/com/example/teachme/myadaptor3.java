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

public class myadaptor3 extends RecyclerView.Adapter<myadaptor3.myviewholder> {


    ArrayList<model3> datalist;
    public myadaptor3(ArrayList<model3> datalist) {
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
        holder.sname.setText(datalist.get(position).getTname());
        holder.smail.setText(datalist.get(position).getTmail());
        holder.smobile.setText(datalist.get(position).getTmode());
        holder.scity.setText(datalist.get(position).getTschedule());

        holder.showdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

Intent intent = new Intent(holder.showdetails.getContext(), accepteddetails.class);
intent.putExtra("taddress",datalist.get(position).getTaddress());
                intent.putExtra("taddress",datalist.get(position).getTaddress());
                intent.putExtra("tem",datalist.get(position).getTem());
                intent.putExtra("tland",datalist.get(position).getTland());
                intent.putExtra("tmid",datalist.get(position).getTmid());
                intent.putExtra("tname",datalist.get(position).getTname());
                intent.putExtra("tschedule",datalist.get(position).getTschedule());
                intent.putExtra("twno",datalist.get(position).getTwno());
                intent.putExtra("tmode",datalist.get(position).getTmode());
                intent.putExtra("tmno", datalist.get(position).getmno());


                intent.putExtra("tid",datalist.get(position).getTid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.showdetails.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {TextView sname,smobile,smail,scity,showdetails;


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
