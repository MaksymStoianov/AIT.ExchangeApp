package repository;

import model.User;
import model.UserRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public interface UserRepository {

    /**
     * Список пользователей.
     * <p>{@code key} - email пользователя;</p>
     * <p>{@code value} - объект пользователя.</p>
     */
    Map<String, User> users = new HashMap<>();


    /**
     * Добавляет нового пользователя с указанной электронной почтой и паролем в репозиторий.
     *
     * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
     * @param password Пароль пользователя.
     * @return Объект пользователя {@code User}.
     */
    User addUser(String email, String password);


    /**
     * Добавляет нового пользователя с указанной электронной почтой, паролем и ролью в репозиторий.
     *
     * @param email    Электронная почта пользователя. Должна быть уникальной в системе.
     * @param password Пароль пользователя.
     * @param role     Роль пользователя.
     * @return Объект пользователя {@code User}.
     */
    User addUser(String email, String password, UserRole role);


    /**
     * Находит и возвращает пользователя по заданному адресу электронной почты.
     *
     * @param email Электронная почта пользователя для поиска.
     * @return Объект пользователя {@code User}, если найден; {@code null} иначе.
     */
    User getUserByEmail(String email);


    /**
     * Возвращает список всех пользователей.
     *
     * @return {@code List<User>} Список всех пользователей.
     */
    List<User> getAllUsers();


    /**
     * Возвращает список пользователей, имеющих указанную роль.
     *
     * @param role Роль, по которой осуществляется фильтрация пользователей.
     * @return {@code List<User>} Список пользователей, соответствующих указанной роли.
     */
    List<User> getUsersByRole(UserRole role);


    /**
     * Возвращает список администраторов.
     *
     * @return {@code List<User>} Список администраторов.
     */
    List<User> getAdmins();


    /**
     * Возвращает список заблокированных пользователей.
     *
     * @return {@code List<User>} Список заблокированных пользователей.
     */
    List<User> getBlockedUsers();


    /**
     * Проверяет, существует ли пользователь с заданным адресом электронной почты.
     *
     * @param email Электронная почта для проверки.
     * @return {@code true}, если пользователь с указанным адресом существует; {@code false} иначе.
     */
    boolean isEmailExists(String email);

}
