package com.example.medicalapp.viewmodel;

import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.medicalapp.model.MainActivityMedicalModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<MainActivityMedicalModel> medicalModelLiveData = new MutableLiveData<>(new MainActivityMedicalModel("", "", ""));

    // Отримання LiveData
    public MutableLiveData<MainActivityMedicalModel> getMedicalModelLiveData() {
        return medicalModelLiveData;
    }

    // Метод для кнопки "Надіслати"
    public void onNextButtonClicked(View v) {
        MainActivityMedicalModel medicalModel = medicalModelLiveData.getValue();

        if (medicalModel != null) {
            if (medicalModel.getPatientName().isEmpty() || medicalModel.getAgeString().isEmpty() || medicalModel.getDiagnosis().isEmpty()) {
                // Показати повідомлення про помилку
                Toast.makeText(v.getContext(), "Заповніть всі поля!", Toast.LENGTH_SHORT).show();
                medicalModel.setShowErrorMessage(true);
                medicalModelLiveData.setValue(medicalModel);
            } else {
                // Показати повідомлення про успіх
                Toast.makeText(v.getContext(), "Дані надіслані!", Toast.LENGTH_SHORT).show();
                medicalModel.setNavigating(true);
                medicalModelLiveData.setValue(medicalModel);
            }
        }
    }
}




