package model;

import java.math.BigDecimal;

/**
 * Счет в объекте пользователя.
 */
public interface Account {

  public int getId();


  public String getTitle();


  public String setTitle(String title);


  public String getCurrency();


  public String setCurrency(String currency);


  public BigDecimal getBalance();


  /**
   * Добавляет сумму к счету.
   *
   * @param money
   */
  public void deposit(BigDecimal money);


  /**
   * Снимает сумму со счета.
   *
   * @param money
   */
  public void withdrawal(BigDecimal money);

  String getUserEmail();
}
