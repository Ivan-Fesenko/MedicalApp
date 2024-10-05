package com.example.medicalapp.model;

public class MainActivityMedicalModel {

    private String patientName;
    private String ageString;
    private String diagnosis;
    private boolean isNavigating;
    private boolean showErrorMessage;

    // Конструктор
    public MainActivityMedicalModel(String patientName, String ageString, String diagnosis) {
        this.patientName = patientName;
        this.ageString = ageString;
        this.diagnosis = diagnosis;
        this.isNavigating = false;
        this.showErrorMessage = false;
    }

    // Геттери і сеттери для двостороннього прив'язування
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getAgeString() { return ageString; }
    public void setAgeString(String ageString) { this.ageString = ageString; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public boolean isNavigating() { return isNavigating; }
    public void setNavigating(boolean navigating) { isNavigating = navigating; }

    public boolean isShowErrorMessage() { return showErrorMessage; }
    public void setShowErrorMessage(boolean showErrorMessage) { this.showErrorMessage = showErrorMessage; }
}
