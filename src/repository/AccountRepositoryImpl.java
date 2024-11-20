package repository;

import model.Account;
import model.enums.AccountStatus;
import repository.interfaces.AccountRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Реализация репозитория для управления счетами пользователей.
 */
public class AccountRepositoryImpl implements AccountRepository {

    // Хранилище всех счетов.
    private final Map<Integer, Account> accounts;

    // Счетчик для генерации уникальных ID счетов.
    private final AtomicInteger accountIdCounter;


    public AccountRepositoryImpl() {
        this.accounts = new HashMap<>();
        this.accountIdCounter = new AtomicInteger(0);
    }


    /**
     * Создает новый счет.
     *
     * @param userEmail    Email пользователя.
     * @param currencyCode Код валюты.
     * @return Счет.
     */
    public Account createAccount(String userEmail, String currencyCode) {
        int accountId = this.accountIdCounter.getAndIncrement();

        Account account = new Account(
                accountId,
                currencyCode,
                BigDecimal.ZERO,
                userEmail
        );

        this.accounts.put(accountId, account);

        return account;
    }


    /**
     * Создает новый счет.
     *
     * @param userEmail    Email пользователя.
     * @param currencyCode Код валюты.
     * @param title        Название счет.
     * @return Счет.
     */
    @Override
    public Account createAccount(String userEmail, String title, String currencyCode) {
        int accountId = this.accountIdCounter.getAndIncrement();

        Account account = new Account(
                accountId,
                currencyCode,
                BigDecimal.ZERO,
                userEmail,
                title
        );

        this.accounts.put(accountId, account);

        return account;
    }


    /**
     * Создает новый системный счет.
     *
     * @param userEmail    Email пользователя.
     * @param currencyCode Код валюты.
     * @param title        Название счет.
     * @return Счет.
     */
    @Override
    public Account createSystemAccount(String userEmail, String currencyCode, String title) {
        int accountId = this.accountIdCounter.getAndIncrement();

        Account account = new Account(
                accountId,
                currencyCode,
                BigDecimal.ZERO,
                userEmail,
                title
        );

        account.setStatus(AccountStatus.SYSTEM);

        this.accounts.put(accountId, account);

        return account;
    }


    /**
     * Возвращает счет по его id.
     *
     * @param id Идентификатор счет.
     * @return Счет.
     */
    @Override
    public Account getAccountById(int id) {
        return this.accounts.get(id);
    }


    /**
     * Возвращает список всех счетов.
     *
     * @return Список всех счетов.
     */
    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(this.accounts.values());
    }


    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    @Override
    public List<Account> getAccountsByUserEmail(String userEmail) throws IllegalArgumentException{
        if (userEmail == null) {
            throw new IllegalArgumentException("Аргумент userEmail не может быть null!");
        }

        return this
                .getAllAccounts()
                .stream()
                .filter(item -> item.getUserEmail().equals(userEmail))
                .collect(Collectors.toList());
    }


    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    @Override
    public List<Account> getAccountsByCurrencyCode(String userEmail, String currencyCode) throws IllegalArgumentException {
        if (userEmail == null) {
            throw new IllegalArgumentException("Аргумент userEmail не может быть null!");
        }

        if (currencyCode == null) {
            throw new IllegalArgumentException("Аргумент currencyCode не может быть null!");
        }

        return this
                .getAllAccounts()
                .stream()
                .filter(item -> item.getUserEmail().equals(userEmail))
                .filter(item -> item.getCurrency().equals(currencyCode))
                .collect(Collectors.toList());
    }


    /**
     * Удаляет счет пользователя по его идентификатору.
     *
     * @param id Идентификатор счета.
     */
    @Override
    public void removeAccount(int id) throws Exception {
        if (!this.accounts.containsKey(id)) {
            throw new Exception("Счета с указанным id не найден!");
        }

        this.accounts.remove(id);
    }


    /**
     * Удаляет счет из списка счетов пользователя.
     *
     * @param account Счет.
     */
    @Override
    public void removeAccount(Account account) throws Exception {
        if (account == null) {
            throw new IllegalArgumentException("Аргумент account не может быть null!");
        }

        int id = account.getId();

        if (!this.accounts.containsKey(id)) {
            throw new Exception("Счета с указанным id не найден!");
        }

        this.accounts.remove(id);
    }

}