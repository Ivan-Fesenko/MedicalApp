package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Знаходимо елементи інтерфейсу
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextDiagnosis = findViewById(R.id.editTextDiagnosis);
        Button submitButton = findViewById(R.id.submitButton);

// Обробка натискання кнопки
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientName = editTextName.getText().toString();
                String ageString = editTextAge.getText().toString();
                String diagnosis = editTextDiagnosis.getText().toString();

                // Перевірка, чи всі поля заповнені
                if (patientName.isEmpty() || ageString.isEmpty() || diagnosis.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageString);
                MedicalCard medicalCard = new MedicalCard(patientName, age, diagnosis);

                // Переходимо до іншої активності
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("MedicalCard", medicalCard);
                startActivity(intent);
            }
        });
    }
}