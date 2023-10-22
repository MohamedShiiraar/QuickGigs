package com.example.quickgigs;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import model.Jobs;

public class JobListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Jobs> jobList ;

    public JobListAdapter(Context context, List<Jobs> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.jobNameView.setText(jobList.get(position).getJobTitle());
        holder.jobRateView.setText(jobList.get(position).getRatePerHour());
        holder.jobLocationView.setText(jobList.get(position).getAreaLocated());
        holder.imageView.setImageResource(R.drawable.logo);

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

}

