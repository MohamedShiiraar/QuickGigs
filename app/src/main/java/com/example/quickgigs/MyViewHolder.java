package com.example.quickgigs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView jobNameView,jobRateView,jobLocationView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        jobNameView = itemView.findViewById(R.id.jobName);
        jobRateView = itemView.findViewById(R.id.jobRate);
        jobLocationView = itemView.findViewById(R.id.jobLocation);
    }
}
