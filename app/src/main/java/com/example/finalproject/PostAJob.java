package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostAJob extends AppCompatActivity {

    TextInputLayout jobTitle,jobSkillSet,jobLocation,jobOpenings,contactDetails,jobOrganization;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ajob);

        jobTitle=findViewById(R.id.jobTitle);
        jobSkillSet=findViewById(R.id.jobSkillSet);
        jobLocation=findViewById(R.id.jobLocation);
        jobOpenings=findViewById(R.id.jobEntries);
        contactDetails=findViewById(R.id.contactDetails);
        jobOrganization=findViewById(R.id.jobOrganizationId);


        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("Jobs");
    }

    private Boolean jobTitle() {
        String title = jobTitle.getEditText().getText().toString();
        return title.length() != 0;
    }

    private Boolean jobSkillSet() {
        String skill = jobSkillSet.getEditText().getText().toString(); // Corrected reference to jobSkillSet
        return skill.length() != 0;
    }

    private Boolean jobLocation() {
        String location = jobLocation.getEditText().getText().toString();
        return location.length() != 0;
    }

    private Boolean jobOpenings() {
        String openings = jobOpenings.getEditText().getText().toString();
        return openings.length() != 0;
    }

    private Boolean contactDetails() {
        String conDetails = contactDetails.getEditText().getText().toString();
        return conDetails.length() != 0;
    }

    private Boolean jobOrganization() {
        String organization = jobOrganization.getEditText().getText().toString();
        return organization.length() != 0;
    }

    public void postJob(View view){

        if(!jobTitle() |!jobOpenings() | !jobLocation() | !jobSkillSet() | !contactDetails()| !jobOrganization())
        {
            return;
        }

        String title = jobTitle.getEditText().getText().toString();
        String openings = jobOpenings.getEditText().getText().toString();
        String location = jobLocation.getEditText().getText().toString();
        String skills = jobSkillSet.getEditText().getText().toString();
        String contactDet = contactDetails.getEditText().getText().toString();
        String organization=jobOrganization.getEditText().getText().toString();

        JobHelperClass jobHelperClass = new JobHelperClass(title, skills, organization, openings, contactDet, location);

        reference.child(contactDet).setValue(jobHelperClass);

        Toast.makeText(this,"Job has been posted successfully", Toast.LENGTH_SHORT).show();;

        Intent intent = new Intent(getApplicationContext(),AdminHomePage.class);
        startActivity(intent);
        finish();


    }
}