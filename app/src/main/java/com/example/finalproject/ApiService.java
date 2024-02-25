package com.example.finalproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/resume")
    Call<ResumeResponse> postResume(@Body RequestBodyModel body);

    @POST("/tpscore")
    Call<Float> postTpScore(@Body ResumeResponse resumeResponse);
}
