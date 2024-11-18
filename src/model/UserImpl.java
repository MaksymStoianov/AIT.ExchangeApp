package model;

import java.util.Objects;

/**
 * UserImpl
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class UserImpl implements User {

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


  public UserImpl(String email, String password) {
    this.email = email;
    this.password = password;
    this.role = UserRole.USER;
  }


  public UserImpl(String email, String password, UserRole role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }


  /**
   * Возвращает email пользователя.
   *
   * @return Email пользователя.
   */
  @Override
  public String getEmail() {
    return this.email;
  }


  /**
   * Устанавливает email пользователя.
   *
   * @param email Email пользователя.
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }


  /**
   * Возвращает пароль пользователя.
   *
   * @return Пароль пользователя.
   */
  @Override
  public String getPassword() {
    return this.password;
  }


  /**
   * Устанавливает пароль пользователя.
   *
   * @param password Пароль пользователя.
   */
  @Override
  public void setPassword(String password) {
    this.password = password;
  }


  /**
   * Возвращает роль пользователя.
   *
   * @return Роль пользователя.
   */
  @Override
  public UserRole getRole() {
    return this.role;
  }


  /**
   * Устанавливает роль пользователя.
   *
   * @param role Роль пользователя.
   */
  @Override
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


  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserImpl user = (UserImpl) o;
    return Objects.equals(email, user.email);
  }


  @Override
  public int hashCode() {
    return Objects.hashCode(email);
  }


  /**
   * Возвращает строковое представление объекта.
   *
   * @return Строковое представление объекта.
   */
  @Override
  public String toString() {
    return this.email;
  }

}
