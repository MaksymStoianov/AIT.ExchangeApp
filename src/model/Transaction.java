package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final int id;
    private final LocalDateTime date;
    private final TransactionType type;
    private final String currencyFrom;
    private final String currencyTo;
    private final BigDecimal amountFrom;
    private final BigDecimal amountTo;
    private final String comment;
    private final int accountId;
    private final String userEmailFrom;
    private final String userEmailTo;


    // Конструктор для инициализации всех полей
    public Transaction(int id, LocalDateTime date, TransactionType type, String currencyFrom,
                       String currencyTo, BigDecimal amountFrom, BigDecimal amountTo,
                       String comment, int accountId, String userEmailFrom, String userEmailTo) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.comment = comment;
        this.accountId = accountId;
        this.userEmailFrom = userEmailFrom;
        this.userEmailTo = userEmailTo;
    }


    /**
     * Возвращает ID транзакции.
     */
    public int getId() {
        return id;
    }


    /**
     * Возвращает дату транзакции.
     */
    public LocalDateTime getDate() {
        return date;
    }


    /**
     * Возвращает тип транзакции: Пополнение, Снятие, Перевод.
     */
    public TransactionType getType() {
        return type;
    }


    /**
     * Возвращает валюту счета "из".
     */
    public String getCurrencyFrom() {
        return currencyFrom;
    }


    /**
     * Возвращает валюту счета "в".
     */
    public String getCurrencyTo() {
        return currencyTo;
    }


    /**
     * Возвращает сумму счета "из".
     */
    public BigDecimal getAmountFrom() {
        return amountFrom;
    }


    /**
     * Возвращает сумму счета "в".
     */
    public BigDecimal getAmountTo() {
        return amountTo;
    }


    /**
     * Возвращает комментарий к транзакции.
     */
    public String getComment() {
        return comment;
    }


    /**
     * Возвращает ID счета, связанного с транзакцией.
     */
    public int getAccountId() {
        return accountId;
    }


    /**
     * Возвращает email пользователя, связанного с транзакцией.
     */
    public String getUserEmailFrom() {
        return userEmailFrom;
    }


    /**
     * Возвращает email пользователя, связанного с транзакцией.
     */
    public String getUserEmailTo() {
        return userEmailTo;
    }

}