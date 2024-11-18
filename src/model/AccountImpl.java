package model;

import java.math.BigDecimal;

public class AccountImpl implements Account {
    private int id;
    private String title;
    private String currency;
    private BigDecimal balance;
    private String userEmail;
    private String comment;

    // Конструктор для создания нового счета


    public AccountImpl(int id, String title, String currency, BigDecimal balance, String userEmail) {
        this.id = id;
        this.title = title;
        this.currency = currency;
        this.balance = balance;
        this.userEmail = userEmail;
        this.comment = "";
    }

    public AccountImpl(int id, String title, String currency, BigDecimal balance, String userEmail, String comment) {
        this.id = id;
        this.title = title;
        this.currency = currency;
        this.balance = balance;
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
     * @param title
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
     * @param currency
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

    /**
     * Добавляет сумму к балансу счета.
     *
     * @param money сумма для добавления
     */
    @Override
    public void deposit(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(money);
        } else {
            throw new IllegalArgumentException("Сумма для пополнения должна быть больше нуля");
        }
    }

    /**
     * Снимает сумму с баланса счета.
     *
     * @param money сумма для снятия
     */
    @Override
    public void withdrawal(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            if (balance.compareTo(money) >= 0) {
                balance = balance.subtract(money);
            } else {
                throw new IllegalArgumentException("Недостаточно средств на счету для снятия");
            }
        } else {
            throw new IllegalArgumentException("Сумма для снятия должна быть больше нуля");
        }
    }
}