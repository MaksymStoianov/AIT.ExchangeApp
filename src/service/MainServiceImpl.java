package service;

import model.Account;
import model.Transaction;
import model.User;
import model.UserRole;
import repository.AccountRepository;
import repository.CurrencyRepository;
import repository.TransactionRepository;
import repository.UserRepository;
import utils.EmailValidateException;
import utils.EmailValidator;
import utils.PasswordValidateException;
import utils.PasswordValidator;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainServiceImpl implements MainService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionRepository transactionRepository;
    private User loggedInUser;
    private final AtomicInteger currentdIdAccount = new AtomicInteger(1);
    private final AtomicInteger currentdIdForTransaction = new AtomicInteger(1);
    private final AtomicInteger currentdIdForUser = new AtomicInteger(1);

    public MainServiceImpl(UserRepository userRepository, AccountRepository accountRepository, CurrencyRepository currencyRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.transactionRepository = transactionRepository;
    }


    /**
     * Добавляет пользователя. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public User registerUser(String email, String password) throws UserIsExistsExeption {
        if (!EmailValidator.isValidEmail(email)) {
            throw new EmailValidateException("Email не прошел проверку!");
        }
        if (!PasswordValidator.isValidPassword(password)){
            throw new PasswordValidateException("Пароль не прошел проверку!");
        }
        if (userRepository.isEmailExists(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }

        User user = userRepository.addUser(email,password); //TODO
        return user;


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
        if (!PasswordValidator.isValidPassword(password)){
            throw new PasswordValidateException("Пароль не прошел проверку!");
        }
        if (userRepository.isEmailExists(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }
        User user = new User(email, password, UserRole.USER);
        userRepository.save(user); //TODO
        return users; // надо User
    }

    /**
     * Возвращает пользователя по его email.
     *
     * @param email
     * @return
     */
    @Override
    public User getUser(String email) { // userRepo
        return userRepository.getUserByEmail(String email);
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
        // проверка email + password на null
       User user = userRepository.getUserByEmail(email);
       // if user == null error
        // equals password
        if (user != null){
            loggedInUser = user;
            return true;
        }

        throw new SecurityException("Неверный email или пароль!");
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
    public Account creatAccount(String title, String currencyCode) {
        if (loggedInUser == null){
            throw new SecurityException("Пользователь не авторизован");
        }
        Account account = new Account(currentdIdAccount.getAndIncrement(), currencyCode, BigDecimal.ZERO, userEmail);
        accountRepository.put(account.getId(), account);

        return account;
    }

    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    @Override
    public List<Account> getAllAccounts() {
        return null;
    }
    /**
     * Возвращает счет пользователя по его уникальному идентификатору.
     *
     * @param id
     * @return Счет.
     */
    @Override
    public Account getAccountById(int id) {
        return null;
    }
    /**
     * Возвращает список счетов по коду валюты.
     *
     * @param currencyCode
     * @return
     */
    @Override
    public List<Account> getAccountsByCurrencyCode(String currencyCode) {
        return List.of();
    }



    /**
     * Добавляет сумму к счету. Этот метод должен вернуть Ошибку если пользователь не залогинен.
     *
     * @param accountId
     * @param money
     */
    @Override
    public boolean deposit(int accountId, BigDecimal money) {
        return false;
    }
    /**
     * Снимает сумму со счета. Этот метод должен вернуть Ошибку если пользователь не залогинен.
     *
     * @param accountId
     * @param money
     */
    @Override
    public boolean withdrawal(int accountId, BigDecimal money) {
        return false;
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
        return false;
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
        return null;
    }

    /**
     * Возвращает список всех транзакций по id счета.
     *
     * @param accountId
     * @return
     */
    @Override
    public Map<LocalDateTime, Transaction> getTransactionsByAccountId(int accountId) {
        return Map.of();
    }


    /**
     * Удаляет счет из списка счетов пользователя.
     *
     * @param id Уникальный идентификатор счета.
     */
    @Override
    public void removeAccount(int id) {

    }

    /**
     * Удаляет счет из списка счетов пользователя.
     *
     * @param account Счет.
     */
    @Override
    public void removeAccount(Account account) {

    }
}
