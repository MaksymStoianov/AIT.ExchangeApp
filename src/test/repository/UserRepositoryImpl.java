package test.repository;

import model.User;
import model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование класса {@link repository.UserRepositoryImpl}.
 */
class UserRepositoryImpl {

    private repository.UserRepositoryImpl userRepository;

    /**
     * Метод, который выполняется перед каждым тестом для инициализации объекта репозитория.
     */
    @BeforeEach
    void setUp() {
        userRepository = new repository.UserRepositoryImpl();
    }

    /**
     * Тестирует добавление нового пользователя в репозиторий.
     * Проверяет, что добавленный пользователь существует в репозитории.
     */
    @Test
    void addUser() {
        String email = "testuser@example.com";
        String password = "password123";

        // Добавление нового пользователя
        User user = userRepository.addUser(email, password);

        // Проверка, что пользователь был добавлен
        assertNotNull(user, "Пользователь не был добавлен!");
        assertEquals(email, user.getEmail(), "Email пользователя не совпадает");
        assertEquals(password, user.getPassword(), "Пароль пользователя не совпадает");
    }

    /**
     * Тестирует добавление пользователя с ролью.
     * Проверяет, что пользователь с указанной ролью добавляется в репозиторий.
     */
    @Test
    void testAddUserWithRole() {
        String email = "admin@example.com";
        String password = "adminPassword";
        UserRole role = UserRole.ADMIN;

        // Добавление пользователя с ролью администратора
        User user = userRepository.addUser(email, password, role);

        // Проверка, что роль установлена корректно
        assertNotNull(user, "Пользователь не был добавлен!");
        assertEquals(role, user.getRole(), "Роль пользователя не совпадает");
    }

    /**
     * Тестирует получение пользователя по email.
     * Проверяет, что метод {@link repository.UserRepositoryImpl#getUserByEmail} работает корректно.
     */
    @Test
    void getUserByEmail() {
        String email = "testuser@example.com";
        String password = "password123";

        // Добавление пользователя
        userRepository.addUser(email, password);

        // Получение пользователя по email
        User user = userRepository.getUserByEmail(email);

        // Проверка, что полученный пользователь совпадает с добавленным
        assertNotNull(user, "Пользователь не найден!");
        assertEquals(email, user.getEmail(), "Email пользователя не совпадает");
    }

    /**
     * Тестирует получение всех пользователей.
     * Проверяет, что метод {@link repository.UserRepositoryImpl#getAllUsers} возвращает список всех пользователей.
     */
    @Test
    void getAllUsers() {
        String email1 = "user1@example.com";
        String email2 = "user2@example.com";
        String password = "password123";

        // Добавление пользователей
        userRepository.addUser(email1, password);
        userRepository.addUser(email2, password);

        // Получение всех пользователей
        var users = userRepository.getAllUsers();

        // Проверка, что все добавленные пользователи присутствуют в списке
        assertEquals(2, users.size(), "Неверное количество пользователей");
        assertTrue(users.stream().anyMatch(user -> user.getEmail().equals(email1)),
                "Пользователь с email " + email1 + " не найден!");
        assertTrue(users.stream().anyMatch(user -> user.getEmail().equals(email2)),
                "Пользователь с email " + email2 + " не найден!");
    }

    /**
     * Тестирует получение пользователей по роли.
     * Проверяет, что метод {@link repository.UserRepositoryImpl#getUsersByRole} фильтрует пользователей по заданной роли.
     */
    @Test
    void getUsersByRole() {
        String email1 = "admin@example.com";
        String email2 = "user@example.com";
        String password = "password123";

        // Добавление пользователей с разными ролями
        userRepository.addUser(email1, password, UserRole.ADMIN);
        userRepository.addUser(email2, password, UserRole.USER);

        // Получение пользователей по роли
        var admins = userRepository.getUsersByRole(UserRole.ADMIN);
        var users = userRepository.getUsersByRole(UserRole.USER);

        // Проверка, что список администраторов содержит только администратора
        assertEquals(1, admins.size(), "Неверное количество администраторов");
        assertEquals(email1, admins.get(0).getEmail(), "Администратор не найден!");

        // Проверка, что список обычных пользователей содержит только обычного пользователя
        assertEquals(1, users.size(), "Неверное количество обычных пользователей");
        assertEquals(email2, users.get(0).getEmail(), "Обычный пользователь не найден!");
    }

    /**
     * Тестирует получение списка администраторов.
     * Проверяет, что метод {@link repository.UserRepositoryImpl#getAdmins} возвращает только пользователей с ролью ADMIN.
     */
    @Test
    void getAdmins() {
        String email1 = "admin@example.com";
        String email2 = "user@example.com";
        String password = "password123";

        // Добавление пользователей с разными ролями
        userRepository.addUser(email1, password, UserRole.ADMIN);
        userRepository.addUser(email2, password, UserRole.USER);

        // Получение списка администраторов
        var admins = userRepository.getAdmins();

        // Проверка, что список администраторов содержит только администратора
        assertEquals(1, admins.size(), "Неверное количество администраторов");
        assertEquals(email1, admins.get(0).getEmail(), "Неверный администратор в списке");
    }

    /**
     * Тестирует получение заблокированных пользователей.
     * Проверяет, что метод {@link repository.UserRepositoryImpl#getBlockedUsers} возвращает только пользователей с ролью BLOCKED.
     */
    @Test
    void getBlockedUsers() {
        String email1 = "blocked@example.com";
        String email2 = "user@example.com";
        String password = "password123";

        // Добавление пользователей с разными ролями
        userRepository.addUser(email1, password, UserRole.BLOCKED);
        userRepository.addUser(email2, password, UserRole.USER);

        // Получение списка заблокированных пользователей
        var blockedUsers = userRepository.getBlockedUsers();

        // Проверка, что список заблокированных пользователей содержит только заблокированных пользователей
        assertEquals(1, blockedUsers.size(), "Неверное количество заблокированных пользователей");
        assertEquals(email1, blockedUsers.get(0).getEmail(), "Неверный заблокированный пользователь в списке");
    }

    /**
     * Тестирует проверку существования пользователя по email.
     * Проверяет, что метод {@link repository.UserRepositoryImpl#isEmailExists} правильно определяет наличие пользователя по email.
     */
    @Test
    void isEmailExists() {
        String email = "testuser@example.com";
        String password = "password123";

        // Добавление пользователя
        userRepository.addUser(email, password);

        // Проверка существования пользователя
        assertTrue(userRepository.isEmailExists(email), "Пользователь с таким email должен существовать!");
        assertFalse(userRepository.isEmailExists("nonexistent@example.com"), "Пользователь с таким email не должен существовать!");
    }
}