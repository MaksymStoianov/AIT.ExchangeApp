package service;

import model.*;
import model.enums.CurrencyCode;
import model.enums.TransactionType;
import model.enums.UserRole;
import repository.interfaces.AccountRepository;
import repository.interfaces.CurrencyRepository;
import repository.interfaces.TransactionRepository;
import repository.interfaces.UserRepository;
import service.interfaces.MainService;
import utils.*;
import utils.exceptions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

public class MainServiceImpl implements MainService {

    private final UserRepository repoUser;
    private final AccountRepository repoAccount;
    private final CurrencyRepository repoCurrency;
    private final TransactionRepository repoTransaction;
    private User loggedInUser;


    public MainServiceImpl(
            UserRepository repoUser,
            AccountRepository repoAccount,
            CurrencyRepository repoCurrency,
            TransactionRepository repoTransaction
    ) {
        this.repoUser = repoUser;
        this.repoAccount = repoAccount;
        this.repoCurrency = repoCurrency;
        this.repoTransaction = repoTransaction;
    }


    /**
     * Возвращает текущего активного пользователя.
     * <p>Активный пользователь — это тот, который прошел процесс аутентификации и в данный момент работает в
     * системе.</p>
     *
     * @return Объект пользователя, или {@code null}.
     */
    public User getActiveUser() {
        return this.loggedInUser;
    }


    /**
     * Устанавливает текущего активного пользователя.
     *
     * @param userEmail Email пользователя.
     */
    public void setActiveUser(String userEmail) {
        if (userEmail == null) {
            throw new IllegalArgumentException("Аргумент userEmail не может быть null!");
        }

        this.loggedInUser = this.repoUser.getUserByEmail(userEmail);
    }


