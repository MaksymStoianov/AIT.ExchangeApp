package repository;

import model.User;
import model.UserRole;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * UserRepository
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public interface UserRepository {

  /**
   * key - UserId; value - User
   */
  Map<String, User> users = new LinkedHashMap<>();


  /**
   * Добавляет нового пользователя с указанной электронной почтой и паролем в репозиторий.
   *
   * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
   * @param password Пароль пользователя.
   * @return Объект пользователя {@code User}.
   */
  User addUser(String email, String password);


  /**
   * Возвращает список пользователей, имеющих указанные роли.
   *
   * @param roles Роли, по которым осуществляется фильтрация пользователей.
   * @return {@code List<User>} Список пользователей, соответствующих указанным ролям.
   */
  public List<User> getUsersByRole(UserRole... roles);


  /**
   * Находит и возвращает пользователя по заданному адресу электронной почты.
   *
   * @param email Электронная почта пользователя для поиска.
   * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
   */
  public User getUserByEmail(String email);


  /**
   * Проверяет, существует ли пользователь с заданным адресом электронной почты.
   *
   * @param email Электронная почта для проверки.
   * @return {@code true}, если пользователь с указанным адресом существует; {@code false} иначе.
   */
  boolean isEmailExists(String email);

}
