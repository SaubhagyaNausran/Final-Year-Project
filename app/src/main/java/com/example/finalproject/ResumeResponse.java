package com.example.finalproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResumeResponse {
    private String name;
    private List<String> achievements;
    private EducationalDetails educational_details;
    private Projects projects;
    private Responsibilities responsibilities;
    private List<String> skills;
    @SerializedName("trainings and certifications")
    private List<String> trainingsAndCertifications;
    private WorkExperience work_experience;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public EducationalDetails getEducationalDetails() {
        return educational_details;
    }

    public void setEducationalDetails(EducationalDetails educational_details) {
        this.educational_details = educational_details;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Responsibilities getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(Responsibilities responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getTrainingsAndCertifications() {
        return trainingsAndCertifications;
    }

    public void setTrainingsAndCertifications(List<String> trainingsAndCertifications) {
        this.trainingsAndCertifications = trainingsAndCertifications;
    }

    public WorkExperience getWorkExperience() {
        return work_experience;
    }

    public void setWorkExperience(WorkExperience work_experience) {
        this.work_experience = work_experience;
    }
}
