package com.example.medicalapp;

import java.io.Serializable;

public class MedicalCard implements Serializable {
    private String patientName; // Ім'я пацієнта (рядок)
    private int age; // Вік пацієнта (ціле число)
    private String diagnosis; // Діагноз пацієнта (рядок)

    // Конструктор для створення об'єкта MedicalCard
    public MedicalCard(String patientName, int age, String diagnosis) {
        this.patientName = patientName;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    // Геттери та сеттери для властивостей
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
