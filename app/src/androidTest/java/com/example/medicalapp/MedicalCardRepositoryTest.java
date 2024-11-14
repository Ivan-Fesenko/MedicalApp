package com.example.medicalapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.medicalapp.data.room.MedicalCardRepository;
import com.example.medicalapp.data.room.MedicalCardEntity;
import com.example.medicalapp.data.room.MedicalCardDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MedicalCardRepositoryTest {

    private MedicalCardRepository repository;

    @Before
    public void setUp() {
        MedicalCardDAO mockDAO = Mockito.mock(MedicalCardDAO.class);
        repository = new MedicalCardRepository(mockDAO);
    }

    @Test
    public void testRepositoryInitialization() {
        assertNotNull("Repository should be initialized", repository);
    }

    @Test
    public void testRepositoryAddObject() {
        MedicalCardEntity medicalCard = new MedicalCardEntity("Test Patient", 30, "Test Diagnosis");
        repository.insertMedicalCard(medicalCard, new MedicalCardRepository.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                assertTrue("Insertion should succeed", result);
            }

            @Override
            public void onFailure(Exception e) {
                fail("Insertion should not fail");
            }
        });
    }

    @Test
    public void testRepositoryFetchObjects() {
        repository.getAllMedicalCards(result -> {
            assertNotNull("Result should not be null", result);
            assertFalse("Result should not be empty", result.isEmpty());
        });
    }

    @Test
    public void testRepositoryAddNullObject() {
        repository.insertMedicalCard(null, new MedicalCardRepository.TaskCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                fail("Callback should not succeed for null insertion");
            }

            @Override
            public void onFailure(Exception e) {
                assertNotNull("Exception should be thrown", e);
            }
        });
    }
}
