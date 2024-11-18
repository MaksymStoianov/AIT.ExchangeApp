package model;

public interface User {

  /**
   * Содержит email пользователя.
   */
  String email = null;


  /**
   * Содержит пароль пользователя.
   */
  String password = null;


  /**
   * Содержит роль пользователя.
   */
  UserRole role = null;


  /**
   * Возвращает email пользователя.
   *
   * @return Email пользователя.
   */
  String getEmail();


  /**
   * Устанавливает email пользователя.
   *
   * @param email Email пользователя.
   */
  void setEmail(String email);


  /**
   * Возвращает пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  String getPassword();


  /**
   * Устанавливает пароль пользователя.
   *
   * @param password Пароль пользователя.
   */
  void setPassword(String password);


  /**
   * Возвращает роль пользователя.
   *
   * @return Роль пользователя.
   */
  UserRole getRole();


  /**
   * Устанавливает роль пользователя.
   *
   * @param role Роль пользователя.
   */
  void setRole(UserRole role);

}
