package com.example.teachme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadaptor2 extends RecyclerView.Adapter<myadaptor2.myviewholder> implements Filterable {


    ArrayList<model2> datalist;
    ArrayList<model2> backup;
    Context context;
    public myadaptor2(ArrayList<model2> datalist, Context context) {
        this.datalist = datalist;
        this.context=context;
        backup=new ArrayList<>(datalist);
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow6,parent,false);
        return new myadaptor2.myviewholder(view);
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
                Intent intent = new Intent(holder.showdetails.getContext(),studentdetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("stname",datalist.get(position).getStname().toString());
                intent.putExtra("stcity",datalist.get(position).getStcity().toString());
                intent.putExtra("stpin",datalist.get(position).getStpin().toString());
                intent.putExtra("ststate",datalist.get(position).getStstate().toString());
                intent.putExtra("stclass",datalist.get(position).getStclass().toString());
                intent.putExtra("stmail",datalist.get(position).getStmail().toString());
                intent.putExtra("stmobile",datalist.get(position).getStmobile().toString());
                intent.putExtra("stmode",datalist.get(position).getStmode().toString());
                intent.putExtra("sid",datalist.get(position).getSid().toString());
                intent.putExtra("stage",datalist.get(position).getStage().toString());
                holder.showdetails.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence keyword)
        {
            ArrayList<model2> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(model2 obj : backup)
                {
                    if(obj.getStname().toString().toLowerCase().contains(keyword.toString().toLowerCase().trim()) ||
                       obj.getStcity().toString().toLowerCase().contains(keyword.toString().toLowerCase().trim()))
                          filtereddata.add(obj);
                }
            }

            FilterResults results=new FilterResults();
            results.values=filtereddata;
            results.count=filtereddata.size();
            return results;
        }

        @Override  // main UI thread
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            datalist.clear();
            datalist.addAll((ArrayList<model2>)results.values);
            notifyDataSetChanged();
        }
    };

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
