package com.example.medicalapp.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicalCardDAO {
    @Query("SELECT * FROM medical_cards")
    List<MedicalCardEntity> getAll();

    @Insert
    void insert(MedicalCardEntity medicalCard);

    @Query("DELETE FROM medical_cards")
    void deleteAll();  // Очищення бази даних
}
