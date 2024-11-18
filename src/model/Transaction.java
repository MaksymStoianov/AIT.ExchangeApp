package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Transaction {

  int getId();


  LocalDateTime getDate();


  /**
   * Возвращает тип транзакции. - Пополнение. - Снятие. - Перевод.
   *
   * @return Тип транзакции.
   */
  TransactionType getType();


  /**
   * Возвращает валюту счета "из".
   *
   * @return
   */
  public String getCurrencyFrom();


  /**
   * Возвращает валюту счета "в".
   *
   * @return
   */
  String getCurrencyTo();


  /**
   * Возвращает сумму счета "на".
   *
   * @return
   */
  BigDecimal getAmountFrom();


  /**
   * Возвращает сумму счета "в".
   *
   * @return
   */
  BigDecimal getAmountTo();


  /**
   * Возвращает комментарий к транзакции.
   *
   * @return
   */
  String getComment();

}
