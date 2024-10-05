package com.example.medicalapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import com.example.medicalapp.R;
import com.example.medicalapp.databinding.ActivityMainBinding;
import com.example.medicalapp.model.MainActivityMedicalModel;
import com.example.medicalapp.model.MedicalCard;
import com.example.medicalapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ініціалізація DataBinding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Підписка на зміни в LiveData
        viewModel.getMedicalModelLiveData().observe(this, new Observer<MainActivityMedicalModel>() {
            @Override
            public void onChanged(MainActivityMedicalModel medicalModel) {
                if (medicalModel.isNavigating()) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    // Створення об'єкта MedicalCard з даних MedicalModel
                    MedicalCard medicalCard = new MedicalCard(
                            medicalModel.getPatientName(),
                            Integer.parseInt(medicalModel.getAgeString()),
                            medicalModel.getDiagnosis()
                    );

                    // Передаємо MedicalCard через Intent
                    intent.putExtra("MedicalCard", medicalCard);
                    startActivity(intent);
                }
                if (medicalModel.isShowErrorMessage()) {
                    Toast.makeText(MainActivity.this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



