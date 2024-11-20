package repository;

import model.Account;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    // Хранилище всех счетов.
    private final Map<Integer, Account> accounts;

    // Счетчик для генерации уникальных ID счетов.
    private final AtomicInteger accountIdCounter = new AtomicInteger(0);


    public AccountRepositoryImpl() {
        this.accounts = new HashMap<>();
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
    public Account createAccount(String userEmail, String currencyCode, String title) {
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
     * Возвращает счет по его id.
     *
     * @param id Идентификатор счет.
     * @return Счет.
     */
    @Override
    public Account getAccountById(int id) {
        // Возвращаем счет по его идентификатору
        return accounts.get(id);
    }

    @Override
    public List<Account> getAllAccounts(String userEmail) {
        // Возвращаем все счета пользователя или пустой список, если пользователь не имеет счетов
        return userAccounts.getOrDefault(userEmail, new ArrayList<>());
    }

    @Override
    public List<Account> getAccountsByCurrency(String userEmail, String currencyCode) {
        // Получаем все счета пользователя и фильтруем их по коду валюты
        return userAccounts.getOrDefault(userEmail, new ArrayList<>()).stream()
                .filter(account -> account.getCurrency().equals(currencyCode))
                .collect(Collectors.toList());
    }

    @Override
    public void removeAccount(int id) {
        // Находим счет по идентификатору
        Account accountToRemove = accounts.get(id);

        if (accountToRemove != null) {
            // Удаляем счет из общего хранилища
            accounts.remove(id);

            // Удаляем счет из списка счетов пользователя
            List<Account> userAccountList = userAccounts.get(accountToRemove.getUserEmail());
            if (userAccountList != null) {
                userAccountList.remove(accountToRemove);
                if (userAccountList.isEmpty()) {
                    userAccounts.remove(accountToRemove.getUserEmail());
                }
            }
        }
    }

    @Override
    public void removeAccount(Account account) {
        if (account != null) {
            // Удаляем счет из общего хранилища
            accounts.remove(account.getId());

            // Удаляем счет из списка счетов пользователя
            List<Account> userAccountList = userAccounts.get(account.getUserEmail());
            if (userAccountList != null) {
                userAccountList.remove(account);
                if (userAccountList.isEmpty()) {
                    userAccounts.remove(account.getUserEmail());
                }
            }
        }
    }
}