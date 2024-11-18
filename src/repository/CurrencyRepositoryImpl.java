package repository;

import model.Rate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    // Хранение курсов валют
    private final Map<String, Rate> currencyRates = new HashMap<>();

    /**
     * Возвращает курс валюты по ее коду.
     *
     * @param currencyCode Код валюты.
     * @return Объект Rate с курсом валюты, или null, если валюты нет.
     */
    @Override
    public Rate getRate(String currencyCode) {
        return currencyRates.get(currencyCode);
    }

    /**
     * Добавляет или обновляет курс валюты.
     *
     * @param currencyCode Код валюты.
     * @param course       Новый курс валюты.
     */
    @Override
    public void addOrUpdateRate(String currencyCode, BigDecimal course) {
        if (currencyCode == null || currencyCode.isEmpty() || course == null || course.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Неверный код валюты или курс");
        }

        // Обновляем или добавляем курс валюты
        Rate rate = currencyRates.get(currencyCode);
        if (rate != null) {
            rate.setCourse(course); // Обновляем существующий курс
        } else {
            rate = new Rate(currencyCode, course); // Создаем новый объект Rate
            currencyRates.put(currencyCode, rate); // Добавляем новый курс в коллекцию
        }
    }

    /**
     * Проверяет, существует ли валюта в списке курсов.
     *
     * @param currencyCode Код валюты.
     * @return true, если валюта существует, иначе false.
     */
    @Override
    public boolean currencyExists(String currencyCode) {
        return currencyRates.containsKey(currencyCode);
    }
}