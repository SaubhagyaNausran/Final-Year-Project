package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminSignUp extends AppCompatActivity {

    TextInputLayout regName,regUsername,regEmail,regPhoneNo,regPassword,regOrganization;
    Button regBtn,adminLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        regName=findViewById(R.id.adminnameET);
        regUsername=findViewById(R.id.adminusernameET);
        regEmail=findViewById(R.id.adminemailET);
        regPhoneNo=findViewById(R.id.adminphoneNoET);
        regPassword=findViewById(R.id.adminpasswordET);
        regBtn=findViewById(R.id.goBtn);
        adminLoginBtn=findViewById(R.id.adminsigninBtn);
        regOrganization=findViewById(R.id.adminorganizationET);

        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("admin");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminSignIn.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private boolean validateName(){
        String val=regName.getEditText().getText().toString();

        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName(){
        String val=regUsername.getEditText().getText().toString();
        String noWhiteSpaces="\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        } else if(val.length() >=15){
            regUsername.setError("Username too long");
            return false;
        } else if(!val.matches(noWhiteSpaces)) {
            regUsername.setError("white space are not allowed");
            return false;

        } else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateOrg() {
        String org = regOrganization.getEditText().getText().toString();
        return org.length() != 0;
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(){

        if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUserName() | !validateOrg())
        {
            return;
        }
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String org  = regOrganization.getEditText().getText().toString();

        AdminHelperClass adminHelperClass = new AdminHelperClass(name,username,password,email,phoneNo,org);

        reference.child(phoneNo).setValue(adminHelperClass);

        Toast.makeText(this,"Your account has been created successfully", Toast.LENGTH_SHORT).show();;

        Intent intent = new Intent(getApplicationContext(),AdminSignIn.class);
        startActivity(intent);
        finish();



    }
}