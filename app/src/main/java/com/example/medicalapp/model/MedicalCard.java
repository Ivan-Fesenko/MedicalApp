package com.example.medicalapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicalCard implements Parcelable {
    private String patientName;
    private int age;
    private String diagnosis;

    public MedicalCard(String patientName, int age, String diagnosis) {
        this.patientName = patientName;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    protected MedicalCard(Parcel in) {
        patientName = in.readString();
        age = in.readInt();
        diagnosis = in.readString();
    }

    public static final Creator<MedicalCard> CREATOR = new Creator<MedicalCard>() {
        @Override
        public MedicalCard createFromParcel(Parcel in) {
            return new MedicalCard(in);
        }

        @Override
        public MedicalCard[] newArray(int size) {
            return new MedicalCard[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patientName);
        dest.writeInt(age);
        dest.writeString(diagnosis);
    }

    @Override
    public int describeContents() {
        return 0;
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

