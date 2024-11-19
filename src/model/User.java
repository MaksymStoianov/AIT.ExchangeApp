package model;

import java.util.Objects;

/**
 * UserImpl
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class User {

  /**
   * Содержит email пользователя.
   */
  private String email;


  /**
   * Содержит пароль пользователя.
   */
  private String password;


  /**
   * Содержит роль пользователя.
   */
  private UserRole role;


  public User(String email, String password) {
    this.email = email;
    this.password = password;
    this.role = UserRole.USER;
  }


  public User(String email, String password, UserRole role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }


  /**
   * Возвращает email пользователя.
   *
   * @return Email пользователя.
   */
  public String getEmail() {
    return this.email;
  }


  /**
   * Устанавливает email пользователя.
   *
   * @param email Email пользователя.
   */
  public void setEmail(String email) {
    this.email = email;
  }


  /**
   * Возвращает пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  public String getPassword() {
    return this.password;
  }


  /**
   * Устанавливает пароль пользователя.
   *
   * @param password Пароль пользователя.
   */
  public void setPassword(String password) {
    this.password = password;
  }


  /**
   * Возвращает роль пользователя.
   *
   * @return Роль пользователя.
   */
  public UserRole getRole() {
    return this.role;
  }


  /**
   * Устанавливает роль пользователя.
   *
   * @param role Роль пользователя.
   */
  public void setRole(UserRole role) {
    this.role = role;
  }


  /**
   * Определяет, обладает ли текущий пользователь правами администратора.
   *
   * @return {@code true}, если пользователь является администратором; {@code false} в противном случае.
   */
  public boolean isAdmin() {
    return this.role.equals(UserRole.ADMIN);
  }


  /**
   * Определяет, заблокирован ли текущий пользователь.
   *
   * @return {@code true}, если пользователь заблокирован; {@code false} в противном случае.
   */
  public boolean isBlocked() {
    return this.role.equals(UserRole.BLOCKED);
  }


  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(email, user.email);
  }


  public int hashCode() {
    return Objects.hashCode(email);
  }


  /**
   * Возвращает строковое представление объекта.
   *
   * @return Строковое представление объекта.
   */
  public String toString() {
    return this.email;
  }

}
