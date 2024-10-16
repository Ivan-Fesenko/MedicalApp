package com.example.medicalapp.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicalCardDAO {

    // Метод отримання всіх записів
    @Query("SELECT * FROM medical_cards")
    List<MedicalCardEntity> getAll();  // Повертає всі медичні картки

    // Метод для вставки нового запису
    @Insert
    void insert(MedicalCardEntity medicalCard);  // Вставляє медичну картку

    // Метод для видалення всіх записів
    @Query("DELETE FROM medical_cards")
    void deleteAll();  // Очищає таблицю
}
