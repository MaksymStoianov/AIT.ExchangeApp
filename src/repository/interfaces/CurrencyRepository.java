package repository.interfaces;

import model.Rate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface CurrencyRepository {

  /**
   * Хранит курсы валют. key - String - код валюты. value - Rate - Курсы валюты.
   */
  Map<String, Rate> currencyRates = new HashMap();


  /**
   * Возвращает курс валюты по коду.
   *
   * @param currencyCode
   * @return
   */
  Rate getRate(String currencyCode);


  /**
   * Добавляет или обновляет курс валюты.
   *
   * @param currencyCode
   * @param course
   */
  void addOrUpdateRate(String currencyCode, BigDecimal course);


  /**
   * Проверяет, существует ли валюта.
   *
   * @param currencyCode
   * @return
   */
  boolean currencyExists(String currencyCode);

}
