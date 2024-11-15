package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Transaction {

  public int getId();


  public LocalDateTime getDate();


  /**
   * Возвращает тип транзакции.
   * - Пополнение.
   * - Снятие.
   * - Перевод.
   *
   * @return Тип транзакции.
   */
  public String getType();


  public String getOldCurrency();


  public String getCurrentCurrency();


  public BigDecimal getAmount();


  public String getComment();

}
