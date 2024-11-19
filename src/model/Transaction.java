package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс представляет транзакцию по счету в рамках пользователя.
 */
public class Transaction {

    // Уникальный идентификатор счета пользователя.
    private final int id;

    // Дата создания транзакции.
    private final LocalDateTime date;

    // Тип транзакции.
    private final TransactionType type;

    // Email пользователя "из".
    private final String userEmailFrom;

    // Email пользователя "в".
    private final String userEmailTo;

    // Счет "из".
    private final int accountIdFrom;

    // Счет "в".
    private final int accountIdTo;

    // Символ валюты "из".
    private final String currencyFrom;

    // Символ валюты "в".
    private final String currencyTo;

    // Курс обмена.
    private final BigDecimal course;

    // Сумма.
    private final BigDecimal amount;

    // Комментарий к транзакции.
    private final String comment;


    public Transaction(
            int id,
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount
    ) {
        this.id = id;
        this.date = LocalDateTime.now();
        this.type = type;

        this.userEmailFrom = userEmailFrom;
        this.accountIdFrom = accountIdFrom;
        this.currencyFrom = currencyFrom;

        this.userEmailTo = userEmailTo;
        this.accountIdTo = accountIdTo;
        this.currencyTo = currencyTo;

        this.amount = amount;
        this.course = null;
        this.comment = null;
    }


    public Transaction(
            int id,
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount,
            BigDecimal course
    ) {
        this.id = id;
        this.date = LocalDateTime.now();
        this.type = type;

        this.userEmailFrom = userEmailFrom;
        this.accountIdFrom = accountIdFrom;
        this.currencyFrom = currencyFrom;

        this.userEmailTo = userEmailTo;
        this.accountIdTo = accountIdTo;
        this.currencyTo = currencyTo;

        this.amount = amount;
        this.course = course;
        this.comment = null;
    }


    public Transaction(
            int id,
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount,
            BigDecimal course,
            String comment
    ) {
        this.id = id;
        this.date = LocalDateTime.now();
        this.type = type;

        this.userEmailFrom = userEmailFrom;
        this.accountIdFrom = accountIdFrom;
        this.currencyFrom = currencyFrom;

        this.userEmailTo = userEmailTo;
        this.accountIdTo = accountIdTo;
        this.currencyTo = currencyTo;

        this.amount = amount;
        this.course = course;
        this.comment = comment;
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