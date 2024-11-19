package repository;

import model.Account;
import model.Transaction;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryImpl implements AccountRepository {

    // Хранилище счетов пользователя
    private final Map<Integer, Account> accounts = new HashMap<>();


    // Счетчик для генерации уникальных ID счетов
    private final AtomicInteger accountIdCounter = new AtomicInteger(1);


    @Override
    public Account createAccount(String userEmail, String title, String currencyCode) {
        // TODO createAccount()
        return null;

        // Создание нового счета
//        Account newAccount = new Account(accountIdCounter++, String currencyCode, BigDecimal.ZERO, userEmail);
//
//        // Добавление счета в список
//        userAccounts.add(newAccount);
//        accounts.put(userEmail, userAccounts);
//
//        return newAccount;
    }


    @Override
    public Account getAccountById(int id) {
        // TODO

        // Поиск счета по идентификатору среди всех пользователей
//        for (List<Account> accounts : accounts.values()) {
//            for (Account account : accounts) {
//                if (account.getId() == id) {
//                    return account;
//                }
//            }
//        }
        return null; // Возвращаем null, если счет не найден
    }


    // Возвращаем все счета пользователя по userId (email)
    @Override
    public List<Account> getAllAccounts(String userEmail) {
        // TODO нужно из Map (accounts) сделать List (accounts), можно использовать стрим.
//        return accounts.getOrDefault(userEmail, new ArrayList<>());
        return null;
    }


    @Override
    public List<Account> getAccountsByCurrency(String userEmail, String currencyCode) {
        // TODO:

//        // Получаем все счета пользователя и фильтруем по коду валюты
//        List<Account> userAccounts = accounts.getOrDefault(userEmail, new ArrayList<>());
//        List<Account> filteredAccounts = new ArrayList<>();
//
//        for (Account account : userAccounts) {
//            if (account.getCurrency().equals(currencyCode)) {
//                filteredAccounts.add(account);
//            }
//        }
//        return filteredAccounts;
        return null;
    }


    @Override
    public void removeAccount(int id) {
        // TODO

//        // Удаление счета из списка пользователя по идентификатору
//        for (List<Account> accounts : accounts.values()) {
//            accounts.removeIf(account -> account.getId() == id);
//        }
    }


    @Override
    public void removeAccount(Account account) {
        // TODO:

//        // Удаление счета из списка пользователя
//        String userEmail = account.getUserEmail();
//        List<Account> userAccounts = accounts.get(userEmail);
//
//        if (userAccounts != null) {
//            userAccounts.remove(account);
//        }
    }

}