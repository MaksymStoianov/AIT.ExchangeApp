package model;

import java.util.Objects;

/**
 * Класс представляет пользователя.
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class User {

    // Имя пользователя.
    private String firstName;

    // Фамилия пользователя.
    private String lastName;

    // Email пользователя.
    private final String email;

    // Пароль пользователя.
    private String password;

    // Роль пользователя.
    private UserRole role;


    /**
     * Конструктор для создания объекта {@code User}.
     *
     * @param email    Email пользователя.
     * @param password Пароль пользователя.
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = UserRole.USER;
    }


    /**
     * Конструктор для создания объекта {@code User}.
     *
     * @param email    Email пользователя.
     * @param password Пароль пользователя.
     * @param role     Роль пользователя.
     */
    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }


    /**
     * Конструктор для создания объекта {@code User}.
     *
     * @param email     Email пользователя.
     * @param password  Пароль пользователя.
     * @param role      Роль пользователя.
     * @param firstName Имя пользователя.
     * @param lastName  Фамилия пользователя.
     */
    public User(String email, String password, UserRole role, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
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
     * Возвращает имя пользователя.
     *
     * @return Имя пользователя.
     */
    public String getFirstName() {
        return this.firstName;
    }


    /**
     * Устанавливает имя пользователя.
     *
     * @param firstName Имя пользователя.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Возвращает фамилию пользователя.
     *
     * @return Фамилия пользователя.
     */
    public String getLastName() {
        return this.lastName;
    }


    /**
     * Устанавливает фамилию пользователя.
     *
     * @param lastName Фамилия пользователя.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
