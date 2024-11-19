package model;

import java.math.BigDecimal;

public class Account {
    private int id;
    private String title;
    private String currency;
    private BigDecimal balance;
    private String userEmail;


    // Конструктор для создания нового счета с обязательными полями
    public Account(int id, String currency, BigDecimal balance, String userEmail) {
        this.id = id;
        this.title = null;
        this.currency = currency;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.userEmail = userEmail;
    }


    // Конструктор с дополнительным полем комментария
    public Account(int id, String currency, BigDecimal balance, String userEmail, String title) {
        this.id = id;
        this.title = title;
        this.currency = currency;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.userEmail = userEmail;
    }


    /**
     * Возвращает ID счета.
     */
    public int getId() {
        return id;
    }


    /**
     * Возвращает название счета.
     */
    public String getTitle() {
        return title;
    }


    /**
     * Устанавливает название счета.
     *
     * @param title новое название счета
     * @return новое название счета
     */
    public String setTitle(String title) {
        this.title = title;
        return this.title;
    }


    /**
     * Возвращает код валюты счета.
     */
    public String getCurrency() {
        return currency;
    }


    /**
     * Устанавливает код валюты счета.
     *
     * @param currency новый код валюты
     * @return новый код валюты
     */
    public String setCurrency(String currency) {
        this.currency = currency;
        return this.currency;
    }


    /**
     * Возвращает баланс счета.
     */
    public BigDecimal getBalance() {
        return balance;
    }


    /**
     * Обновляет баланс счета.
     *
     * @param balance новый баланс
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    /**
     * Возвращает email пользователя, связанный с этим счетом.
     */
    public String getUserEmail() {
        return userEmail;
    }


    /**
     * Устанавливает email пользователя, связанный с этим счетом.
     *
     * @param userEmail новый email пользователя
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}