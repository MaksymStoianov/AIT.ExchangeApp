package repository;

import model.User;
import model.UserImpl;
import model.UserRole;

import java.util.*;
import java.util.stream.Collectors;

/**
 * UserRepositoryImpl
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class UserRepositoryImpl implements UserRepository {

  /**
   * Список пользователей.
   * <p>{@code key} - email пользователя;</p>
   * <p>{@code value} - объект пользователя.</p>
   */
  private final Map<String, User> users = new HashMap<>();


  /**
   * Добавляет нового пользователя с указанной электронной почтой и паролем в репозиторий.
   *
   * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
   * @param password Пароль пользователя.
   * @return Объект пользователя {@code User}.
   */
  @Override
  public User addUser(String email, String password)
      throws IllegalArgumentException {
    if (email == null || password == null) {
      throw new IllegalArgumentException("Email и Password не должны быть null!");
    }

    email = email.toLowerCase();

    if (this.isEmailExists(email)) {
      throw new IllegalArgumentException("Пользователь с таким email уже существует!");
    }

    User user = new UserImpl(email, password);

    this.users.put(email, user);

    return user;
  }


  /**
   * Добавляет нового пользователя с указанной электронной почтой и паролем в репозиторий.
   *
   * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
   * @param password Пароль пользователя.
   * @return Объект пользователя {@code User}.
   */
  @Override
  public User addUser(String email, String password, UserRole role)
      throws IllegalArgumentException {
    if (email == null || password == null) {
      throw new IllegalArgumentException("Email и Password не должны быть null!");
    }

    email = email.toLowerCase();

    if (this.isEmailExists(email)) {
      throw new IllegalArgumentException("Пользователь с таким email уже существует!");
    }

    User user = new UserImpl(email, password, role);

    this.users.put(email, user);

    return user;
  }


  /**
   * Находит и возвращает пользователя по заданному адресу электронной почты.
   *
   * @param email Электронная почта пользователя для поиска.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  @Override
  public User getUserByEmail(String email) {
    if (email == null) {
      throw new IllegalArgumentException("Аргумент email не может быть null!");
    }

    return this.users.getOrDefault(email.toLowerCase(), null);
  }


  /**
   * Возвращает список всех пользователей.
   *
   * @return {@code List<User>} Список всех пользователей.
   */
  public List<User> getAllUsers() {
    return new ArrayList<>(this.users.values());
  }


  /**
   * Возвращает список пользователей, имеющих указанную роль.
   *
   * @param role Роль, по которой осуществляется фильтрация пользователей.
   * @return {@code List<User>} Список пользователей, соответствующих указанной роли.
   */
  @Override
  public List<User> getUsersByRole(UserRole role)
      throws IllegalArgumentException {
    if (role == null) {
      throw new IllegalArgumentException("Аргумент role не может быть null!");
    }

    return this
        .getAllUsers()
        .stream()
        .filter(item -> item.getRole() == role)
        .collect(Collectors.toList());
  }


  /**
   * Возвращает список администраторов.
   *
   * @return {@code List<User>} Список администраторов.
   */
  @Override
  public List<User> getAdmins() {
    return this.getUsersByRole(UserRole.ADMIN);
  }


  /**
   * Возвращает список заблокированных пользователей.
   *
   * @return {@code List<User>} Список заблокированных пользователей.
   */
  @Override
  public List<User> getBlockedUsers() {
    return this.getUsersByRole(UserRole.BLOCKED);
  }


  /**
   * Проверяет, существует ли пользователь с заданным адресом электронной почты.
   *
   * @param email Электронная почта для проверки.
   * @return {@code true}, если пользователь с указанным адресом существует; {@code false} иначе.
   */
  @Override
  public boolean isEmailExists(String email) {
    if (email == null) {
      return false;
    }

    return this.users.containsKey(email.toLowerCase());
  }

}
