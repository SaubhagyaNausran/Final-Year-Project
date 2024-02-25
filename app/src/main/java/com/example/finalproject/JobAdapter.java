package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private List<JobHelperClass> jobList;
    private Context context;

    public JobAdapter(Context context, List<JobHelperClass> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jobs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobHelperClass job = jobList.get(position);

        holder.titleTextView.setText(job.getTitle());
        holder.skillsTextView.setText(job.getSkill());
        holder.organizationTextView.setText(job.getOrganization());
        holder.openingsTextView.setText(job.getOpenings());
        holder.contactTextView.setText(job.getContact());
        holder.cityTextView.setText(job.getCity());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView skillsTextView;
        TextView organizationTextView;
        TextView openingsTextView;
        TextView contactTextView;
        TextView cityTextView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            skillsTextView = itemView.findViewById(R.id.skillsTextView);
            organizationTextView = itemView.findViewById(R.id.organizationTextView);
            openingsTextView = itemView.findViewById(R.id.openingsTextView);
            contactTextView = itemView.findViewById(R.id.contactTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);

        }
    }
}
