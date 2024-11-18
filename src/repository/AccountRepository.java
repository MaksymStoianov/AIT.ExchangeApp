package repository;

import model.Account;
import model.Transaction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface AccountRepository {

  /**
   * Список пользователей.
   * <p>{@code key} - email пользователя;</p>
   * <p>{@code value} - список счетов пользователя.</p>
   */
  Map<String, List<Account>> users = new LinkedHashMap<>();


  /**
   * Список транзакций по счету.
   * <p>{@code key} - ID счета;</p>
   * <p>{@code value} - список транзакций по счету.</p>.
   */
  Map<Integer, List<Transaction>> transactions = new LinkedHashMap<>();


  /**
   * Создает новый счет для пользователя.
   *
   * @param userEmail       Идентификатор пользователя.
   * @param title        Название счет.
   * @param currencyCode Код валюты.
   * @return Созданный счет.
   */
  Account createAccount(String userEmail, String title, String currencyCode);


  /**
   * Получает счет по его идентификатору.
   *
   * @param id Идентификатор счет.
   * @return Найденный счет или {@code null}, если не найден.
   */
  Account getAccountById(int id);


  /**
   * Получает все счета пользователя.
   *
   * @param userEmail Идентификатор пользователя.
   * @return Список всех счетов пользователя.
   */
  List<Account> getAllAccounts(String userEmail);


  /**
   * Получает счет пользователя по коду валюты.
   *
   * @param userEmail       Идентификатор пользователя.
   * @param currencyCode Код валюты.
   * @return Список счетов с указанным кодом валюты.
   */
  List<Account> getAccountsByCurrency(String userEmail, String currencyCode);


  /**
   * Удаляет счет пользователя по его идентификатору.
   *
   * @param id Идентификатор счета.
   */
  void removeAccount(int id);


  /**
   * Удаляет счет из списка счетов пользователя.
   *
   * @param account Счет.
   */
  void removeAccount(Account account);

}
