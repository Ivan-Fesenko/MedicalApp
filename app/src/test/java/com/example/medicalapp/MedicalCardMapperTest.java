import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.medicalapp.data.mappers.MedicalCardMapper;
import com.example.medicalapp.data.room.MedicalCardEntity;
import com.example.medicalapp.MedicalCard;

public class MedicalCardMapperTest {

    private MedicalCardMapper medicalCardMapper;

    @Before
    public void setUp() {
        // Ініціалізуємо мапер перед кожним тестом
        medicalCardMapper = new MedicalCardMapper();
    }

    @Test
    public void testMapperInitialization() {
        // Перевірка на ініціалізацію мапера
        assertNotNull("MedicalCardMapper should be initialized", medicalCardMapper);
    }

    @Test(expected = NullPointerException.class)
    public void testMapperThrowsExceptionOnNullInput() {
        // Перевірка, чи викликається помилка при спробі мапінгу null
        medicalCardMapper.mapToDomainModel(null);
    }

    @Test
    public void testMapperCorrectMappingToDomainModel() {
        // Створюємо тестовий об'єкт MedicalCardEntity
        MedicalCardEntity entity = new MedicalCardEntity("Test Patient", 30, "Test Diagnosis");

        // Виконуємо мапінг до MedicalCard
        MedicalCard result = medicalCardMapper.mapToDomainModel(entity);

        // Перевіряємо, що мапінг виконано коректно
        assertNotNull("Result should not be null", result);
        assertEquals("Test Patient", result.getPatientName());
        assertEquals(30, result.getAge());
        assertEquals("Test Diagnosis", result.getDiagnosis());
    }

    @Test
    public void testMapperCorrectMappingToEntity() {
        // Створюємо тестовий об'єкт MedicalCard
        MedicalCard card = new MedicalCard("Test Patient", 30, "Test Diagnosis");

        // Виконуємо мапінг до MedicalCardEntity
        MedicalCardEntity result = medicalCardMapper.mapToEntity(card);

        // Перевіряємо, що мапінг виконано коректно
        assertNotNull("Result should not be null", result);
        assertEquals("Test Patient", result.patientName);
        assertEquals(30, result.age);
        assertEquals("Test Diagnosis", result.diagnosis);
    }
}
