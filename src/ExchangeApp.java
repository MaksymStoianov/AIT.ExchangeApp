import service.MainService;
import view.MenuImpl;

/**
 * ExchangeApp
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class ExchangeApp {

  public static void main(String[] args) {
//    UserRepository userRep = new UserRepositoryImpl();
//
//    MainService service = new MainServiceImpl(carRep, userRep);

    MenuImpl menu = new MenuImpl(null);

    // TODO:

    menu.run();
  }

}
