package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс представляет банковский счёт пользователя.
 *
 * <p>Каждый счёт связывается с электронной почтой пользователя.</p>
 */
public class Account {

    // Уникальный идентификатор счета пользователя.
    private final int id;

    // Дата создания счета.
    private final LocalDateTime creationDate;

    // Статус счета.
    private AccountStatus status;

    // Название счета.
    private String title;

    // Валюта счета.
    private final String currency;

    // Баланс счета.
    private BigDecimal balance;

    // Email владельца счета.
    private final String userEmail;


    public Account(int id, String currency, BigDecimal balance, String userEmail) {
        this.id = id;
        this.creationDate = LocalDateTime.now();
        this.status = AccountStatus.ACTIVE;
        this.title = null;
        this.currency = currency;
        this.balance = (balance != null ? balance : BigDecimal.ZERO);
        this.userEmail = userEmail;
    }


    public Account(int id, String currency, BigDecimal balance, String userEmail, String title) {
        this.id = id;
        this.creationDate = LocalDateTime.now();
        this.status = AccountStatus.ACTIVE;
        this.title = title;
        this.currency = currency;
        this.balance = (balance != null ? balance : BigDecimal.ZERO);
        this.userEmail = userEmail;
    }


    /**
     * Возвращает уникальный идентификатор счета.
     *
     * @return Уникальный идентификатор счета.
     */
    public int getId() {
        return this.id;
    }


    /**
     * Возвращает название счета.
     *
     * @return Название счета.
     */
    public String getTitle() {
        return this.title;
    }


    /**
     * Устанавливает новое название счета.
     *
     * @param title Новое название счета.
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Возвращает дату и время создания счета.
     *
     * @return Дата и время создания счета.
     */
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }


    /**
     * Возвращает статус счета.
     *
     * @return Статус счета.
     */
    public AccountStatus getStatus() {
        return this.status;
    }


    /**
     * Устанавливает новый статус счета.
     *
     * @param status Новый статус счета.
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }


    /**
     * Возвращает код валюты счета.
     *
     * @return Код валюты счета.
     */
    public String getCurrency() {
        return this.currency;
    }


    /**
     * Возвращает баланс счета.
     *
     * @return Баланс счета.
     */
    public BigDecimal getBalance() {
        return this.balance;
    }


    /**
     * Обновляет баланс счета.
     *
     * @param balance Новый баланс.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    /**
     * Возвращает email пользователя, связанный с этим счетом.
     *
     * @return Email владельца счета.
     */
    public String getUserEmail() {
        return this.userEmail;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(creationDate, account.creationDate) && status == account.status &&
               Objects.equals(title, account.title) && Objects.equals(currency, account.currency) &&
               Objects.equals(balance, account.balance) && Objects.equals(userEmail, account.userEmail);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, status, title, currency, balance, userEmail);
    }


    @Override
    public String toString() {
        return "Account{" +
               "id=" + id +
               ", creationDate=" + creationDate +
               ", status=" + status +
               ", title='" + title + '\'' +
               ", currency='" + currency + '\'' +
               ", balance=" + balance +
               ", userEmail='" + userEmail + '\'' +
               '}';
    }
}