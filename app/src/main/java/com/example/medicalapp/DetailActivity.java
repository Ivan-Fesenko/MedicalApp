package com.example.medicalapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Налаштування Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewAge = findViewById(R.id.textViewAge);
        TextView textViewDiagnosis = findViewById(R.id.textViewDiagnosis);

        // Отримання об'єкта MedicalCard з Intent
        MedicalCard medicalCard = (MedicalCard) getIntent().getSerializableExtra("MedicalCard");

        if (medicalCard != null) {
            textViewName.setText("Ім'я: " + medicalCard.getPatientName());
            textViewAge.setText("Вік: " + medicalCard.getAge());
            textViewDiagnosis.setText("Діагноз: " + medicalCard.getDiagnosis());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
