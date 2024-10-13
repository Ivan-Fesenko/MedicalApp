package com.example.medicalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.data.room.MedicalCardEntity;
import com.example.medicalapp.data.room.MedicalCardRepository;
import com.example.medicalapp.recycler.MedicalCardRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MedicalCardRepository repository;
    private RecyclerView recyclerView;
    private MedicalCardRecyclerViewAdapter adapter;
    private List<MedicalCardEntity> allCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізуємо репозиторій
        repository = MedicalCardRepository.getInstance(this);

        // Знаходимо елементи інтерфейсу
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextDiagnosis = findViewById(R.id.editTextDiagnosis);
        Button submitButton = findViewById(R.id.submitButton);
        Button clearButton = findViewById(R.id.clearButton);  // Додаємо кнопку очистки
        recyclerView = findViewById(R.id.recyclerView);

        // Ініціалізація RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allCards = new ArrayList<>();
        adapter = new MedicalCardRecyclerViewAdapter(allCards);
        recyclerView.setAdapter(adapter);
        loadAllCards();  // Завантаження карт на початку

        // Обробка натискання кнопки для додавання даних
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
                MedicalCardEntity medicalCard = new MedicalCardEntity(patientName, age, diagnosis);

                // Додаємо пацієнта до БД
                repository.insertMedicalCard(medicalCard);

                // Оновлюємо список в реальному часі
                allCards.add(0, medicalCard);  // Додаємо нову карту на початок списку
                adapter.notifyItemInserted(0);  // Оновлюємо адаптер тільки для нового елемента
                recyclerView.scrollToPosition(0);  // Прокручуємо до початку
            }
        });

        // Обробка натискання кнопки для очистки бази даних
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Очищуємо базу даних
                repository.deleteAllCards();
                allCards.clear();  // Очищаємо список у адаптері
                adapter.notifyDataSetChanged();  // Оновлюємо адаптер
                Toast.makeText(MainActivity.this, "База даних очищена", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Завантаження всіх карт з БД
    private void loadAllCards() {
        allCards.clear();  // Очищаємо список перед завантаженням нових карт
        allCards.addAll(repository.getAllMedicalCards());  // Завантажуємо всі карти
        Collections.reverse(allCards);  // Нові карти будуть зверху
        adapter.notifyDataSetChanged();  // Оновлюємо адаптер після завантаження
    }
}

