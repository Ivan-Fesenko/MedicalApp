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

    public void triggerCrash() {
        throw new RuntimeException("Test Crash: This is a test exception for Firebase Crashlytics");
    }

    public MainViewModel(MedicalCardRepository repository) {
        this.repository = repository;
        loadAllMedicalCards();
    }

    public void insertMedicalCard(MedicalCardEntity card) {
        repository.insertMedicalCard(card, new MedicalCardRepository.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                insertionSuccess.postValue(result);
                loadAllMedicalCards();
            }

            @Override
            public void onFailure(Exception e) {
                insertionSuccess.postValue(false);
            }
        });
    }

    public void deleteAllCards() {
        repository.deleteAllCards(new MedicalCardRepository.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                loadAllMedicalCards();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle error
            }
        });
    }

    public LiveData<List<MedicalCardEntity>> getAllMedicalCards() {
        return allMedicalCards;
    }

    public LiveData<Boolean> getInsertionSuccess() {
        return insertionSuccess;
    }

    private void loadAllMedicalCards() {
        repository.getAllMedicalCards(new MedicalCardRepository.ResultCallback<List<MedicalCardEntity>>() {
            @Override
            public void onResult(List<MedicalCardEntity> result) {
                allMedicalCards.postValue(result);
            }
        });
    }

    public void reinitializeMedicalCardList() {
        loadAllMedicalCards();
    }
}
