//import repository.*;
//import service.MainService;
//import service.MainServiceImpl;
//import view.Menu;
//
///**
// * ExchangeApp
// *
// * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
// */
//public class ExchangeApp {
//
//  public static void main(String[] args)
//      throws InterruptedException {
//    UserRepository userRep = new UserRepositoryImpl();
//    AccountRepository accountRepo = new AccountRepositoryImpl();
//    CurrencyRepository currencyRepo = new CurrencyRepositoryImpl();
//    TransactionRepository transactionRepo = new TransactionRepositoryImpl();
//
//    MainService service = new MainServiceImpl(userRep,accountRepo, currencyRepo, transactionRepo );
//
//    Menu menu = new Menu(service);
//
//    // TODO: Установить демо пользователей.
//    // 2 админа
//    // 5 обычный пользователей
//    // 1 заблокированного
//
//    // TODO: Создать демо счета для пользователй.
//    // минимум по з счета на каждого пользователя в разных валютах
//
//    // TODO: Добавить возможность экспорта транзакций в файл.
//
//    menu.run();
//  }
//
//}
