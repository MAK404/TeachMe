package com.example.teachme;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadaptor6 extends RecyclerView.Adapter<myadaptor6.myviewholder>{


    ArrayList<model6> datalist;
    public myadaptor6(ArrayList<model6> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myadaptor6.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow6,parent,false);
        return new myadaptor6.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myadaptor6.myviewholder holder, int position) {

       holder.sname.setText(datalist.get(position).getSname());
       holder.smail.setText(datalist.get(position).getSmail());
       holder.smobile.setText(datalist.get(position).getSmobile());
        holder.scity.setText(datalist.get(position).getScity());
        holder.showdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.showdetails.getContext(),showdetails.class);
                intent.putExtra("taddress",datalist.get(position).getTaddress());
                intent.putExtra("tem",datalist.get(position).getTem());
                intent.putExtra("tland",datalist.get(position).getTland());
                intent.putExtra("tmid",datalist.get(position).getTmid());
                intent.putExtra("tname",datalist.get(position).getTname());
                intent.putExtra("tschedule",datalist.get(position).getTschedule());
                intent.putExtra("twno",datalist.get(position).getTwno());
                intent.putExtra("tmode",datalist.get(position).getTmode());
                intent.putExtra("tmno", datalist.get(position).getMno());


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.showdetails.getContext().startActivity(intent);
            }
        });

//        holder.nametxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                holder.nametxt.getContext().startActivity(intent);
//
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView sname,smobile,smail,scity,showdetails;


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
