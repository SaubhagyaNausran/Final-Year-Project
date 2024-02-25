package com.example.finalproject;

public class RequestBodyModel {
    private String file_name;

    public RequestBodyModel(String file_name) {
        this.file_name = file_name;
    }

    // Getter and Setter
    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
