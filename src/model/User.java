package model;

import java.util.List;
import java.util.Map;

public interface User {

  /**
   * Возвращает email пользователя.
   *
   * @return Email пользователя.
   */
  public String getEmail();


  public String setEmail(String email);


  public String getPassword();


  public String setPassword(String password);


  public UserRole getRole();


  public void setRole(UserRole role);


  /**
   * Добавляет счет к пользователю в определенной валюте.
   *
   * @param title Название счета.
   * @param currency Название валюты.
   * @return Счет.
   */
  public Account creatAccount(String title, String currency);


  /**
   * Возвращает список всех счетов пользователя.
   *
   * @return Список всех счетов пользователя.
   */
  public List<Account> getAllAccounts();


  /**
   * Возвращает счет пользователя по его уникальному идентификатору.
   *
   * @return Счет.
   */
  public Account getAccountById(int id);


  public List<Account> getAccountsByCurrency(String currency);


  /**
   * Удаляет счет из списка счетов пользователя.
   *
   * @param id Уникальный идентификатор счета.
   */
  public void removeAccount(int id);


  public String toString();

}
