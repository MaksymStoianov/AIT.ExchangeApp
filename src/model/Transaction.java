package model;

import model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс представляет транзакцию по счету в рамках пользователя.
 */
public class Transaction {

    // Уникальный идентификатор.
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


    /**
     * Конструктор для создания объекта {@code Transaction}.
     *
     * @param id
     * @param type
     * @param userEmailFrom
     * @param accountIdFrom
     * @param currencyFrom
     * @param userEmailTo
     * @param accountIdTo
     * @param currencyTo
     * @param amount
     */
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


    /**
     * Конструктор для создания объекта {@code Transaction}.
     *
     * @param id
     * @param type
     * @param userEmailFrom
     * @param accountIdFrom
     * @param currencyFrom
     * @param userEmailTo
     * @param accountIdTo
     * @param currencyTo
     * @param amount
     * @param course
     */
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

    /**
     * Конструктор для создания объекта {@code Transaction}.
     *
     * @param id
     * @param type
     * @param userEmailFrom
     * @param accountIdFrom
     * @param currencyFrom
     * @param userEmailTo
     * @param accountIdTo
     * @param currencyTo
     * @param amount
     * @param course
     * @param comment
     */
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
     * Возвращает уникальный идентификатор транзакции.
     *
     * @return Уникальный идентификатор транзакции.
     */
    public int getId() {
        return this.id;
    }


    /**
     * Возвращает дату создания транзакции.
     *
     * @return Дата создания транзакции.
     */
    public LocalDateTime getDate() {
        return this.date;
    }


    /**
     * Возвращает тип транзакции: Пополнение, Снятие, Перевод.
     *
     * @return Тип транзакции.
     */
    public TransactionType getType() {
        return this.type;
    }


    /**
     * Возвращает email пользователя "из".
     *
     * @return Email пользователя "из".
     */
    public String getUserEmailFrom() {
        return this.userEmailFrom;
    }


    /**
     * Возвращает счет аккаунта пользователя "из".
     *
     * @return Счет аккаунта пользователя "из".
     */
    public int getAccountIdFrom() {
        return this.accountIdFrom;
    }


    /**
     * Возвращает код валюты счета "из".
     *
     * @return Код валюты счета "из".
     */
    public String getCurrencyFrom() {
        return this.currencyFrom;
    }


    /**
     * Возвращает email пользователя "в".
     *
     * @return Email пользователя "в".
     */
    public String getUserEmailTo() {
        return this.userEmailTo;
    }


    /**
     * Возвращает счет аккаунта пользователя "в".
     *
     * @return Счет аккаунта пользователя "в".
     */
    public int getAccountIdTo() {
        return this.accountIdTo;
    }


    /**
     * Возвращает код валюты счета "в".
     *
     * @return Код валюты счета "в".
     */
    public String getCurrencyTo() {
        return this.currencyTo;
    }


    /**
     * Возвращает сумму транзакции.
     *
     * @return Сумма транзакции.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }


    /**
     * Возвращает курс перевода или {@code null}.
     *
     * @return Курс перевода или {@code null}.
     */
    public BigDecimal getCourse() {
        return this.course;
    }


    /**
     * Возвращает комментарий к транзакции.
     *
     * @return Комментарий.
     */
    public String getComment() {
        return this.comment;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && accountIdFrom == that.accountIdFrom && accountIdTo == that.accountIdTo &&
               Objects.equals(date, that.date) && type == that.type &&
               Objects.equals(userEmailFrom, that.userEmailFrom) &&
               Objects.equals(userEmailTo, that.userEmailTo) &&
               Objects.equals(currencyFrom, that.currencyFrom) &&
               Objects.equals(currencyTo, that.currencyTo) && Objects.equals(course, that.course) &&
               Objects.equals(amount, that.amount);
    }


    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                date,
                type,
                userEmailFrom,
                userEmailTo,
                accountIdFrom,
                accountIdTo,
                currencyFrom,
                currencyTo,
                course,
                amount
        );
    }


    @Override
    public String toString() {
        return "Transaction{" +
               "id=" + id +
               ", date=" + date +
               ", type=" + type +
               ", userEmailFrom='" + userEmailFrom + '\'' +
               ", userEmailTo='" + userEmailTo + '\'' +
               ", accountIdFrom=" + accountIdFrom +
               ", accountIdTo=" + accountIdTo +
               ", currencyFrom='" + currencyFrom + '\'' +
               ", currencyTo='" + currencyTo + '\'' +
               ", course=" + course +
               ", amount=" + amount +
               ", comment='" + comment + '\'' +
               '}';
    }

}