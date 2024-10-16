package com.example.medicalapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.medicalapp.data.room.MedicalCardEntity;
import com.example.medicalapp.data.room.MedicalCardRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MedicalCardRepository repository;
    private MutableLiveData<List<MedicalCardEntity>> allMedicalCards = new MutableLiveData<>();

    // Конструктор для ін'єкції репозиторію
    public MainViewModel(MedicalCardRepository repository) {
        this.repository = repository;
        loadAllMedicalCards();
    }

    // Метод для додавання картки медичного запису
    public void insertMedicalCard(MedicalCardEntity card) {
        repository.insertMedicalCard(card);
    }

    // Метод для видалення всіх карток
    public void deleteAllCards() {
        repository.deleteAllCards();
    }

    // Метод для отримання всіх карток
    public LiveData<List<MedicalCardEntity>> getAllMedicalCards() {
        return allMedicalCards;
    }

    // Завантажує всі картки медичного запису і передає їх через LiveData
    private void loadAllMedicalCards() {
        repository.getAllMedicalCards(new MedicalCardRepository.ResultCallback<List<MedicalCardEntity>>() {
            @Override
            public void onResult(List<MedicalCardEntity> result) {
                // Використовуємо postValue() для роботи з фонової нитки
                allMedicalCards.postValue(result);
            }
        });
    }
}
