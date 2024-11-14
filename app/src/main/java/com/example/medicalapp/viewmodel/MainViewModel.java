package com.example.medicalapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.medicalapp.data.room.MedicalCardEntity;
import com.example.medicalapp.data.room.MedicalCardRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MedicalCardRepository repository;
    private final MutableLiveData<List<MedicalCardEntity>> allMedicalCards = new MutableLiveData<>();
    private final MutableLiveData<Boolean> insertionSuccess = new MutableLiveData<>();

    // Конструктор для ін'єкції репозиторію
    public MainViewModel(MedicalCardRepository repository) {
        this.repository = repository;
        loadAllMedicalCards();
    }

    // Метод для додавання картки медичного запису з обробкою зворотного виклику
    public void insertMedicalCard(MedicalCardEntity card) {
        repository.insertMedicalCard(card, new MedicalCardRepository.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                insertionSuccess.postValue(result); // Оновлюємо insertionSuccess при успішному додаванні
                loadAllMedicalCards(); // Оновлюємо список всіх карток після додавання
            }

            @Override
            public void onFailure(Exception e) {
                insertionSuccess.postValue(false); // Оновлюємо insertionSuccess при помилці
            }
        });
    }

    // Метод для видалення всіх карток з обробкою зворотного виклику
    public void deleteAllCards() {
        repository.deleteAllCards(new MedicalCardRepository.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                loadAllMedicalCards(); // Оновлюємо список після успішного видалення
            }

            @Override
            public void onFailure(Exception e) {
                // Можна обробити помилку, якщо потрібно, або відобразити повідомлення
            }
        });
    }

    // Метод для отримання всіх карток
    public LiveData<List<MedicalCardEntity>> getAllMedicalCards() {
        return allMedicalCards;
    }

    // Метод для отримання статусу успішності додавання
    public LiveData<Boolean> getInsertionSuccess() {
        return insertionSuccess;
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

    // Метод для реініціалізації списку карток медичного запису
    public void reinitializeMedicalCardList() {
        loadAllMedicalCards(); // Повторно завантажуємо всі картки медичних записів
    }
}

