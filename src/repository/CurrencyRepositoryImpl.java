package repository;

import model.Account;
import model.Rate;
import model.enums.CurrencyCode;
import repository.interfaces.CurrencyRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Реализация репозитория для управления курсами валют.
 */
public class CurrencyRepositoryImpl implements CurrencyRepository {

    /**
     * Хранилище курсов валюты.
     * <p>key - Код валюты</p>
     * <p>value - Список курсов (key - отметка времени, value - курс)</p>
     */
    private final Map<CurrencyCode, Map<LocalDateTime, Rate>> rates;


    public CurrencyRepositoryImpl() {
        this.rates = new HashMap<>();
    }


    /**
     * Добавляет новый курс валюты.
     *
     * @param currencyCode Код валюты.
     * @param time         Время курса.
     * @param course       Курс
     * @return Возвращает объект {@code Rate}.
     */
    @Override
    public Rate addRate(String currencyCode, BigDecimal course, LocalDateTime time) {
        Rate rate = new Rate(currencyCode, course, time);
        rates.computeIfAbsent(CurrencyCode.valueOf(currencyCode), k -> new HashMap<>()).put(time, rate);
        return rate;
    }


    /**
     * Возвращает список всех курсов валют.
     *
     * @return Список всех курсов валют.
     */
    @Override
    public List<Rate> getAllRates() {
        return rates.values().stream()
                .flatMap(rateMap -> rateMap.values().stream())
                .collect(Collectors.toList());

    }


    /**
     * Возвращает список курсов валюты по коду.
     *
     * @param currencyCode Код валюты для которой нужно получить список всех курсов.
     * @return Список курсов валюты.
     */
    @Override
    public List<Rate> getCursesByRate(String currencyCode) {
        CurrencyCode code;
        try {
            code = CurrencyCode.valueOf(currencyCode);
        } catch (IllegalArgumentException e) {
            return List.of(); // Если код валюты неверный
        }

        Map<LocalDateTime, Rate> currencyRates = rates.get(code);
        if (currencyRates == null) {
            return List.of();
        }

        return new ArrayList<>(currencyRates.values());
    }


    /**
     * Возвращает актуальный курс для валюты. (Актуальный, значит с последней датой).
     *
     * @param currencyCode Код валюты, курс которой нужно получить.
     * @return Актуальный курс валюты.
     */
    @Override
    public BigDecimal getActualRate(CurrencyCode currencyCode) {
        Map<LocalDateTime, Rate> currencyRates = rates.get(currencyCode);
        if (currencyRates == null || currencyRates.isEmpty()) {
            return null; // Если курсы отсутствуют
        }

        Rate latestRate = null;
        LocalDateTime latestTime = null;

        for (Map.Entry<LocalDateTime, Rate> entry : currencyRates.entrySet()) {
            if (latestTime == null || entry.getKey().isAfter(latestTime)) {
                latestTime = entry.getKey();
                latestRate = entry.getValue();
            }
        }

        return latestRate.getCourse();

    }


    /**
     * Проверяет, существует ли валюта в списке курсов.
     *
     * @param currencyCode Код валюты.
     * @return true, если валюта существует, иначе false.
     */
    public boolean currencyExists(CurrencyCode currencyCode) {
        return rates.containsKey(currencyCode);
    }


    /**
     * Проверяет, существует ли валюта в списке курсов.
     *
     * @param currencyCode Код валюты.
     * @return true, если валюта существует, иначе false.
     */
    public boolean currencyExists(String currencyCode) {
        return rates.containsKey(currencyCode);
    }
}