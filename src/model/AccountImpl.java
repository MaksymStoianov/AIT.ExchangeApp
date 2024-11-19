package model;

import java.math.BigDecimal;

public class AccountImpl implements Account {
    private int id;
    private String title;
    private String currency;
    private BigDecimal balance;
    private String userEmail;
    private String comment;

    // Конструктор для создания нового счета с обязательными полями
    public AccountImpl(int id, String title, String currency, BigDecimal balance, String userEmail) {
        this.id = id;
        this.title = title;
        this.currency = currency;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.userEmail = userEmail;
        this.comment = "";
    }

    // Конструктор с дополнительным полем комментария
    public AccountImpl(int id, String title, String currency, BigDecimal balance, String userEmail, String comment) {
        this.id = id;
        this.title = title;
        this.currency = currency;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.userEmail = userEmail;
        this.comment = comment;
    }

    /**
     * Возвращает ID счета.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Возвращает название счета.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название счета.
     *
     * @param title новое название счета
     * @return новое название счета
     */
    @Override
    public String setTitle(String title) {
        this.title = title;
        return this.title;
    }

    /**
     * Возвращает код валюты счета.
     */
    @Override
    public String getCurrency() {
        return currency;
    }

    /**
     * Устанавливает код валюты счета.
     *
     * @param currency новый код валюты
     * @return новый код валюты
     */
    @Override
    public String setCurrency(String currency) {
        this.currency = currency;
        return this.currency;
    }

    /**
     * Возвращает баланс счета.
     */
    @Override
    public BigDecimal getBalance() {
        return balance;
    }

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

    /**
     * Возвращает комментарий, связанный с этим счетом.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Устанавливает комментарий для этого счета.
     *
     * @param comment новый комментарий
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}