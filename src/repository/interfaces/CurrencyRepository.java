package repository.interfaces;

import model.Rate;
import model.enums.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CurrencyRepository {

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
   * @param course Курс
   * @return Возвращает объект {@code Rate}.
   */
  Rate addRate(String currencyCode, BigDecimal course, LocalDateTime time);


  /**
   * Возвращает список всех курсов валют.
   *
   * @return Список всех курсов валют.
   */
  List<Rate> getAllRates();


  /**
   * Возвращает список курсов валюты по коду.
   *
   * @param currencyCode Код валюты для которой нужно получить список всех курсов.
   * @return Список курсов валюты.
   */
  List<Rate> getCursesByRate(String currencyCode);


  /**
   * Возвращает актуальный курс для валюты.
   * (Актуальный, значит с последней датой).
   *
   * @param currencyCode Код валюты, курс которой нужно получить.
   * @return Актуальный курс валюты.
   */
  BigDecimal getActualRate(CurrencyCode currencyCode);


  /**
   * Проверяет, существует ли валюта.
   *
   * @param currencyCode
   * @return
   */
  boolean currencyExists(String currencyCode);

}
