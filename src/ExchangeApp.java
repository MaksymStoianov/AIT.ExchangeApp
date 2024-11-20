import model.*;
import repository.*;
import service.*;
import view.*;

/**
 * ExchangeApp
 *
 * @author <a href="stoianov.maksym@gmail.com">Maksym Stoianov</a>
 */
public class ExchangeApp {

    public static void main(String[] args)
            throws InterruptedException {
        UserRepository userRep = new UserRepositoryImpl();
        AccountRepository accountRepo = new AccountRepositoryImpl();
        CurrencyRepository currencyRepo = new CurrencyRepositoryImpl();
        TransactionRepository transactionRepo = new TransactionRepositoryImpl();

        // Устанавливаем стандартных пользователей
        setDefaultUsers(userRep);

        // Устанавливаем демо пользователей
        setDemoUsers(userRep);

        MainService service = new MainServiceImpl(userRep, accountRepo, currencyRepo, transactionRepo);

        Menu menu = new Menu(service);

        menu.run();
    }


    private static void setDefaultUsers(UserRepository userRep) {
        userRep.addUser(
                "admin@example.com",
                "123_Pass!0",
                UserRole.ADMIN
        );

        userRep.addUser(
                "SergiiBugaienko@example.com",
                "123_Pass!1",
                UserRole.ADMIN,
                "Sergii",
                "Bugaienko"
        );
    }


    private static void setDemoUsers(UserRepository userRep) {
        userRep.addUser(
                "angelika@example.com",
                "123_Pass!2",
                UserRole.USER,
                "Angelika",
                "Khaustova"
        );
        userRep.addUser(
                "viktoriia@example.com",
                "123_Pass!3",
                UserRole.USER,
                "Viktoriia",
                "Romanenko"
        );
        userRep.addUser(
                "igor@example.com",
                "123_Pass!4",
                UserRole.USER,
                "Igor",
                "Derk"
        );
        userRep.addUser(
                "max@example.com",
                "123_Pass!5",
                UserRole.USER,
                "Maksym",
                "Stoianov"
        );


        userRep.addUser(
                "blocked1@example.com",
                "123_Pass!6",
                UserRole.BLOCKED
        );
        userRep.addUser(
                "blocked2@example.com",
                "123_Pass!7",
                UserRole.BLOCKED
        );
    }

}
