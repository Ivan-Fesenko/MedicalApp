package com.example.medicalapp.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.medicalapp.R;
import com.example.medicalapp.model.MedicalCard;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Налаштування Toolbar з кнопкою "Назад"
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Встановлюємо власний заголовок
        getSupportActionBar().setTitle("Пацієнт");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Отримуємо об'єкт MedicalCard, переданий через Intent
        MedicalCard medicalCard = (MedicalCard) getIntent().getSerializableExtra("MedicalCard");

        // Знаходимо елементи інтерфейсу
        TextView nameView = findViewById(R.id.textViewName);
        TextView ageView = findViewById(R.id.textViewAge);
        TextView diagnosisView = findViewById(R.id.textViewDiagnosis);

        // Перевіряємо, чи об'єкт не є null
        if (medicalCard != null) {
            // Додаємо Toast для перевірки отримання даних
            Toast.makeText(this, "Отримані дані: " + medicalCard.toString(), Toast.LENGTH_SHORT).show();

            // Встановлюємо текст з описовими підписами до значень
            nameView.setText("Ім'я пацієнта: " + medicalCard.getPatientName());
            ageView.setText("Вік: " + String.valueOf(medicalCard.getAge()));
            diagnosisView.setText("Діагноз: " + medicalCard.getDiagnosis());
        }
    }
}
