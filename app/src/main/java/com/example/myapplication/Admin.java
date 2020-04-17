package com.example.myapplication;

public class Admin {

    int id;
    String name,symp,symptom,medicare,selfcare,expertname;




    public Admin(int id, String name, String symp, String symptom, String medicare, String selfcare, String expertname) {
        this.id = id;
        this.name = name;
        this.symp = symp;
        this.symptom = symptom;
        this.medicare = medicare;
        this.selfcare = selfcare;
        this.expertname = expertname;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymp() {
        return symp;
    }

    public void setSymp(String symp) {
        this.symp = symp;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getMedicare() {
        return medicare;
    }

    public void setMedicare(String medicare) {
        this.medicare = medicare;
    }

    public String getSelfcare() {
        return selfcare;
    }

    public void setSelfcare(String selfcare) {
        this.selfcare = selfcare;
    }

    public String getExpertname() {
        return expertname;
    }

    public void setExpertname(String expertname) {
        this.expertname = expertname;
    }
}
