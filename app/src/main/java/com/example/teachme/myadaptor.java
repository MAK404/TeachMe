package com.example.teachme;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class myadaptor extends RecyclerView.Adapter<myadaptor.myviewholder>implements Filterable {


    ArrayList<model> datalist;
    ArrayList<model> backup;
    Context context;
    public myadaptor(ArrayList<model> datalist, Context context) {
        this.datalist = datalist;
        this.context=context;
        backup=new ArrayList<>(datalist);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowt,parent,false);
    return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        double rating = datalist.get(position).getTrating();
        String rat= Double.toString(rating);

        holder.sname.setText(datalist.get(position).getTname());
        holder.smail.setText(datalist.get(position).getTmail());
        holder.smobile.setText(datalist.get(position).getTmobile());
        holder.scity.setText(datalist.get(position).getTcity());
        holder.srating.setText("Rating: "+rat+"/5");

        holder.showdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.showdetails.getContext(),tutordetails.class);
                intent.putExtra("tname",datalist.get(position).getTname().toString());
                intent.putExtra("tcity",datalist.get(position).getTcity().toString());
                intent.putExtra("tpin",datalist.get(position).getTpin().toString());
                intent.putExtra("tstate",datalist.get(position).getTstate().toString());
                intent.putExtra("tskills",datalist.get(position).getTskills().toString());
                intent.putExtra("timing",datalist.get(position).getTiming().toString());
                intent.putExtra("tmode",datalist.get(position).getTmode().toString());
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence keyword)
        {
            ArrayList<model> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(model obj : backup)
                {
                    if(obj.getTname().toString().toLowerCase().contains(keyword.toString().toLowerCase().trim())
                            || obj.getTcity().toString().toLowerCase().contains(keyword.toString().toLowerCase().trim())
                            || Double.toString(obj.getTrating()).toLowerCase().contains(keyword.toString().toLowerCase().trim() ))
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
            datalist.addAll((ArrayList<model>)results.values);
            notifyDataSetChanged();
        }
    };

    class myviewholder extends RecyclerView.ViewHolder
{TextView sname,smobile,smail,scity,showdetails,srating;


    public myviewholder(@NonNull View itemView) {
        super(itemView);
        sname=itemView.findViewById(R.id.sname);
        smail=itemView.findViewById(R.id.smail);
        scity=itemView.findViewById(R.id.scity);
        smobile=itemView.findViewById(R.id.smobile);
        showdetails=itemView.findViewById(R.id.showdetails);
        srating=itemView.findViewById(R.id.srating);

    }
}

}
