package repository;

import model.Rate;
import model.enums.CurrencyCode;
import repository.interfaces.CurrencyRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param amount       Курс
     * @return Возвращает объект {@code Rate}.
     */
    @Override
    public Rate addRate(CurrencyCode currencyCode, LocalDateTime time, BigDecimal amount) {
        // TODO addRate()
        return null;
    }


    /**
     * Возвращает список всех курсов валют.
     *
     * @return Список всех курсов валют.
     */
    @Override
    public List<Rate> getAllRates() {
        // TODO: getAllRates()
        return List.of();
    }


    /**
     * Возвращает список курсов валюты по коду.
     *
     * @param currencyCode Код валюты для которой нужно получить список всех курсов.
     * @return Список курсов валюты.
     */
    @Override
    public List<Rate> getCursesByRate(String currencyCode) {
        // TODO: getCursesByRate()
        return List.of();
    }


    /**
     * Возвращает актуальный курс для валюты. (Актуальный, значит с последней датой).
     *
     * @param currencyCode Код валюты, курс которой нужно получить.
     * @return Актуальный курс валюты.
     */
    @Override
    public BigDecimal getActualRate(CurrencyCode currencyCode) {
        // TODO getActualRate()
        return null;
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
        // TODO currencyExists()
        return false;
    }
}