package repository.interfaces;

import model.Rate;
import model.enums.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RateRepository {

    /**
     * Хранилище курсов валюты.
     * <p>key - Код валюты</p>
     * <p>value - Список курсов (key - отметка времени, value - курс)</p>
     */
    Map<CurrencyCode, Map<LocalDateTime, Rate>> rates = new HashMap<>();


    /**
     * Добавляет новый курс валюты.
     *
     * @param currencyCode Код валюты.
     * @param time Время курса.
     * @param amount Курс
     * @return Возвращает объект {@code Rate}.
     */
    Rate addRate(CurrencyCode currencyCode, LocalDateTime time, BigDecimal amount);


    /**
     * Возвращает список всех курсов валют.
     *
     * @return Список всех курсов валют.
     */
    List<Rate> getAllRates();


    /**
     * Возвращает актуальный курс для валюты.
     * Актуальный, значит с последней датой.
     *
     * @param currencyCode Код валюты, курс которой нужно получить.
     * @return Актуальный курс валюты.
     */
    BigDecimal getActualRate(CurrencyCode currencyCode);

}
