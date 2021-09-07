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

public class myadaptor5 extends RecyclerView.Adapter<myadaptor5.myviewholder> {


    ArrayList<model5> datalist;
    public myadaptor5(ArrayList<model5> datalist) {
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
        holder.smobile.setText(datalist.get(position).getTmobile());
        holder.scity.setText(datalist.get(position).getTcity());
        holder.showdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.showdetails.getContext(),waitingtutors.class);
                intent.putExtra("tname",datalist.get(position).getTname().toString());
                intent.putExtra("tcity",datalist.get(position).getTcity().toString());
                intent.putExtra("tpin",datalist.get(position).getTpin().toString());
                intent.putExtra("tstate",datalist.get(position).getTstate().toString());
                intent.putExtra("tskills",datalist.get(position).getTskills().toString());
                intent.putExtra("timing",datalist.get(position).getTiming().toString());
                intent.putExtra("tmode",datalist.get(position).getTmode().toString());
                intent.putExtra("taddress",datalist.get(position).getTaddress().toString());
                intent.putExtra("tage",datalist.get(position).getTage().toString());
                intent.putExtra("tgender",datalist.get(position).getTgender().toString());
                intent.putExtra("tmail",datalist.get(position).getTmail().toString());
                intent.putExtra("tmobile",datalist.get(position).getTmobile().toString());

                intent.putExtra("tid",datalist.get(position).getTid().toString());


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
