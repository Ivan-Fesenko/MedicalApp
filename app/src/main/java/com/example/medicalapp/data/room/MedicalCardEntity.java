package com.example.medicalapp.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medical_cards")
public class MedicalCardEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String patientName;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "diagnosis")
    public String diagnosis;

    public MedicalCardEntity(String patientName, int age, String diagnosis) {
        this.patientName = patientName;
        this.age = age;
        this.diagnosis = diagnosis;
    }
}

