package com.example.medicalapp.data.room;

import android.content.Context;
import androidx.room.Room;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicalCardRepository {
    private final MedicalCardDAO medicalCardDAO;
    private final ExecutorService executorService;

    private static MedicalCardRepository instance;

    public static synchronized MedicalCardRepository getInstance(Context context) {
        if (instance == null) {
            AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "medical-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            instance = new MedicalCardRepository(db.medicalCardDAO());
        }
        return instance;
    }

    private MedicalCardRepository(MedicalCardDAO medicalCardDAO) {
        this.medicalCardDAO = medicalCardDAO;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void insertMedicalCard(MedicalCardEntity medicalCard) {
        executorService.execute(() -> medicalCardDAO.insert(medicalCard));
    }

    public List<MedicalCardEntity> getAllMedicalCards() {
        return medicalCardDAO.getAll();
    }

    // Додаємо метод для видалення всіх записів
    public void deleteAllCards() {
        executorService.execute(() -> medicalCardDAO.deleteAll());
    }
}

