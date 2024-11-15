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


  public void plusMoney(BigDecimal money);


  public void minusMoney(BigDecimal money);


  public String toString();

}
