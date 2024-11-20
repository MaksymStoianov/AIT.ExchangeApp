package view;

import model.Account;
import model.Transaction;
import model.User;
import service.MainService;
import service.UserIsExistsExeption;
import utils.EmailValidateException;
import utils.PasswordValidateException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * MenuImpl
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class Menu {

    private final Color primaryColor = Color.CYAN;
    private final Color secondaryColor = Color.ORANGE;
    private final int width = 50;
    private final boolean isBorder = true;


    /**
     * Сканер.
     */
    private final Scanner scanner = new Scanner(System.in);


    /**
     * Сервис.
     */
    private final MainService service;


    public Menu(MainService service) {
        this.service = service;
    }


    /**
     * Показывает экран загрузки программы.
     */
    private void loading()
            throws InterruptedException {
        System.out.println();

        String text = "Загрузка ...";
        int newWidth = width + 4;
        int paddingLeft = (newWidth - text.length()) / 2;
        int paddingRight = newWidth - text.length() - paddingLeft;

        text = " ".repeat(paddingLeft)
                + text
                + " ".repeat(paddingRight);

        int length = text.length();

        System.out.print(TextStyle.REVERSE + text + Color.RESET);

        for (int i = 0; i < length; i++) {
            Thread.sleep(25);
            System.out.print("\r");
            System.out.flush();

            System.out.print(
                    TextStyle.REVERSE + "" + Color.GREEN +
                            text.substring(0, i + 1) +
                            Color.RESET +
                            TextStyle.REVERSE +
                            text.substring(i + 1) +
                            Color.RESET
            );
        }

        Thread.sleep(500);

        System.out.print("\r");
    }


    /**
     * Показывает стартовое меню.
     */
    private void printMenuStart() {
        boolean isRunning = true;

        Map<Integer, String> menu = new LinkedHashMap<>();
        String description = "Добро пожаловать в главное меню обменного пункта валюты.";

        // TODO: Получить активного пользователя.
        User user = null;

        // Добавляем элементы
        if (user == null) {
            description += "\nПожалуйста, "
                    + TextStyle.BOLD + TextStyle.UNDERLINE
                    + "выполните вход"
                    + Color.RESET
                    + " в систему или "
                    + TextStyle.BOLD + TextStyle.UNDERLINE
                    + "создайте новый аккаунт" + TextStyle.RESET + ".";

            menu.put(1, "Вход");
            menu.put(2, "Регистрация");
        } else {
            description += String.format(
                    "\nВы вошли в систему как: " + this.secondaryColor + "%s." + Color.RESET,
                    user.getEmail()
            );

            menu.put(3, "Меню пользователя");

            if (user.isAdmin()) {
                menu.put(4, "Меню администратора");
            }

            menu.put(0, Color.RED + "⏻ Выход" + Color.RESET);
        }

        menu.put(5, "Курс валют");
        menu.put(6, "О программе");
//        menu.put(-1, "Предыдущее меню");

        this.printMenu(
                "Главное меню",
                description,
                menu,
                this.primaryColor + "Введите пункт меню: " + Color.RESET
        );


        while (isRunning) {
            try {
                int choice = this.scanner.nextInt();
                this.scanner.nextLine();
                this.handleMainMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("Упс... ошибка!");
                System.out.println(e.getMessage());
                this.waitRead();
                System.out.print("Пожалуйста, попробуйте еще раз: ");
            }
        }
    }


    /**
     * @param input
     * @throws Exception
     */
    private void handleMainMenuChoice(int input)
            throws Exception {
        switch (input) {
            // Выход
            case 0:
                System.out.println("Вы вышли!");
                // TODO: Пункт 0
                break;

            case 1:
                System.out.println("Вы выбрали пункт " + input);
                // TODO: Пункт 1
                break;

            case 2:
                System.out.println("Вы выбрали пункт " + input);
                this.printUserRegistration();
                break;

            case 3:
                System.out.println("Вы выбрали пункт " + input);
                this.printMenuAdmin();
                break;

            case 4:
                System.out.println("Вы выбрали пункт " + input);
                this.printMenuUser();
                break;

            default:
                throw new Exception("Некорректный ввод.");
        }
    }

    private void printUserRegistration() {
        System.out.println("Регистрация пользователя");
        System.out.println("Введите email");
        String email = this.scanner.nextLine();
        System.out.println("Требования к паролю: не менее 8 символов, должен содержать: цифру, спец символ, маленькую и большую букву");
        System.out.println("Введите пароль:");
        String password = this.scanner.nextLine();
        //Optional<User> optionalUser = Optional.empty();
        Boolean userCreated = false;
        try {
            userCreated = service.registerUser(email, password);

        } catch (EmailValidateException e) {
            System.out.println("Пользователь не зарегистрирован");
            System.out.println(e.getMessage());
            return;
        } catch (PasswordValidateException e) {
            System.out.println("Пользователь не зарегистрирован");
            System.out.println(e.getMessage());
            return;
        } catch (UserIsExistsExeption e) {
            System.out.println("Пользователь не зарегистрирован");
            System.out.println(e.getMessage());
            return;
        }

        System.out.printf("Пользователь с email %s успешно зарегистрирован.\n", email);

    }


    /**
     * Показывает меню администратора. 1. заблокировать пользователя 2. разблокировать пользователя 3. посмотреть список
     * всех пользователей 4. посмотреть список заблокированных пользователей 5. посмотреть список транзакций у
     * конкретноно пользователя 6. посмотреть счета у конкретноно пользователя
     */
    private void printMenuAdmin() {
        boolean isRunning = true;

        Map<Integer, String> adminMenu = new LinkedHashMap<>();
        String description = "Добро пожаловать в меню администратора.";

        // Добавляем элементы меню администратора
        adminMenu.put(1, "Сменить роль пользователя");
        adminMenu.put(2, "Изменить курс валюты");
        adminMenu.put(3, "Экспорт транзакций пользователей");
        adminMenu.put(4, "Импорт изменения курса валюты");
        adminMenu.put(5, "История изменения курса валюты");
        adminMenu.put(6, "Заблокировать пользователя");
        adminMenu.put(7, "Разблокировать пользователя");
        adminMenu.put(8, "Посмотреть список всех пользователей");
        adminMenu.put(9, "Посмотреть список заблокированных пользователей");
        adminMenu.put(10, "Посмотреть список транзакций пользователя");
        adminMenu.put(11, "Посмотреть счета пользователя");

        // Пункт для возврата в предыдущее меню
        adminMenu.put(-1, "Предыдущее меню");

        // Пункт выхода в главное меню
        adminMenu.put(0, Color.RED + "⏻ Выход в главное меню" + Color.RESET);

        // Печатаем меню
        this.printMenu(
                "Меню администратора",
                description,
                adminMenu,
                this.primaryColor + "Введите пункт меню: " + Color.RESET
        );

        while (isRunning) {
            try {
                int choice = this.scanner.nextInt();
                this.scanner.nextLine();
                this.handleAdminMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("Упс... ошибка!");
                System.out.println(e.getMessage());
                this.waitRead();
                System.out.print("Пожалуйста, попробуйте еще раз: ");
            }
        }
    }



    /**
     * Обрабатывает выбор пользователя в меню администратора.
     *
     * @param input Ввод пользователя.
     * @throws Exception
     */
    /**
     * Обрабатывает выбор пользователя в меню администратора.
     * В зависимости от выбранного пункта меню, выполняются различные действия, такие как смена роли пользователя,
     * изменение курса валюты, блокировка пользователя и другие административные операции.
     *
     * @param input Ввод пользователя, указывающий на выбранный пункт меню.
     * @throws Exception Исключение, если ввод пользователя некорректен или операция не выполнена.
     */
    private void handleAdminMenuChoice(int input) throws Exception {
        switch (input) {
            case 1:
                // Сменить роль пользователя
                System.out.println("Введите ID пользователя для смены роли:");
                int userId = this.scanner.nextInt();
                this.scanner.nextLine(); // Очистка буфера
                System.out.println("Введите новую роль для пользователя:");
                String newRole = this.scanner.nextLine();

                try {
                    service.changeUserRole(userId, newRole);
                    System.out.printf("Роль пользователя с ID %d изменена на %s.\n", userId, newRole);
                } catch (Exception e) {
                    System.out.println("Не удалось сменить роль.");
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                // Изменить курс валюты
                System.out.println("Введите новую валюту:");
                String currency = this.scanner.nextLine();
                System.out.println("Введите новый курс для валюты " + currency + ":");
                BigDecimal newRate = this.scanner.nextBigDecimal();
                try {
                    service.updateCurrencyRate(currency, newRate);
                    System.out.printf("Курс валюты %s изменён на %s.\n", currency, newRate);
                } catch (Exception e) {
                    System.out.println("Не удалось изменить курс валюты.");
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                // Экспорт транзакций пользователей
                System.out.println("Введите дату начала для экспорта транзакций (формат: YYYY-MM-DD):");
                String startDate = this.scanner.nextLine();
                System.out.println("Введите дату конца для экспорта транзакций (формат: YYYY-MM-DD):");
                String endDate = this.scanner.nextLine();

                try {
                    service.exportTransactions(startDate, endDate);
                    System.out.println("Транзакции успешно экспортированы.");
                } catch (Exception e) {
                    System.out.println("Не удалось экспортировать транзакции.");
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                // Импорт изменения курса валюты
                System.out.println("Введите путь к файлу с новыми курсами валют:");
                String filePath = this.scanner.nextLine();

                try {
                    service.importCurrencyRates(filePath);
                    System.out.println("Курсы валют успешно импортированы.");
                } catch (Exception e) {
                    System.out.println("Не удалось импортировать курсы валют.");
                    System.out.println(e.getMessage());
                }
                break;

            case 5:
                // История изменения курса валюты
                System.out.println("Введите валюту для просмотра истории изменения курса:");
                String historyCurrency = this.scanner.nextLine();
                try {
                    service.getCurrencyRateHistory(historyCurrency);
                    System.out.printf("История изменения курса валюты %s выведена.\n", historyCurrency);
                } catch (Exception e) {
                    System.out.println("Не удалось показать историю изменения курса валюты.");
                    System.out.println(e.getMessage());
                }
                break;

            case 6:
                // Заблокировать пользователя
                System.out.println("Введите ID пользователя для блокировки:");
                int blockUserId = this.scanner.nextInt();
                this.scanner.nextLine(); // Очистка буфера
                try {
                    service.blockUser(blockUserId);
                    System.out.printf("Пользователь с ID %d заблокирован.\n", blockUserId);
                } catch (Exception e) {
                    System.out.println("Не удалось заблокировать пользователя.");
                    System.out.println(e.getMessage());
                }
                break;

            case 7:
                // Разблокировать пользователя
                System.out.println("Введите ID пользователя для разблокировки:");
                int unblockUserId = this.scanner.nextInt();
                this.scanner.nextLine(); // Очистка буфера
                try {
                    service.unblockUser(unblockUserId);
                    System.out.printf("Пользователь с ID %d разблокирован.\n", unblockUserId);
                } catch (Exception e) {
                    System.out.println("Не удалось разблокировать пользователя.");
                    System.out.println(e.getMessage());
                }
                break;

            case 8:
                // Посмотреть список всех пользователей
                try {
                    service.getAllUsers();
                } catch (Exception e) {
                    System.out.println("Не удалось вывести список всех пользователей.");
                    System.out.println(e.getMessage());
                }
                break;

            case 9:
                // Посмотреть список заблокированных пользователей
                try {
                    service.getBlockedUsers();
                } catch (Exception e) {
                    System.out.println("Не удалось вывести список заблокированных пользователей.");
                    System.out.println(e.getMessage());
                }
                break;

            case 10:
                // Посмотреть список транзакций пользователя
                System.out.println("Введите ID пользователя для просмотра транзакций:");
                int userTransactionsId = this.scanner.nextInt();
                this.scanner.nextLine(); // Очистка буфера
                try {
                    service.getTransactionById(userTransactionsId);
                } catch (Exception e) {
                    System.out.println("Не удалось вывести список транзакций пользователя.");
                    System.out.println(e.getMessage());
                }
                break;

            case 11:
                // Посмотреть счета пользователя
                System.out.println("Введите ID пользователя для просмотра счетов:");
                int userAccountsId = this.scanner.nextInt();
                this.scanner.nextLine(); // Очистка буфера
                try {
                    service.getAccountById(userAccountsId);
                } catch (Exception e) {
                    System.out.println("Не удалось вывести список счетов пользователя.");
                    System.out.println(e.getMessage());
                }
                break;

            case -1:
                // Возврат в предыдущее меню
                System.out.println("Возвращаемся в предыдущее меню...");
                printMenuStart();
                break;

            case 0:
                // Возврат в главное меню
                System.out.println("Возвращаемся в главное меню...");
                logout();
                break;

            default:
                throw new Exception("Некорректный ввод.");
        }
    }


    private void logout() {
        service.logout();
        System.out.println("Вы вышли из системы.");
    }


    /**
     * Показывает меню пользователя.
     */
    private void printMenuUser() {
        boolean isRunning = true;

        Map<Integer, String> menuUser = new LinkedHashMap<>();
        String description = "Добро пожаловать в меню пользователя обменного пункта валюты.";

        menuUser.put(1, "Открыть счет");
        menuUser.put(2, "Пополнить счет");
        menuUser.put(3, "Снять средства со счета");
        menuUser.put(4, "Перевод средств со счета, включая конвертацию валюты");
        menuUser.put(5, "История всех операций по счету");
        menuUser.put(6, "Список всех действующих аккаунтов");
        menuUser.put(7, "Закрытие счета");

        menuUser.put(0, Color.RED + "⏻ Выход" + Color.RESET);


        this.printMenu(
                "Меню пользователя",
                description,
                menuUser,
                this.primaryColor + "Введите пункт меню: " + Color.RESET
        );


        while (isRunning) {
            try {
                int choice = this.scanner.nextInt();
                this.scanner.nextLine();
                this.handleUserMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("Упс... ошибка!");
                System.out.println(e.getMessage());
                this.waitRead();
                System.out.print("Пожалуйста, попробуйте еще раз: ");
            }
        }

    }


    /**
     * @param input
     * @throws Exception
     */
    private void handleUserMenuChoice(int input)
            throws Exception {

        switch (input) {
            case 1:
                System.out.println("Открытие счета. Введите название для нового счёта (свободный текст)");
                String accountTitle = this.scanner.nextLine();
                System.out.println("Введите валюту счёта (3 символа)");
                String accountCurrency = this.scanner.nextLine();
                Account newAccount;
                try {
                    newAccount = service.creatAccount(accountTitle, accountCurrency);
                } catch (Exception e) {
                    System.out.println("Счёт не создан");
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.printf("Поздравляем. Счёт %s создан в валюте %s", newAccount.getTitle(), newAccount.getCurrency());
                break;
            case 2:
                System.out.println("Давайте пополним счет. Выберите номер счета для пополнения:");
                //
                List<Account> currentUserAccounts = service.getCurrentUserAccounts();
                for (Account account : currentUserAccounts) {
                    System.out.printf("Счет %s в валюте %s. Номер счета %s", account.getTitle(), account.getCurrency(), account.getId());
                }
                System.out.println("Введите номер счета:");
                int accoutnId = this.scanner.nextInt();
                System.out.println("Введите сумму пополнения счёта:");
                BigDecimal depositedAmount = this.scanner.nextBigDecimal();

                try {
                    service.deposit(accoutnId, depositedAmount);
                } catch (Exception e) {
                    System.out.println("Не удалось пополнить счет.");
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.println("Поздравляем. Счёт пополнен. Ваш текущий баланс:" + service.getAccountById(accoutnId).getBalance());
                break;

            case 3:
                System.out.println("Давайте снимем средства со счета. Список достпуных счетов и их баланс");
                //
                currentUserAccounts = service.getCurrentUserAccounts();
                for (Account account : currentUserAccounts) {
                    System.out.printf("Счет %s в валюте %s. Номер счета %s. Остаток на счету %s", account.getTitle(), account.getCurrency(), account.getId(), account.getBalance());
                }
                System.out.println("Введите номер счета:");
                accoutnId = this.scanner.nextInt();
                System.out.println("Введите сумму снятия:");
                BigDecimal withdrawalAmount = this.scanner.nextBigDecimal();

                try {
                    service.withdrawal(accoutnId, withdrawalAmount);
                } catch (Exception e) {
                    System.out.println("Не удалось снять деньги со счета.");
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.println("Поздравляем. Вы получили наличность. Остаток на вашем счету:" + service.getAccountById(accoutnId).getBalance());
                break;

            case 4:
                System.out.println("Давайте средства с одного счета на другой. Список ваших счетов:");
                //
                currentUserAccounts = service.getCurrentUserAccounts();
                for (Account account : currentUserAccounts) {
                    System.out.printf("Счет %s в валюте %s. Номер счета %s", account.getTitle(), account.getCurrency(), account.getId());
                }
                System.out.println("Введите номер счета с которого будет осуществляться перевод:");
                int accoutnIdFrom = this.scanner.nextInt();
                System.out.println("Введите номер счета на который будет осуществляться перевод:");
                int accoutnIdTo = this.scanner.nextInt();

                System.out.println("Введите сумму перевода в валюте: " + service.getAccountById(accoutnIdFrom).getCurrency());
                BigDecimal transferAmount = this.scanner.nextBigDecimal();


                try {
                    service.exchange(accoutnIdFrom, accoutnIdTo, transferAmount);
                } catch (Exception e) {
                    System.out.println("Не удалось перевести деньги.");
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.printf("Поздравляем. Деньги переведены. Остаток на счету %s: %s.  Остаток на счету %s: %s. " + service.getAccountById(accoutnIdFrom).getTitle(), service.getAccountById(accoutnIdFrom).getBalance()
                        , service.getAccountById(accoutnIdTo).getTitle(), service.getAccountById(accoutnIdTo).getBalance()
                );
                break;

            case 5:
                System.out.println("Посмотреть историю всех операций по счету. Список доступных счетов:");
                //
                currentUserAccounts = service.getCurrentUserAccounts();
                for (Account account : currentUserAccounts) {
                    System.out.printf("Счет %s в валюте %s. Номер счета %s.", account.getTitle(), account.getCurrency(), account.getId());
                }
                System.out.println("Введите номер счета:");
                accoutnId = this.scanner.nextInt();

                try {
                    Map<LocalDateTime, Transaction> transactionHistory = service.getTransactionsByAccountId(accoutnId);
                    transactionHistory.forEach((dateTime, transaction) -> {
                        System.out.printf("Дата и время: %s, Тип транзакции %s, Со счёта %s, На счет %s, Из валюты %s, В валюту %s, Курс %s, Сумма: %s, Описание: %s%n",
                                dateTime, transaction.getType(), transaction.getAccountIdFrom(), transaction.getAccountIdTo(), transaction.getCurrencyFrom(), transaction.getCurrencyTo(), transaction.getCourse(), transaction.getAmount(), transaction.getComment());
                    });


                } catch (Exception e) {
                    System.out.println("Не удалось показать историю транзакций.");
                    System.out.println(e.getMessage());
                    return;
                }
                break;


            case 6:
                System.out.println("Список всех действующих аккаунтов");
                //
                List<Account> currentUserAccounts = service.getCurrentUserAccounts();
                for (Account account : currentUserAccounts) {
                    System.out.printf("Счет %s в валюте %s. Номер счета %s. Остаток на счету %s", account.getTitle(), account.getCurrency(), account.getId(), account.getBalance());
                }
                break;

            case 7:
                System.out.println("Закрытие счёта. Список всех действующих счетов:");
                List<Account> currentUserAccounts = service.getCurrentUserAccounts();
                for (Account account : currentUserAccounts) {
                    System.out.printf("Счет %s в валюте %s. Номер счета %s. Остаток на счету %s", account.getTitle(), account.getCurrency(), account.getId(), account.getBalance());
                }
                System.out.println("Выберите номре счёта для закрытия");
                accoutnId = this.scanner.nextInt();

                try{
                    service.removeAccount(accoutnId);
                } catch (Exception e) {
                    System.out.println("Не удалось удалить счёт");
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.printf("Поздравляем. Счёт %s удалён", accoutnId);
                break;

            default:
                throw new Exception("Некорректный ввод.");
        }

    }


    /**
     * Печатает список пользователей в табличном формате.
     *
     * @param users Список пользователей.
     */
    private void printUsers(List<User> users) {
        // TODO: Этот метод (printUsers) еще в разработке.
        System.out.printf(
                "\n" + Color.CYAN + TextStyle.BOLD + "%-30s %-30s %-15s" + Color.RESET,
                "Email",
                "Password",
                "Role"
        );

        for (User user : users) {
            System.out.printf(
                    "\n%-30s %-30s %-15s",
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
            );
        }

        System.out.println("\n");
    }


    /**
     * Выводит меню в консоль с поддержкой настроек отображения.
     * <ul>
     *   <li>Поддерживает заголовок, описание, пункты меню и футер.</li>
     *   <li>Сохраняет форматирование ANSI-кодов при переносе строк.</li>
     *   <li>Переносит строки с учетом слов — слова не разделяются.</li>
     *   <li>Добавляет декоративную рамку (бордюр) вокруг меню, если включено.</li>
     *   <li>Добавляет форматирование к заголовку, пунктам меню и футеру.</li>
     * </ul>
     *
     * @param title       Заголовок меню. Будет выровнен по центру с форматированием в рамке, если она включена.
     * @param description Описание меню. Выводится под заголовком.
     * @param items       Пункты меню, где ключ — номер пункта, значение — текст пункта. Будет добавлено
     *                    форматирование.
     * @param footer      Футер. Выводится после пунктов меню. Будет добавлено форматирование.
     */
    private void printMenu(String title, String description, Map<Integer, String> items, String footer) {
        StringBuilder menu = new StringBuilder();

        // Заголовок.
        if (title != null) {
            title = title.toUpperCase();

            if (isBorder == true) {
                int paddingLeft = (width - title.length()) / 2;
                int paddingRight = width - title.length() - paddingLeft;

                title = TextStyle.REVERSE + "" + this.primaryColor
                        + " ".repeat(paddingLeft)
                        + title
                        + " ".repeat(paddingRight)
                        + TextStyle.RESET + "\n";
            }

            menu
                    .append(TextStyle.BOLD)
                    .append(title)
                    .append(Color.RESET)
                    .append("\n");
        }

        // Описание.
        if (description != null) {
            menu
                    .append(description)
                    .append("\n");
        }

        // Элементы меню.
        if (items != null) {
            menu.append("\n");

            for (Map.Entry<Integer, String> entry : items.entrySet()) {
                menu
                        .append(this.primaryColor)
                        .append(String.format("%5s", entry.getKey() + ". "))
                        .append(Color.RESET)
                        .append(entry.getValue())
                        .append("\n");
            }

            // FIXME: Не добавляется перенос строки.
            menu.append("\n\n");
        }


        if (isBorder == true) {
            StringBuilder result = new StringBuilder();

            result
                    .append(this.primaryColor)
                    .append("╔═")
                    .append("═".repeat(width))
                    .append("═╗\n")
                    .append(Color.RESET);

            StringBuilder activeFormatting = new StringBuilder();

            for (String row : menu.toString().split("\n")) {
                if (activeFormatting.length() > 0) {
                    row = activeFormatting + row;
                }

                // Разбить строку row на части длиной не более this.width символов
                List<String> parts = new ArrayList<>();

                while (this.getVisibleLength(row) > this.width) {
                    int visibleCount = 0;
                    int index = 0;

                    while (visibleCount < this.width && index < row.length()) {
                        if (row.charAt(index) == '\u001B') {
                            int endIndex = row.indexOf('m', index);

                            if (endIndex == -1) continue;

                            String ansiCode = row.substring(index, endIndex + 1);

                            if (ansiCode.equals("\u001B[0m")) {
                                activeFormatting.setLength(0);
                            } else {
                                activeFormatting.append(ansiCode);
                            }

                            index = endIndex;
                        } else {
                            visibleCount++;
                        }

                        index++;
                    }

                    // Проверяем, есть ли разрыв посередине слова
                    if (index < row.length() && !Character.isWhitespace(row.charAt(index))) {
                        int lastSpace = row.lastIndexOf(' ', index);

                        if (lastSpace > -1) {
                            index = lastSpace;
                        }
                    }

                    // Формируем видимую часть строки (включая форматирование)
                    String currentPart = row.substring(0, index).stripTrailing() + TextStyle.RESET;
                    parts.add(currentPart);

                    // Переносим активное форматирование в начало следующей строки, если оно есть
                    row = activeFormatting + row.substring(index).stripLeading();
                }

                if (this.getVisibleLength(row) <= this.width) {
                    if (row.contains("\u001B[0m")) {
                        activeFormatting.setLength(0);
                    }
                }

                parts.add(row);


                for (String part : parts) {
                    int totalWidth = this.width + part.length() - this.getVisibleLength(part);

                    result.append(String.format(
                            this.primaryColor + "║ "
                                    + Color.RESET + "%-" + totalWidth + "s"
                                    + Color.RESET + this.primaryColor + " ║\n" +
                                    Color.RESET,
                            part
                    ));
                }
            }

            result
                    .append(this.primaryColor)
                    .append("╚═")
                    .append("═".repeat(width))
                    .append("═╝\n")
                    .append(Color.RESET);

            menu = result;
        }

        // Футер.
        if (footer != null) {
            menu
                    .append(" ".repeat(isBorder ? 2 : 0))
                    .append(footer);
        }

        System.out.printf(menu.toString());
    }


    /**
     * Ожидает ввода от пользователя для продолжения.
     */
    private void waitRead() {
        System.out.println(this.secondaryColor + "Для продолжения нажмите Enter..." + Color.RESET);
        this.scanner.nextLine();
    }


    /**
     * Возвращает длину строки без учёта ANSI-кодов.
     *
     * @param text Текст.
     * @return Ширина строки с учётом ANSI-кодов.
     */
    private int getVisibleLength(String text) {
        return text
                .replaceAll("\u001B\\[[;\\d]*m", "")
                .length();
    }


    /**
     * Запускает программу.
     */
    public void run()
            throws InterruptedException {
        this.loading();
        this.printMenuStart();
    }
}


