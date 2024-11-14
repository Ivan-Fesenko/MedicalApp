package com.example.medicalapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.medicalapp.viewmodel.MainViewModel;
import com.example.medicalapp.data.room.MedicalCardRepository;
import com.example.medicalapp.data.room.MedicalCardEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MainViewModel viewModel;
    private MedicalCardRepository mockRepository;

    @Before
    public void setUp() {
        mockRepository = Mockito.mock(MedicalCardRepository.class);
        viewModel = new MainViewModel(mockRepository);
    }

    @Test
    public void testViewModelInitialization() {
        assertNotNull("ViewModel should be initialized", viewModel);
    }

    @Test
    public void testViewModelGetLiveDataForRecyclerView() {
        // Моделюємо поведінку репозиторію для повернення тестових даних
        List<MedicalCardEntity> mockData = new ArrayList<>();
        mockData.add(new MedicalCardEntity("Test Patient", 30, "Test Diagnosis"));

        Mockito.doAnswer(invocation -> {
            ((MedicalCardRepository.ResultCallback<List<MedicalCardEntity>>) invocation.getArguments()[0]).onResult(mockData);
            return null;
        }).when(mockRepository).getAllMedicalCards(Mockito.any());

        // Викликаємо метод завантаження даних у ViewModel
        viewModel.reinitializeMedicalCardList();

        // Перевіряємо, що LiveData містить значення
        viewModel.getAllMedicalCards().observeForever(allCards -> {
            assertNotNull("LiveData should not be null", allCards);
            assertEquals("LiveData should contain one entry", 1, allCards.size());
        });
    }

    @Test
    public void testViewModelGetLiveDataForViewState() {
        // Моделюємо успішне додавання картки
        Mockito.doAnswer(invocation -> {
            ((MedicalCardRepository.TaskCallback<Boolean>) invocation.getArguments()[1]).onSuccess(true);
            return null;
        }).when(mockRepository).insertMedicalCard(Mockito.any(), Mockito.any());

        viewModel.insertMedicalCard(new MedicalCardEntity("Test Patient", 30, "Test Diagnosis"));

        LiveData<Boolean> liveData = viewModel.getInsertionSuccess();
        assertNotNull("Insertion success LiveData should not be null", liveData.getValue());
        assertEquals("Insertion should be successful", true, liveData.getValue());
    }

    @Test
    public void testViewModelRecyclerViewAdapter() {
        List<MedicalCardEntity> cards = new ArrayList<>();
        cards.add(new MedicalCardEntity("Test", 25, "Test Diagnosis"));

        Mockito.doAnswer(invocation -> {
            ((MedicalCardRepository.ResultCallback<List<MedicalCardEntity>>) invocation.getArguments()[0]).onResult(cards);
            return null;
        }).when(mockRepository).getAllMedicalCards(Mockito.any());

        viewModel.reinitializeMedicalCardList();

        viewModel.getAllMedicalCards().observeForever(allCards -> {
            assertEquals("RecyclerView should display correct item count", cards.size(), allCards.size());
        });
    }

    @Test
    public void testViewModelReinitializeRecyclerViewList() {
        // Симуляція оновлених даних
        List<MedicalCardEntity> newMockData = new ArrayList<>();
        newMockData.add(new MedicalCardEntity("New Test Patient", 40, "New Test Diagnosis"));

        Mockito.doAnswer(invocation -> {
            ((MedicalCardRepository.ResultCallback<List<MedicalCardEntity>>) invocation.getArguments()[0]).onResult(newMockData);
            return null;
        }).when(mockRepository).getAllMedicalCards(Mockito.any());

        // Викликаємо метод реініціалізації
        viewModel.reinitializeMedicalCardList();

        // Перевіряємо, що LiveData оновилося новими даними
        viewModel.getAllMedicalCards().observeForever(cards -> {
            assertNotNull("List should not be null after reinitialization", cards);
            assertEquals("List should contain updated data", newMockData.size(), cards.size());
        });
    }
}
