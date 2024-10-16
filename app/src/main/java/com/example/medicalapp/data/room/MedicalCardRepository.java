package com.example.medicalapp.data.room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicalCardRepository {
    private final MedicalCardDAO medicalCardDAO;
    private final ExecutorService executorService;

    // Ініціалізуємо ExecutorService для фонових задач
    public MedicalCardRepository(MedicalCardDAO medicalCardDAO) {
        this.medicalCardDAO = medicalCardDAO;
        this.executorService = Executors.newSingleThreadExecutor(); // Використовуємо Executor для асинхронних операцій
    }

    // Функція для асинхронного додавання медичної карти
    public void insertMedicalCard(MedicalCardEntity medicalCard) {
        executorService.execute(() -> {
            medicalCardDAO.insert(medicalCard);
        });
    }

    // Функція для асинхронного отримання всіх медичних карт
    public void getAllMedicalCards(ResultCallback<List<MedicalCardEntity>> callback) {
        executorService.execute(() -> {
            List<MedicalCardEntity> result = medicalCardDAO.getAll();
            callback.onResult(result);  // Передаємо результат через callback
        });
    }

    // Функція для асинхронного видалення всіх медичних карт
    public void deleteAllCards() {
        executorService.execute(() -> {
            medicalCardDAO.deleteAll();
        });
    }

    // Інтерфейс для передачі результатів асинхронних операцій
    public interface ResultCallback<T> {
        void onResult(T result);
    }
}
