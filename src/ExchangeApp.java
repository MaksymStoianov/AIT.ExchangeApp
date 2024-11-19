import view.Menu;

/**
 * ExchangeApp
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class ExchangeApp {

  public static void main(String[] args)
      throws InterruptedException {
    // MainService service = new MainServiceImpl(carRep, userRep);
    // UserRepository userRep = new UserRepositoryImpl();

    Menu menu = new Menu(null);

    // TODO: Установить демо пользователей.
    // 2 админа
    // 5 обычный пользователей
    // 1 заблокированного

    // TODO: Создать демо счета для пользователй.
    // минимум по з счета на каждого пользователя в разных валютах

    // TODO: Добавить возможность экспорта транзакций в файл.

    menu.run();
  }

}
