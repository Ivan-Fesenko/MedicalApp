package com.example.medicalapp.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MedicalCardEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicalCardDAO medicalCardDAO();
}

