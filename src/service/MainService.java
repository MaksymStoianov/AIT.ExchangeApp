package service;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MainService {



  /**
   * бд по rate
   */
  Map<String, Rate> currencyRates = new HashMap<>();


  /**
   * Добавляет пользователя. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
   *
   * @param email
   * @param password
   * @return
   */
  boolean registerUser(String email, String password)
      throws UserIsExistsExeption;


  /**
   * Добавляет пользователя с ролью. Проверяет, если пользователь есть базе то мы возвращаем ошибку.
   *
   * @param email
   * @param password
   * @param role
   * @return
   */
   boolean registerUser(String email, String password, UserRole role)
      throws UserIsExistsExeption;


  /**
   * Возвращает пользователя по его email.
   *
   * @param email
   * @return
   */
  public User getUser(String email);


  /**
   * Возвращает список всех пользователей.
   *
   * @return List
   */
  List<User> getAllUsers();


  /**
   * Возвращает список пользователей с ролью.
   *
   * @return
   */
  List<User> getUsersByRole(UserRole role);


  /**
   * Возвращает список заблокированных пользователей.
   *
   * @return
   */
  List<User> getBlockedUsers();


  /**
   * Авторизует пользователя в системе.
   *
   * @param email
   * @param password
   * @return boolean
   */
  boolean loginUser(String email, String password);


  /**
   * Выходит из системы.
   */
  void logout();
    /**
     * Добавляет счет к пользователю в определенной валюте.
     *
     * @param title        Название счета.
     * @param currencyCode Код валюты.
     * @return Счет.
     */
    Account creatAccount(String userEmail,String title, String currencyCode);

    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    List<Account> getAllAccounts(String userEmail);


    /**
     * Возвращает счет пользователя по его уникальному идентификатору.
     *
     * @return Счет.
     */
    Account getAccountById(int id);


    /**
     * Возвращает список счетов по коду валюты.
     *
     * @param currencyCode
     * @return
     */
    List<Account> getAccountsByCurrency(String userEmail ,String currencyCode);

    /**
     * Снимает сумму со счета. Этот метод должен вернуть Ошибку если пользователь не залогинен.
     *
     * @param accountId
     * @param money
     */
    boolean withdrawal(int accountId, BigDecimal money);


    /**
     * Переводит сумму между счетами. Если происходит перевод между счетами в разных валютах, обмен делается через USD.
     *
     * @param accountId1
     * @param accountId2
     * @param money
     * @return
     */
    boolean exchange(int accountId1, int accountId2, BigDecimal money);

    /**
   * Возвращает кросс-курс валюты.
   *
   * @param target Символ валюты 1.
   * @param source Символ валюты 2.
   * @return
   */
  BigDecimal crossCourse(String target, String source);


  /**
   * Возвращает список всех транзакций по id счета.
   *
   * @param id
   * @return
   */
 Transaction getTransactionsById(int id);


  /**
   * Добавляет сумму к счету. Этот метод должен вернуть Ошибку если пользователь не залогинен.
   *
   * @param accountId
   * @param money
   */
  boolean deposit(int accountId, BigDecimal money);



  /**
   * Удаляет счет из списка счетов пользователя.
   *
   * @param id Уникальный идентификатор счета.
   */
  void removeAccount(int id);


}
