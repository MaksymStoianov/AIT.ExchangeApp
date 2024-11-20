package repository;

import model.Account;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    // Хранилище счетов пользователя
    private final Map<Integer, Account> accounts = new HashMap<>();

    // Хранилище счетов по email пользователей
    private final Map<String, List<Account>> userAccounts = new HashMap<>();

    // Счетчик для генерации уникальных ID счетов
    private final AtomicInteger accountIdCounter = new AtomicInteger(1);


    /**
     * @param userEmail    Идентификатор пользователя.
     * @param currencyCode Код валюты.
     * @param title        Название счет.
     * @return
     */
    @Override
    public Account createAccount(String userEmail, String currencyCode, String title) {
        // Генерируем уникальный идентификатор для нового счета
        int accountId = accountIdCounter.getAndIncrement();

        // Создаем новый счет с начальным балансом 0
        Account newAccount = new Account(accountId, currencyCode, BigDecimal.ZERO, userEmail, title);

        // Добавляем счет в список счетов пользователя
        userAccounts.computeIfAbsent(userEmail, k -> new ArrayList<>()).add(newAccount);

        // Сохраняем счет в общем хранилище
        accounts.put(accountId, newAccount);

        return newAccount;
    }



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