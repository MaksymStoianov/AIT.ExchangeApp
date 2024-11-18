package view;

import model.User;
import model.UserImpl;
import service.MainService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * MenuImpl
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class MenuImpl {

  private final Color primaryColor = Color.CYAN;
  private final Color secondaryColor = Color.ORANGE;


  /**
   * Сканер.
   */
  private final Scanner scanner = new Scanner(System.in);


  /**
   * Сервис.
   */
  private final MainService service;


  public MenuImpl(MainService service) {
    this.service = service;
  }


  /**
   * Показывает стартовое меню.
   */
  private void printMenuStart() {
    boolean isRunning = true;

    Map<Integer, String> menu = new LinkedHashMap<>();

    String description = "Добро пожаловать в главное меню обменного пункта валюты.";

    // TODO: Получить активного пользователя.
    UserImpl user = null;

    // Добавляем элементы
    if (user == null) {
      description += "\nПожалуйста, выполните вход в систему или создайте новый аккаунт.";

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
   * Печатает меню в консоли.
   *
   * <p>Внимание! Если длина строки превышает 80 символов, используйте перенос на новую строку.</p>
   *
   * @param title       Заголовок сообщения.
   * @param description Описание сообщения.
   * @param items       Пункты меню.
   * @param footer      Сообщение после пунктов меню.
   */
  private void printMenu(String title, String description, Map<Integer, String> items, String footer) {
    StringBuilder menu = new StringBuilder();

    int width = 80;
    boolean isBorder = true;

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
      menu.append("\n");
    }


    if (isBorder == true) {
      StringBuilder result = new StringBuilder();

      result
          .append(this.primaryColor)
          .append("╔═")
          .append("═".repeat(width))
          .append("═╗\n")
          .append(Color.RESET);

      for (String row : menu.toString().split("\n")) {
        int totalWidth = width + row.length() - row
            .replaceAll("\u001B\\[[;\\d]*m", "")
            .length();

        result.append(String.format(
            this.primaryColor + "║" + Color.RESET + " %-" + totalWidth + "s " + this.primaryColor + "║\n" + Color.RESET,
            row
        ));
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
   * Запускает программу.
   */
  public void run() {
    this.printMenuStart();
  }

}
