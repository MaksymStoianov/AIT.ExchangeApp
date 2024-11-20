package test.repository;

import model.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса CurrencyRepositoryImpl.
 * Эти тесты проверяют добавление, обновление, получение и валидацию курса валют.
 */
class CurrencyRepositoryImpl {

    private repository.CurrencyRepositoryImpl repository;

    /**
     * Инициализация репозитория перед каждым тестом.
     * Этот метод будет вызван перед каждым тестом для обеспечения чистого состояния.
     */
    @BeforeEach
    void setUp() {
        repository = new repository.CurrencyRepositoryImpl();
    }

    /**
     * Тестирование добавления новой валюты в репозиторий.
     * Проверяется, что валюта добавляется с правильным кодом и курсом.
     */
    @Test
    void testAddOrUpdateRate_NewCurrency() {
        String currencyCode = "EUR";
        BigDecimal rate = new BigDecimal("1.2");

        // Добавляем новую валюту с курсом
        repository.addOrUpdateRate(currencyCode, rate);

        // Проверяем, что валюта добавлена и курс совпадает
        Rate storedRate = repository.getRate(currencyCode);
        assertNotNull(storedRate, "Курс не был добавлен");
        assertEquals(currencyCode, storedRate.getCurrencyCode(), "Код валюты не совпадает");
        assertEquals(rate, storedRate.getCourse(), "Курс валюты не совпадает");
    }

    /**
     * Тестирование обновления курса для существующей валюты.
     * Проверяется, что курс обновляется при повторном добавлении.
     */
    @Test
    void testAddOrUpdateRate_ExistingCurrency() {
        String currencyCode = "USD";
        BigDecimal initialRate = new BigDecimal("1.0");
        BigDecimal updatedRate = new BigDecimal("1.1");

        // Добавляем валюту с начальным курсом
        repository.addOrUpdateRate(currencyCode, initialRate);
        // Обновляем курс
        repository.addOrUpdateRate(currencyCode, updatedRate);

        // Проверяем, что курс был обновлен
        Rate storedRate = repository.getRate(currencyCode);
        assertNotNull(storedRate, "Курс не был обновлен");
        assertEquals(updatedRate, storedRate.getCourse(), "Курс не был обновлен правильно");
    }

    /**
     * Тестирование получения курса для существующей валюты.
     * Проверяется, что курс возвращается правильно, если валюта существует.
     */
    @Test
    void testGetRate_CurrencyExists() {
        String currencyCode = "GBP";
        BigDecimal rate = new BigDecimal("0.75");

        // Добавляем валюту с курсом
        repository.addOrUpdateRate(currencyCode, rate);

        // Проверяем, что курс был правильно возвращен
        Rate storedRate = repository.getRate(currencyCode);
        assertNotNull(storedRate, "Курс для валюты не найден");
        assertEquals(rate, storedRate.getCourse(), "Курс для валюты неверный");
    }

    /**
     * Тестирование получения курса для несуществующей валюты.
     * Проверяется, что метод getRate возвращает null для несуществующей валюты.
     */
    @Test
    void testGetRate_CurrencyDoesNotExist() {
        String currencyCode = "JPY";

        // Проверяем, что курс для несуществующей валюты возвращает null
        Rate storedRate = repository.getRate(currencyCode);
        assertNull(storedRate, "Курс для несуществующей валюты должен быть null");
    }

    /**
     * Тестирование проверки существования валюты в репозитории.
     * Проверяется, что метод currencyExists возвращает true для существующей валюты.
     */
    @Test
    void testCurrencyExists_Exists() {
        String currencyCode = "AUD";
        BigDecimal rate = new BigDecimal("1.5");

        // Добавляем валюту с курсом
        repository.addOrUpdateRate(currencyCode, rate);

        // Проверяем, что валюта существует в репозитории
        boolean exists = repository.currencyExists(currencyCode);
        assertTrue(exists, "Валюта должна существовать в репозитории");
    }

    /**
     * Тестирование проверки отсутствия валюты в репозитории.
     * Проверяется, что метод currencyExists возвращает false для несуществующей валюты.
     */
    @Test
    void testCurrencyExists_NotExists() {
        String currencyCode = "INR";

        // Проверяем, что валюта не существует в репозитории
        boolean exists = repository.currencyExists(currencyCode);
        assertFalse(exists, "Валюта не должна существовать в репозитории");
    }

    /**
     * Тестирование обработки некорректных значений для кода валюты.
     * Проверяется, что при пустом или null коде валюты выбрасывается исключение.
     */
    @Test
    void testAddOrUpdateRate_InvalidCurrencyCode() {
        // Проверка на пустой код валюты
        assertThrows(IllegalArgumentException.class, () -> {
            repository.addOrUpdateRate("", new BigDecimal("1.0"));
        }, "Должно выбрасываться исключение при пустом коде валюты");

        // Проверка на null код валюты
        assertThrows(IllegalArgumentException.class, () -> {
            repository.addOrUpdateRate(null, new BigDecimal("1.0"));
        }, "Должно выбрасываться исключение при null коде валюты");
    }

    /**
     * Тестирование обработки некорректных значений для курса валюты.
     * Проверяется, что при некорректном курсе (null, 0 или отрицательный) выбрасывается исключение.
     */
    @Test
    void testAddOrUpdateRate_InvalidRate() {
        // Проверка на null курс
        assertThrows(IllegalArgumentException.class, () -> {
            repository.addOrUpdateRate("CAD", null);
        }, "Должно выбрасываться исключение при null курсе");

        // Проверка на курс равный 0
        assertThrows(IllegalArgumentException.class, () -> {
            repository.addOrUpdateRate("CAD", BigDecimal.ZERO);
        }, "Должно выбрасываться исключение при курсе равном 0");

        // Проверка на отрицательный курс
        assertThrows(IllegalArgumentException.class, () -> {
            repository.addOrUpdateRate("CAD", new BigDecimal("-1.0"));
        }, "Должно выбрасываться исключение при отрицательном курсе");
    }
}