package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AdminLogIn extends AppCompatActivity {

    private Button userButton;
    private Button adminButton;
    private Button adminSignUpButton;
    private Button userSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);
        userButton = findViewById(R.id.usersigninBtn);
        adminButton = findViewById(R.id.adminsigninBtn);
        adminSignUpButton = findViewById(R.id.adminsignupBtn);
        userSignUpButton = findViewById(R.id.usersignupBtn);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogIn.this,Login.class);
                startActivity(intent);
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogIn.this,AdminSignIn.class);
                startActivity(intent);
            }
        });

        adminSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogIn.this,adminSignUp.class);
                startActivity(intent);
            }
        });

        userSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogIn.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}