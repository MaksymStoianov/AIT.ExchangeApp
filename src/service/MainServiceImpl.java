package service;

import model.*;
import repository.AccountRepository;
import repository.CurrencyRepository;
import repository.TransactionRepository;
import repository.UserRepository;
import utils.EmailValidateException;
import utils.EmailValidator;
import utils.PasswordValidateException;
import utils.PasswordValidator;
import utils.exceptions.CurrencyCodeValidateExeption;


import java.math.BigDecimal;
import java.util.List;

public class MainServiceImpl implements MainService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionRepository transactionRepository;
    private User loggedInUser;


    public MainServiceImpl(UserRepository userRepository, AccountRepository accountRepository, CurrencyRepository currencyRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.transactionRepository = transactionRepository;
    }

    public MainServiceImpl() {

    }


    /**
     * Добавляет пользователя. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public boolean registerUser(String email, String password) throws UserIsExistsExeption {
        if (!EmailValidator.isValidEmail(email)) {
            throw new EmailValidateException("Email не прошел проверку!");
        }
        if (!PasswordValidator.isValidPassword(password)) {
            throw new PasswordValidateException("Пароль не прошел проверку!");
        }
        if (userRepository.isEmailExists(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }
        User user = userRepository.addUser(email, password);
        return true;
    }

    /**
     * Добавляет пользователя с ролью. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
     *
     * @param email
     * @param password
     * @param role
     * @return
     */
    @Override
    public boolean registerUser(String email, String password, UserRole role) throws UserIsExistsExeption {

        if (!EmailValidator.isValidEmail(email)) {
            throw new EmailValidateException("Email не прошел проверку!");
        }
        if (!PasswordValidator.isValidPassword(password)) {
            throw new PasswordValidateException("Пароль не прошел проверку!");
        }
        if (userRepository.isEmailExists(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }

        User user = userRepository.addUser(email, password, role);
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
        return userRepository.getUserByEmail(email);
    }

    /**
     * Возвращает список всех пользователей.
     *
     * @return List
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    /**
     * Возвращает список пользователей с ролью.
     *
     * @param role
     * @return
     */
    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.getUsersByRole(role);
    }

    /**
     * Возвращает список заблокированных пользователей.
     *
     * @return
     */
    @Override
    public List<User> getBlockedUsers() {
        return userRepository.getBlockedUsers();
    }

    /**
     * Авторизует пользователя в системе.
     *
     * @param email
     * @param password
     * @return boolean
     */
    @Override
    public boolean loginUser(String email, String password) {
        if (email == null || password == null) {
            throw new SecurityException("Неверный email или пароль!");
        }
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new SecurityException("Неверный email или пароль!");
        }
        if (!user.getPassword().equals(password)) {
            throw new SecurityException("Неверный email или пароль!");
        }
        loggedInUser = user;
        return true;
    }

    /**
     * Выходит из системы.
     */
    @Override
    public void logout() {
        loggedInUser = null;
    }

    /**
     * Добавляет счет к пользователю в определенной валюте.
     *
     * @param title        Название счета.
     * @param currencyCode Код валюты.
     * @return Счет.
     */
    @Override
    public Account creatAccount( String title, String currencyCode) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        if (!CurrencyCodeValidator.isValidCurrencyCode(currencyCode)){
            throw new CurrencyCodeValidateExeption("Недопустимый код валюты.");
        }

        Account account = accountRepository.createAccount(loggedInUser.getEmail(), title, currencyCode);
        return account;
    }

    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    @Override
    public List<Account> getAllAccounts() {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }

        return accountRepository.getAllAccounts(loggedInUser.getEmail());
    }

    /**
     * Возвращает счет пользователя по его уникальному идентификатору.
     *
     * @param id
     * @return Счет.
     */
    @Override
    public Account getAccountById(int id) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }

        Account account = accountRepository.getAccountById(id);
        if (account.getUserEmail().equals(loggedInUser.getEmail()){
            //todo
        }
        return account;
    }

    /**
     * Возвращает список счетов по коду валюты.
     *
     * @param currencyCode
     * @return
     */
    @Override
    public List<Account> getAccountsByCurrency(String currencyCode) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }
        if (!CurrencyCodeValidator.isValidCurrencyCode(currencyCode)){
            throw new CurrencyCodeValidateExeption("Недопустимый код валюты.");
        }
        return accountRepository.getAccountsByCurrencyCode(loggedInUser.getEmail(), currencyCode);
    }

    /**
     * Добавляет сумму к счету. Этот метод должен вернуть Ошибку если пользователь не залогинен.
     *
     * @param accountId
     * @param money
     */
    @Override
    public boolean deposit(int accountId, BigDecimal money) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован!");
        }
        if (money == null || money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма для депозита должна быть больше нуля!");
        }
        Account account = accountRepository.getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден!");
        }

        if (!account.getUserEmail().equals(loggedInUser.getEmail())) {
            throw new SecurityException("Этот счет не принадлежит текущему пользователю!");
        }
       Transaction transaction =  transactionRepository.createTransaction(TransactionType.DEPOSIT, loggedInUser.getEmail(),
                accountId, account.getCurrency(), loggedInUser.getEmail(), accountId, account.getCurrency(), money, null, "Депозит");

        account.setBalance(account.getBalance().add(money));
        return true;

    }

    /**
     * Снимает сумму со счета. Этот метод должен вернуть Ошибку если пользователь не залогинен.
     *
     * @param accountId
     * @param money
     */
    @Override
    public boolean withdrawal(int accountId, BigDecimal money) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован!");
        }

        if (money == null || money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма для снятия должна быть больше нуля!");
        }
        Account account = accountRepository.getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден!");
        }

        if (!account.getUserEmail().equals(loggedInUser.getEmail())) {
            throw new SecurityException("Этот счет не принадлежит текущему пользователю!");
        }

        if (account.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счете!");
        }

        account.setBalance(account.getBalance().subtract(money));

        transactionRepository.createTransaction( TransactionType.WITHDRAW, loggedInUser.getEmail(),
                accountId, account.getCurrency(), loggedInUser.getEmail(), accountId, account.getCurrency(), money, null, "Снятие");

        account.setBalance(account.getBalance().subtract(money));
        return true;
    }

    /**
     * Переводит сумму между счетами. Если происходит перевод между счетами в разных валютах, обмен делается через USD.
     *
     * @param accountId1
     * @param accountId2
     * @param money
     * @return
     */
    @Override
    public boolean exchange(int accountId1, int accountId2, BigDecimal money) {

        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован!");
        }

        if (money == null || money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма для обмена должна быть больше нуля!");
        }

        Account account1 = accountRepository.getAccountById(accountId1);
        Account account2 = accountRepository.getAccountById(accountId2);
        if (account1 == null || account2 == null) {
            throw new IllegalArgumentException("Один или оба счета не найдены!");
        }

        if (account1.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счете для обмена!");
        }

        BigDecimal course = crossCourse(account1.getCurrency(), account2.getCurrency());
        if (course == null) {
            throw new IllegalArgumentException("Не удалось найти курс для обмена!");
        }

        account1.setBalance(account1.getBalance().subtract(money));

        BigDecimal exchangedAmount = money.multiply(course);

        account2.setBalance(account2.getBalance().add(exchangedAmount));

        Transaction transaction = transactionRepository.createTransaction( TransactionType.TRANSFER, loggedInUser.getEmail(),
                accountId1, account1.getCurrency(), loggedInUser.getEmail(), accountId2, account2.getCurrency(), money, course, "Обмен");

        return true;
    }

    /**
     * Возвращает кросс-курс валюты.
     *
     * @param target Символ валюты 1.
     * @param source Символ валюты 2.
     * @return
     */
    @Override
    public BigDecimal crossCourse(String target, String source) {
        if ("USD".equals(source) && "EUR".equals(target)) {
            return BigDecimal.valueOf(0.9);
        } else if ("EUR".equals(source) && "USD".equals(target)) {
            return BigDecimal.valueOf(1.1);
        }
        return null;
    }

    /**
     * Возвращает список всех транзакций по id счета.
     *
     * @param id
     * @return
     */
    @Override
    public Transaction getTransactionById(int id) throws Exception {
        if (!(id >= 0 )) {
            throw new Exception("Неверный id.");
        }
        return transactionRepository.getTransactionById(id);
    }


    /**
     * Удаляет счет из списка счетов пользователя.
     *
     * @param id Уникальный идентификатор счета.
     */
    @Override
    public void removeAccount(int id) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован!");
        }
        Account account = accountRepository.getAccountById(id);
        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден!");
        }
        if (!account.getUserEmail().equals(loggedInUser.getEmail())) {
            throw new SecurityException("Этот счет не принадлежит текущему пользователю!");
        }

        accountRepository.removeAccount(id);

    }

}
