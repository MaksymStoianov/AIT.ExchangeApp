package service;

import model.*;
import repository.AccountRepository;
import repository.CurrencyRepository;
import utils.exceptions.UserIsExistsExeption;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainServiceImpl implements MainService {

    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Rate> rates = new HashMap<>();
    private final Map<Integer, AccountImpl> accounts = new HashMap<>();
    private final Map<Integer, Map<LocalDateTime, TransactionImpl>> transactions = new HashMap<>();
    private User loggedInUser;
    private final AtomicInteger currentdId = new AtomicInteger(1);
    private final AtomicInteger currentdIdForTransaction = new AtomicInteger(1);


    public MainServiceImpl(AccountRepository accountRepository, CurrencyRepository currencyRepository) {
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
    }


    /**
     * Добавляет пользователя. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
     *
     * @param email
     * @param password
     * @return
     * @throws UserIsExistsExeption
     */
    @Override
    public boolean registerUser(String email, String password) throws UserIsExistsExeption {
        if (users.containsKey(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }
        return false;
    }

    /**
     * Регистрация пользователя с ролью
     *
     * @param email
     * @param password
     * @param role
     * @return
     * @throws UserIsExistsExeption
     */
    @Override
    public boolean registerUser(String email, String password, UserRole role) throws UserIsExistsExeption {
        if (users.containsKey(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }
        users.put(email, new UserImpl(email, password));
        return true;
    }

    /**
     * Возвращает пользователя по его email.
     *
     * @param email
     * @return
     */
    @Override
    public User getUser(String email) {
        return users.get(email);

    }

    /**
     * Возвращает список всех пользователей.
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Возвращает список пользователей с ролью.
     *
     * @param role
     * @return
     */
    @Override
    public List<User> getUsersByRole(UserRole role) {
        return users.values().stream() //1. Получаем поток объектов User
                .filter(user -> user.getRole() == role) // 2. Оставляем только тех, у кого роль совпадает с указанной
                .collect(Collectors.toList()); // 3. Преобразуем результат в список
    }


    /**
     * Возвращает список
     * заблокированных пользователей.
     *
     * @return
     */

    @Override
    public List<User> getBlockedUsers() {
        return users.values().stream()
                .filter(UserImpl::isBloceked)
                .collect(Collectors.toList());
    }


    /**
     * Авторизует пользователя
     * в системе.
     *
     * @param email
     * @param password
     * @return
     */

    @Override
    public boolean loginUser(String email, String password) {
        UserImpl user = (UserImpl) users.get(email); // todo
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return true;
        }

        throw new SecurityException("Неверный email или пароль!");
    }


    /**
     * Выходит из системы .
     */

    @Override
    public void logout() {
        loggedInUser = null;
    }


    /**
     * Добавляет счет к пользователю
     * в определенной
     * валюте .
     *
     * @param title        Название
     *                     счета .
     * @param currencyCode Код
     *                     валюты .
     * @return
     */

    @Override
    public Account creatAccount(String title, String currencyCode) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        AccountImpl account = new AccountImpl(currentdId.getAndIncrement(), title, currencyCode, BigDecimal.ZERO, loggedInUser); //todo
        accounts.put(account.getId(), account);
        return account;
    }

    /**
     * Возвращает список
     * всех счетов
     * пользователя .
     *
     * @return Список всех
     * счетов пользователя
     */

    @Override
    public List<Account> getAllAccounts() {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        return accounts.values().stream()
                .filter(account -> account.getUserEmail().equals(loggedInUser))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает счет
     * пользователя по
     * его уникальному
     * идентификатору .
     *
     * @param id
     * @return Счет .
     */

    @Override
    public Account getAccountById(int id) {
        return accounts.get(id);
    }

    @Override
    public List<Account> getAccountsByCurrencyCode(String currencyCode) {
        return accounts.values().stream()
                .filter(account -> account.getCurrency().equals(currencyCode))
                .collect(Collectors.toList());
    }


    /**
     * Добавляет сумму
     * к счету.
     * Этот метод
     * должен вернуть
     * Ошибку если
     * пользователь не
     * залогинен .
     *
     * @param accountId
     * @param money
     * @return
     */

    @Override
    public boolean deposit(int accountId, BigDecimal money) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        Account account = getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Счет не найден");
        }
        account.setBalance(account.getBalance().add(money));
        //todo
        //мне нужен лист где буду писать дату, время и назначение
        return false;
    }

    /**
     * Снимает сумму со счета. Этот метод должен вернуть Ошибку если пользователь не залогинен.
     *
     * @param accountId
     * @param money
     * @return
     */
    @Override
    public boolean withdrawal(int accountId, BigDecimal money) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        Account account = getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Счет не найден");
        }
        if (account.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        account.setBalance(account.getBalance().subtract(money));
        //todo
        // transactions.put(accountId, new TransactionImpl(currentdIdForTransaction.getAndIncrement(), LocalDateTime); //TODO
        //мне нужен лист где буду писать дату, время и назначение
//LocalDateTime.now(), "Withdrawal", money.negate())
        return false;
    }


    @Override
    public boolean exchange(int accountId1, int accountId2, BigDecimal money) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        Account fromAccount = getAccountById(accountId1);
        Account toAccount = getAccountById(accountId2);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Один из счетов не найден.");
        }

        BigDecimal exchangeRate = crossCourse(toAccount.getCurrency(), fromAccount.getCurrency());


        return false;
    }


}
