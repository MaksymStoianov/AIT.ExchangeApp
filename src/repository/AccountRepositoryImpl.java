package repository;

import model.Account;
import model.AccountImpl;
import model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class AccountRepositoryImpl implements AccountRepository {

    // Хранилище пользователей и их счетов
    private final Map<String, List<Account>> users = new LinkedHashMap<>();

    // Хранилище транзакций по идентификатору счета
    private final Map<Integer, List<Transaction>> transactions = new LinkedHashMap<>();

    private int accountIdCounter = 1; // Счетчик для генерации уникальных ID счетов

    @Override
    public Account createAccount(String userEmail, String title, String currencyCode) {
        // Получение списка счетов пользователя
        List<Account> userAccounts = users.getOrDefault(userEmail, new ArrayList<>());

        // Создание нового счета
        Account newAccount = new AccountImpl(accountIdCounter++, title, currencyCode, BigDecimal.ZERO, userEmail);

        // Добавление счета в список
        userAccounts.add(newAccount);
        users.put(userEmail, userAccounts);

        return newAccount;
    }

    @Override
    public Account getAccountById(int id) {
        // Поиск счета по идентификатору среди всех пользователей
        for (List<Account> accounts : users.values()) {
            for (Account account : accounts) {
                if (account.getId() == id) {
                    return account;
                }
            }
        }
        return null; // Возвращаем null, если счет не найден
    }

    // Возвращаем все счета пользователя по userId (email)
    @Override
    public List<Account> getAllAccounts(String userEmail) {
        return users.getOrDefault(userEmail, new ArrayList<>());
    }

    @Override
    public List<Account> getAccountsByCurrency(String userEmail, String currencyCode) {
        // Получаем все счета пользователя и фильтруем по коду валюты
        List<Account> userAccounts = users.getOrDefault(userEmail, new ArrayList<>());
        List<Account> filteredAccounts = new ArrayList<>();

        for (Account account : userAccounts) {
            if (account.getCurrency().equals(currencyCode)) {
                filteredAccounts.add(account);
            }
        }
        return filteredAccounts;
    }

    @Override
    public void removeAccount(int id) {
        // Удаление счета из списка пользователя по идентификатору
        for (List<Account> accounts : users.values()) {
            accounts.removeIf(account -> account.getId() == id);
        }
    }

    @Override
    public void removeAccount(Account account) {
        // Удаление счета из списка пользователя
        String userEmail = account.getUserEmail();
        List<Account> userAccounts = users.get(userEmail);
        if (userAccounts != null) {
            userAccounts.remove(account);
        }
    }
}