    /**
     * Устанавливает текущего активного пользователя.
     *
     * @param user Объект пользователя.
     */
    public void setActiveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Аргумент user не может быть null!");
        }

        this.loggedInUser = user;
    }


    /**
     * Добавляет пользователя. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public boolean registerUser(String email, String password)
            throws UserIsExistsExeption {

        this.validateEmailAndPassword(email, password);

        if (repoUser.isEmailExists(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }

        User user = repoUser.addUser(email, password);

        this.setActiveUser(user);

        return true;
    }


    /**
     * Добавляет пользователя с ролью. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
     *
     * @param email    Email пользователя
     * @param password
     * @param role
     * @return
     */
    @Override
    public boolean registerUser(String email, String password, UserRole role)
            throws UserIsExistsExeption {

        this.validateEmailAndPassword(email, password);

        if (repoUser.isEmailExists(email)) {
            throw new UserIsExistsExeption("Пользователь с таким email уже существует!");
        }

        User user = repoUser.addUser(email, password, role);

        this.setActiveUser(user);

        return true;
    }


    /**
     * Возвращает пользователя по его email.
     *
     * @param email
     * @return
     */
    @Override
    public Optional<User> getUser(String email) {
        return Optional.ofNullable(repoUser.getUserByEmail(email));
    }


    /**
     * Возвращает список всех пользователей.
     *
     * @return List
     */
    @Override
    public List<User> getAllUsers() {
        return repoUser.getAllUsers();
    }


    /**
     * Возвращает список пользователей с ролью.
     *
     * @param role
     * @return
     */
    @Override
    public List<User> getUsersByRole(UserRole role) {
        return repoUser.getUsersByRole(role);
    }


    /**
     * Возвращает список заблокированных пользователей.
     *
     * @return
     */
    @Override
    public List<User> getBlockedUsers() {
        return repoUser.getBlockedUsers();
    }


    /**
     * Авторизует пользователя в системе.
     *
     * @param email    Email пользователя.
     * @param password Пароль пользователя.
     * @return boolean
     */
    @Override
    public boolean loginUser(String email, String password) {
        if (email == null || password == null) {
            throw new SecurityException("Неверный email или пароль!");
        }

        User user = this.repoUser.getUserByEmail(email);

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
    public Account creatAccount(String currencyCode, String title) {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }

        if (!CurrencyValidator.isValidCurrencyCode(currencyCode)) {
            throw new CurrencyCodeValidateExeption("Недопустимый код валюты.");
        }

        return repoAccount.createAccount(
                loggedInUser.getEmail(),
                currencyCode,
                title
        );
    }


    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    @Override
    public List<Account> getAllAccountsByActiveUser() {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован");
        }

        return repoAccount.getAccountsByUserEmail(loggedInUser.getEmail());
    }


    /**
     * Возвращает счет пользователя по его уникальному идентификатору.
     *
     * @param id
     * @return Счет.
     */
    @Override
    public Account getAccountById(int id) {
        return repoAccount.getAccountById(id);
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

        if (currencyCode == null) {
            throw new IllegalArgumentException("Аргумент currencyCode не должен быть null!");
        }

        if (!CurrencyValidator.isValidCurrencyCode(currencyCode)) {
            throw new CurrencyCodeValidateExeption("Недопустимый код валюты.");
        }

        return repoAccount.getAccountsByCurrencyCode(loggedInUser.getEmail(), currencyCode);
    }


    /**
     * Возвращает список счетов пользователя.
     *
     * @param userEmail Email пользователя.
     * @return Список счетов.
     */
    public List<Account> getAccountsByUser(String userEmail) {
        if (userEmail == null) {
            throw new IllegalArgumentException("Аргумент userEmail не должен быть null!");
        }

        Optional<User> user = this.getUser(userEmail);

        if (user.isEmpty()) {
            throw new SecurityException("Пользователь не найден!");
        }

        return repoAccount.getAccountsByUserEmail(user.get().getEmail());
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

        Account account = repoAccount.getAccountById(accountId);

        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден!");
        }

        if (!account.getUserEmail().equals(loggedInUser.getEmail())) {
            throw new SecurityException("Этот счет не принадлежит текущему пользователю!");
        }

        // Взять комиссию.
        BigDecimal fee = BigDecimal.valueOf(0);
        if (accountId > 0) {
            BigDecimal feePercentage = new BigDecimal("0.02");
            fee = money.multiply(feePercentage);
            money = money.subtract(fee);
            this.addCommissionToSystemAccount(0, fee);
        }

        account.setBalance(account.getBalance().add(money));

        Transaction transaction = this.repoTransaction.createTransaction(
                TransactionType.DEPOSIT,
                loggedInUser.getEmail(),
                accountId,
                account.getCurrency(),
                loggedInUser.getEmail(),
                accountId,
                account.getCurrency(),
                money
        );

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

        Account account = repoAccount.getAccountById(accountId);

        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден!");
        }

        if (!account.getUserEmail().equals(loggedInUser.getEmail())) {
            throw new SecurityException("Этот счет не принадлежит текущему пользователю!");
        }

        if (account.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счете!");
        }

        // Взять комиссию.
        BigDecimal fee = BigDecimal.valueOf(0);
        if (accountId > 0) {
            BigDecimal feePercentage = new BigDecimal("0.02");
            fee = money.multiply(feePercentage);
            money = money.subtract(fee);
            this.addCommissionToSystemAccount(0, fee);
        }

        account.setBalance(account.getBalance().subtract(money));

        Transaction transaction = this.repoTransaction.createTransaction(
                TransactionType.WITHDRAW,
                loggedInUser.getEmail(),
                accountId,
                account.getCurrency(),
                loggedInUser.getEmail(),
                accountId,
                account.getCurrency(),
                money
        );

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

        Account account1 = repoAccount.getAccountById(accountId1);
        Account account2 = repoAccount.getAccountById(accountId2);

        if (account1 == null || account2 == null) {
            throw new IllegalArgumentException("Один или оба счета не найдены!");
        }

        if (account1.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счете для обмена!");
        }

        BigDecimal course = null;

        try {
            course = this.crossCourse(
                    account1.getCurrency(),
                    account2.getCurrency()
            );
        } catch (Exception error) {
            System.out.println("+++ " + error.getMessage());
        }

        if (course == null) {
            throw new IllegalArgumentException("Не удалось найти курс для обмена!");
        }

        account1.setBalance(account1.getBalance().subtract(money));

        money = money.multiply(course);

        // Взять комиссию.
        BigDecimal fee = BigDecimal.valueOf(0);
        if (accountId1 > 0) {
            BigDecimal feePercentage = new BigDecimal("0.02");
            fee = money.multiply(feePercentage);
            money = money.subtract(fee);
            this.addCommissionToSystemAccount(0, fee);
        }

        account2.setBalance(account2.getBalance().add(money));

        Transaction transaction = this.repoTransaction.createTransaction(
                TransactionType.TRANSFER,
                loggedInUser.getEmail(),
                accountId1,
                account1.getCurrency(),
                loggedInUser.getEmail(),
                accountId2,
                account2.getCurrency(),
                money,
                course
        );

        return true;
    }


    /**
     * Отправляем комиссию в системный счет.
     *
     * @param systemAccountId
     * @param commission
     */
    public Transaction addCommissionToSystemAccount(int systemAccountId, BigDecimal commission) {
        // Получение системного счёта
        Optional<Account> systemAccount = Optional.ofNullable(repoAccount.getAccountById(systemAccountId));

        if (systemAccount.isEmpty()) {
            throw new IllegalStateException("Системный счёт не найден!");
        }

        // Добавление комиссии
        Account account = systemAccount.get();
        BigDecimal newBalance = account.getBalance().add(commission);
        account.setBalance(newBalance);

        return this.repoTransaction.createTransaction(
                TransactionType.WITHDRAW,
                loggedInUser.getEmail(),
                systemAccountId,
                account.getCurrency(),
                loggedInUser.getEmail(),
                systemAccountId,
                account.getCurrency(),
                commission
        );
    }


    /**
     * Вычисляет кросс-курс между двумя валютами.
     *
     * @param target Код целевой валюты (например, "USD").
     * @param source Код исходной валюты (например, "EUR").
     * @return Кросс-курс между целевой и исходной валютами.
     *
     * @throws IllegalArgumentException Если один из кодов валют null, пуст, или не соответствует допустимому значению.
     */
    @Override
    public BigDecimal crossCourse(String target, String source) {
        if (target == null || source == null || target.isEmpty() || source.isEmpty()) {
            throw new IllegalArgumentException("Коды валют не могут быть null или пустыми.");
        }

        // Если валюты совпадают, курс равен 1.
        if (target.equalsIgnoreCase(source)) {
            return BigDecimal.ONE;
        }

        try {
            // Преобразуем коды валют в enum.
            CurrencyCode targetEnum = CurrencyCode.valueOf(target.toUpperCase());
            CurrencyCode sourceEnum = CurrencyCode.valueOf(source.toUpperCase());

            System.out.println(targetEnum);
            System.out.println(sourceEnum);
            // Получаем курс валют к USD.
            BigDecimal targetRate = this.repoCurrency.getActualRate(targetEnum);
            BigDecimal sourceRate = this.repoCurrency.getActualRate(sourceEnum);

            System.out.println(targetRate);
            System.out.println(sourceRate);

            // Вычисляем кросс-курс: target к source через USD.
            return targetRate.divide(sourceRate, MathContext.DECIMAL64);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректный код валюты: " + e.getMessage());
        }
    }


    /**
     * Возвращает список всех транзакций по id счета.
     *
     * @param id
     * @return
     */
    @Override
    public Transaction getTransactionById(int id)
            throws Exception {
        if (!(id >= 0)) {
            throw new Exception("Неверный id.");
        }

        return this.repoTransaction.getTransactionById(id);
    }


    /**
     * Удаляет счет из списка счетов пользователя.
     *
     * @param id Уникальный идентификатор счета.
     */
    @Override
    public void removeAccount(int id)
            throws Exception {
        if (loggedInUser == null) {
            throw new SecurityException("Пользователь не авторизован!");
        }

        Account account = repoAccount.getAccountById(id);

        if (account == null) {
            throw new IllegalArgumentException("Счет с таким ID не найден!");
        }

        if (!account.getUserEmail().equals(loggedInUser.getEmail())) {
            throw new SecurityException("Этот счет не принадлежит текущему пользователю!");
        }

        // Проверяем, что на счёте нет денег
        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new Exception("Невозможно удалить счёт с положительным балансом. Пожалуйста, сначала обнулите баланс.");
        }

        repoAccount.removeAccount(id);
    }


    /**
     * @param userEmail
     */
    @Override
    public void blockUser(String userEmail)
            throws Exception {
        if (userEmail == null) {
            throw new Exception("Аргумент userEmail не может быть null!");
        }

        User user = this.repoUser.getUserByEmail(userEmail);

        if (user == null) {
            throw new Exception("Пользователь с указанным email не найден в базе!");
        }

        user.setRole(UserRole.BLOCKED);
    }


    /**
     * @param userEmail
     */
    @Override
    public void unblockUser(String userEmail)
            throws Exception {
        if (userEmail == null) {
            throw new Exception("Аргумент userEmail не может быть null!");
        }

        User user = this.repoUser.getUserByEmail(userEmail);

        if (user == null) {
            throw new Exception("Пользователь с указанным email не найден в базе!");
        }

        user.setRole(UserRole.USER);
    }


    /**
     * @param historyCurrency
     */
    @Override
    public void getCurrencyRateHistory(String historyCurrency) {
        // TODO: getCurrencyRateHistory()
    }


    /**
     * @param userId
     * @param newRole
     */
    @Override
    public void changeUserRole(int userId, String newRole) {
        // TODO: changeUserRole()
    }


    /**
     * @param currency
     * @param newRate
     */
    @Override
    public void updateCurrencyRate(String currency, BigDecimal newRate) {
        // TODO: updateCurrencyRate()
    }

    /**
     * @param startDate
     * @param endDate
     */
    @Override
    public void exportTransactions(String startDate, String endDate) {
        // TODO: exportTransactions()
    }


    /**
     * @param filePath
     */
    @Override
    public void importCurrencyRates(String filePath) {
        // TODO: importCurrencyRates()
    }


    /**
     * @param accountId
     * @return
     */
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        return this.repoTransaction.getTransactionsByAccountId(accountId);
    }


    private void validateEmailAndPassword(String email, String password) {
        if (!EmailValidator.isValidEmail(email)) {
            throw new EmailValidateException("Email не прошел проверку!");
        }
        if (!PasswordValidator.isValidPassword(password)) {
            throw new PasswordValidateException("Пароль не прошел проверку!");
        }
    }
}
