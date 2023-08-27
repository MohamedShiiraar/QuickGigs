package com.example.quickgigs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import model.Jobs;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {

    private List<Jobs> jobsList;

    public JobListAdapter(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list_item_card, parent, false);

        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Jobs job = jobsList.get(position);
        holder.jobTitle.setText(job.getJobTitle());
        holder.jobDescription.setText(job.toString());
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView jobTitle, jobDescription;

        public JobViewHolder(View view) {
            super(view);
            jobTitle = view.findViewById(R.id.edtJobTitle);
            jobDescription = view.findViewById(R.id.edtJobDescription);
        }
    }
}

