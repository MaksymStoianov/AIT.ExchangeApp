package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionImpl implements Transaction {
    private final int id;
    private final LocalDateTime date;
    private final TransactionType type;
    private final String currencyFrom;
    private final String currencyTo;
    private final BigDecimal amountFrom;
    private final BigDecimal amountTo;
    private final String comment;
    private final int accountId;
    private final String userEmail;

    // Конструктор для инициализации всех полей
    public TransactionImpl(int id, LocalDateTime date, TransactionType type, String currencyFrom,
                           String currencyTo, BigDecimal amountFrom, BigDecimal amountTo,
                           String comment, int accountId, String userEmail) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.comment = comment;
        this.accountId = accountId;
        this.userEmail = userEmail;
    }

    /**
     * Возвращает ID транзакции.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Возвращает дату транзакции.
     */
    @Override
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Возвращает тип транзакции: Пополнение, Снятие, Перевод.
     */
    @Override
    public TransactionType getType() {
        return type;
    }

    /**
     * Возвращает валюту счета "из".
     */
    @Override
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    /**
     * Возвращает валюту счета "в".
     */
    @Override
    public String getCurrencyTo() {
        return currencyTo;
    }

    /**
     * Возвращает сумму счета "из".
     */
    @Override
    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    /**
     * Возвращает сумму счета "в".
     */
    @Override
    public BigDecimal getAmountTo() {
        return amountTo;
    }

    /**
     * Возвращает комментарий к транзакции.
     */
    @Override
    public String getComment() {
        return comment;
    }

    /**
     * Возвращает ID счета, связанного с транзакцией.
     */
    @Override
    public int getAccountId() {
        return accountId;
    }

    /**
     * Возвращает email пользователя, связанного с транзакцией.
     */
    @Override
    public String getUserEmail() {
        return userEmail;
    }
}