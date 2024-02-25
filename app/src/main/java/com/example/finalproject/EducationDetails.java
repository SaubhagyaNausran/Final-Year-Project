package com.example.finalproject;

import java.util.List;

class EducationalDetails {
    private double cgpa;
    private List<String> college_details;
    private List<String> degrees;

    // Getters and Setters
    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public List<String> getCollegeDetails() {
        return college_details;
    }

    public void setCollegeDetails(List<String> college_details) {
        this.college_details = college_details;
    }

    public List<String> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<String> degrees) {
        this.degrees = degrees;
    }
}
