package com.example.medicalapp.model;

import java.io.Serializable;

public class MedicalCard implements Serializable {
    private String patientName;
    private int age;
    private String diagnosis;

    public MedicalCard(String patientName, int age, String diagnosis) {
        this.patientName = patientName;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        return "MedicalCard{" +
                "patientName='" + patientName + '\'' +
                ", age=" + age +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
