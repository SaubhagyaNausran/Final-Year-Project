package com.example.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobsFragment extends Fragment {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private DatabaseReference databaseReference;
    List<JobHelperClass> jobList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_jobs, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = FirebaseDatabase.getInstance().getReference("Jobs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobList.clear(); // Clear the existing list to prevent duplicating items

                // Iterating over the snapshot to get each job item
                for (DataSnapshot jobSnapshot : snapshot.getChildren()) {
                    // Retrieve each field from the jobSnapshot
                    String city = jobSnapshot.child("city").getValue(String.class);
                    String contact = jobSnapshot.child("contact").getValue(String.class);
                    String openings = jobSnapshot.child("openings").getValue(String.class);
                    String organization = jobSnapshot.child("organization").getValue(String.class);
                    String skill = jobSnapshot.child("skill").getValue(String.class);
                    String title = jobSnapshot.child("title").getValue(String.class);

                    // Check if all the fields are retrieved before adding to the list
                    if (city != null && contact != null && openings != null && organization != null && skill != null && title != null) {
                        jobList.add(new JobHelperClass(title,skill,organization,openings,contact,city));
                    }
                }

                // Notifying the adapter about the data change
                jobAdapter.notifyDataSetChanged();
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        jobAdapter = new JobAdapter(getActivity(), jobList);
        recyclerView.setAdapter(jobAdapter);

        return v;
    }


    List<JobHelperClass> generateDummyData() {
        List<JobHelperClass> dummyData = new ArrayList<>();

        return dummyData;
    }
}