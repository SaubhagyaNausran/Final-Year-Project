package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PdfActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button myButton;

    private ApiService apiService;
    String pdfName;

    private TextView score, scoreDes, scoreText;

    private RelativeLayout bannerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        progressBar = findViewById(R.id.progressBar);
        myButton = findViewById(R.id.myButton);
        bannerLayout = findViewById(R.id.BannerRL);
        pdfName = getIntent().getStringExtra("PDF_NAME");
        score = findViewById(R.id.scoreTv);
        scoreDes = findViewById(R.id.scoreDesTV);
        scoreText = findViewById(R.id.scoreText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-35-160-183-251.us-west-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build())
                .build();


        apiService = retrofit.create(ApiService.class);

        progressBar.setVisibility(View.VISIBLE); // Show the ProgressBar

        // Handler to hide the ProgressBar and show the button after 30 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar
                myButton.setVisibility(View.VISIBLE);
                myButton.setEnabled(true);
                bannerLayout.setVisibility(View.VISIBLE);
                scoreDes.setVisibility(View.VISIBLE);// Show the button

            }
        }, 5000); // 30000 milliseconds = 30 seconds

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                sendPostRequest();
            }
        });
    }

    private void sendPostRequest() {
        RequestBodyModel requestBody = new RequestBodyModel(pdfName);
        Log.d("HERE_PDF",pdfName);
        Call<ResumeResponse> call = apiService.postResume(requestBody);

        call.enqueue(new retrofit2.Callback<ResumeResponse>() {
            @Override
            public void onResponse(Call<ResumeResponse> call, retrofit2.Response<ResumeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    ResumeResponse resumeResponse = response.body();

                    // Now, send this resumeResponse as a new request to /tpscore endpoint
                    sendTpScoreRequest(resumeResponse);
                }
            }

            @Override
            public void onFailure(Call<ResumeResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("FAILURE", t.toString());
            }
        });
    }

    private void sendTpScoreRequest(ResumeResponse resumeResponse) {
        Call<Float> call = apiService.postTpScore(resumeResponse);
        call.enqueue(new retrofit2.Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, retrofit2.Response<Float> response) {
                // Handle the response from /tpscore endpoint
                if (response.isSuccessful()) {
                    Float tpscore = response.body();
                    Log.d("TpScore", "Received TpScore: " + tpscore);
                    score.setText(tpscore.toString());
                    score.setVisibility(View.VISIBLE);
                    scoreText.setVisibility(View.VISIBLE);
                    scoreDes.setVisibility(View.GONE);
                    myButton.setVisibility(View.GONE);// Make it visible
                } else {
                    Log.e("ERROR_RESPONSE",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                // Handle failure
                Log.e("ERROR",t.toString());
            }
        });
    }
}