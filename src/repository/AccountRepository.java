package repository;

import model.Account;
import model.Transaction;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface AccountRepository {
  Map<LocalDateTime, Transaction> transactionsHistory = new LinkedHashMap<>();


  /**
   * Добавляет счет к пользователю в определенной валюте.
   *
   * @param title    Название счета.
   * @param currency Название валюты.
   * @return Счет.
   */
  Account creatAccount(String title, String currency);


  /**
   * Возвращает список всех счетов пользователя.
   *
   * @return Список всех счетов пользователя.
   */
  List<Account> getAllAccounts();


  /**
   * Возвращает счет пользователя по его уникальному идентификатору.
   *
   * @return Счет.
   */
  Account getAccountById(int id);


  /**
   * Возвращает список счетов по коду валюты.
   *
   * @param currency
   * @return
   */
  List<Account> getAccountsByCurrency(String currency);


  /**
   * Удаляет счет из списка счетов пользователя.
   *
   * @param id Уникальный идентификатор счета.
   */
  void removeAccount(int id);


  /**
   * Удаляет счет из списка счетов пользователя.
   *
   * @param account Счет.
   */
  void removeAccount(Account account);

}
