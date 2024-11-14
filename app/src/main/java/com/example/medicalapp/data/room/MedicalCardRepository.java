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

    // Функція для асинхронного додавання медичної карти з зворотним викликом
    public void insertMedicalCard(MedicalCardEntity medicalCard, TaskCallback<Boolean> callback) {
        executorService.execute(() -> {
            try {
                medicalCardDAO.insert(medicalCard);
                callback.onSuccess(true); // Виклик при успішному виконанні
            } catch (Exception e) {
                callback.onFailure(e); // Виклик при виникненні помилки
            }
        });
    }

    // Функція для асинхронного отримання всіх медичних карт з обробкою помилок
    public void getAllMedicalCards(ResultCallback<List<MedicalCardEntity>> callback) {
        executorService.execute(() -> {
            try {
                List<MedicalCardEntity> result = medicalCardDAO.getAll();
                callback.onResult(result);  // Передаємо результат через callback
            } catch (Exception e) {
                e.printStackTrace();
                callback.onResult(null); // У разі помилки повертаємо null
            }
        });
    }

    // Функція для асинхронного видалення всіх медичних карт з зворотним викликом
    public void deleteAllCards(TaskCallback<Boolean> callback) {
        executorService.execute(() -> {
            try {
                medicalCardDAO.deleteAll();
                callback.onSuccess(true); // Виклик при успішному видаленні
            } catch (Exception e) {
                callback.onFailure(e); // Виклик при виникненні помилки
            }
        });
    }

    // Інтерфейс для передачі результатів асинхронних операцій
    public interface ResultCallback<T> {
        void onResult(T result);
    }

    // Інтерфейс для зворотного виклику успішності виконання операції
    public interface TaskCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }
}

