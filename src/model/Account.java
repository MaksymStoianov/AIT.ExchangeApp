package model;

import java.math.BigDecimal;

/**
 * Счет в объекте пользователя.
 */
public interface Account {

    int getId();


    String getTitle();


    String setTitle(String title);


    String getCurrency();


    String setCurrency(String currency);


    BigDecimal getBalance();


    String getUserEmail();

    String getComment();
}
