package view;

import model.User;
import service.MainService;

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
                // TODO: Пункт 2
                break;

            case 3:
                System.out.println("Вы выбрали пункт " + input);
                // TODO: Пункт 3
                this.printMenuAdmin();
                break;

            case 4:
                System.out.println("Вы выбрали пункт " + input);
                // TODO: Пункт 4
                this.printMenuUser();
                break;

            default:
                throw new Exception("Некорректный ввод.");
        }
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
        adminMenu.put(1, "Заблокировать пользователя");
        adminMenu.put(2, "Разблокировать пользователя");
        adminMenu.put(3, "Посмотреть список всех пользователей");
        adminMenu.put(4, "Посмотреть список заблокированных пользователей");
        adminMenu.put(5, "Посмотреть список транзакций пользователя");
        adminMenu.put(6, "Посмотреть счета пользователя");
        adminMenu.put(0, Color.RED + "⏻ Выход в главное меню" + Color.RESET);

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
    private void handleAdminMenuChoice(int input)
            throws Exception {
        switch (input) {
            // Выход в главное меню
            case 0:
                System.out.println("Возвращаемся в главное меню...");
                // TODO: Реализовать логику выхода в главное меню
                break;

            case 1:
                System.out.println("Вы выбрали: Заблокировать пользователя");
                // TODO: Реализовать блокировку пользователя
                break;

            case 2:
                System.out.println("Вы выбрали: Разблокировать пользователя");
                // TODO: Реализовать разблокировку пользователя
                break;

            case 3:
                System.out.println("Вы выбрали: Посмотреть список всех пользователей");
                // TODO: Реализовать отображение всех пользователей
                break;

            case 4:
                System.out.println("Вы выбрали: Посмотреть список заблокированных пользователей");
                // TODO: Реализовать отображение заблокированных пользователей
                break;

            case 5:
                System.out.println("Вы выбрали: Посмотреть список транзакций пользователя");
                // TODO: Реализовать отображение транзакций конкретного пользователя
                break;

            case 6:
                System.out.println("Вы выбрали: Посмотреть счета пользователя");
                // TODO: Реализовать отображение счетов конкретного пользователя
                break;

            default:
                throw new Exception("Некорректный ввод.");
        }
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
     *
     * @param input
     * @throws Exception
     */
    private void handleUserMenuChoice(int input)
            throws Exception {
        // TODO: Реализовать метод.
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
