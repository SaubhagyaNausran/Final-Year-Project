package com.example.finalproject;

public class JobHelperClass {

    String title, skill,city,openings,contact,organization;

    JobHelperClass() {

    }

    JobHelperClass(String title,String skill,String organization,String openings,String contact,String city) {
        this.title = title;
        this.skill = skill;
        this.organization=organization;
        this.openings = openings;
        this.contact=contact;
        this.city = city;

    }

    public void setTitle (String title) {this.title = title;}
    public void setSkill (String skill) {this.skill = skill;}
    public void setCity (String city) {this.city = city;}
    public void setOpenings (String openings) {this.openings = openings;}
    public void setContact(String contact) {this.contact=contact;}
    public void setOrganization(String organization){this.organization=organization;}

    public String getTitle(){return title;}
    public String getSkill(){return skill;}
    public String getCity(){return city;}
    public String getOpenings(){return openings;}
    public String getContact(){return contact;}
    public String getOrganization(){return organization;}

}
