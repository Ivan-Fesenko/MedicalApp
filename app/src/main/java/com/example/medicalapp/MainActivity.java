package com.example.medicalapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.data.room.AppDatabase;
import com.example.medicalapp.data.room.MedicalCardEntity;
import com.example.medicalapp.data.room.MedicalCardRepository;
import com.example.medicalapp.recycler.MedicalCardRecyclerViewAdapter;
import com.example.medicalapp.viewmodel.MainViewModel;
import com.example.medicalapp.viewmodel.MainViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private MedicalCardRecyclerViewAdapter adapter;
    private List<MedicalCardEntity> allCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація DAO та репозиторію через AppDatabase
        AppDatabase database = AppDatabase.getInstance(this);
        MedicalCardRepository repository = new MedicalCardRepository(database.medicalCardDAO());
        MainViewModelFactory factory = new MainViewModelFactory(repository);
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        // Ініціалізація елементів інтерфейсу
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextDiagnosis = findViewById(R.id.editTextDiagnosis);
        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);

        // Ініціалізація RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allCards = new ArrayList<>();
        adapter = new MedicalCardRecyclerViewAdapter(allCards);
        recyclerView.setAdapter(adapter);
        loadAllCards();  // Завантаження карт на початку

        // Обробка натискання FAB для Crashlytics
        fab.setOnClickListener(view -> mainViewModel.triggerCrash());

        // Обробка натискання кнопки для додавання даних
        findViewById(R.id.submitButton).setOnClickListener(view -> {
            String patientName = editTextName.getText().toString();
            String ageString = editTextAge.getText().toString();
            String diagnosis = editTextDiagnosis.getText().toString();

            if (patientName.isEmpty() || ageString.isEmpty() || diagnosis.isEmpty()) {
                Toast.makeText(MainActivity.this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Вік має бути числом", Toast.LENGTH_SHORT).show();
                return;
            }

            MedicalCardEntity medicalCard = new MedicalCardEntity(patientName, age, diagnosis);

            mainViewModel.insertMedicalCard(medicalCard);
            allCards.add(0, medicalCard);
            adapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
        });

        // Обробка натискання кнопки для очистки бази даних
        findViewById(R.id.clearButton).setOnClickListener(view -> {
            mainViewModel.deleteAllCards();
            allCards.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "База даних очищена", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadAllCards() {
        mainViewModel.getAllMedicalCards().observe(this, cards -> {
            allCards.clear();
            if (cards != null) {
                allCards.addAll(cards);
                Collections.reverse(allCards);  // Нові карти будуть зверху
            }
            adapter.notifyDataSetChanged();
        });
    }
}
