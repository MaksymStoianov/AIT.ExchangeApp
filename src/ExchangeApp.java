import model.*;
import model.enums.CurrencyCode;
import model.enums.TransactionType;
import model.enums.UserRole;
import repository.*;
import repository.interfaces.AccountRepository;
import repository.interfaces.CurrencyRepository;
import repository.interfaces.TransactionRepository;
import repository.interfaces.UserRepository;
import service.*;
import service.interfaces.MainService;
import view.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

        // Устанавливаем стандартные счета
        setDefaultAccounts(accountRepo, transactionRepo, currencyRepo);

        MainService service = new MainServiceImpl(userRep, accountRepo, currencyRepo, transactionRepo);

        Menu menu = new Menu(service);

//        autoLogin(service, "admin@example.com");
        //        autoLogin(service, "max@example.com");

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


    private static void setDefaultAccounts(
            AccountRepository accountRepo,
            TransactionRepository transactionRepo,
            CurrencyRepository currencyRepo
    ) {
        Account account1 = accountRepo.createSystemAccount(
                "admin@example.com",
                "USD",
                "SYSTEM_USD"
        );

        Transaction transaction1 = transactionRepo.createTransaction(
                TransactionType.DEPOSIT,
                account1.getUserEmail(),
                account1.getId(),
                CurrencyCode.EUR.name(),
                account1.getUserEmail(),
                account1.getId(),
                CurrencyCode.EUR.name(),
                new BigDecimal(1_000)
        );
        account1.setBalance(transaction1.getAmount());


        Account account2 = accountRepo.createSystemAccount(
                "admin@example.com",
                "EUR",
                "SYSTEM_EUR"
        );

        Transaction transaction2 = transactionRepo.createTransaction(
                TransactionType.DEPOSIT,
                account2.getUserEmail(),
                account2.getId(),
                CurrencyCode.EUR.name(),
                account2.getUserEmail(),
                account2.getId(),
                CurrencyCode.EUR.name(),
                new BigDecimal(10_000)
        );
        account2.setBalance(transaction2.getAmount());


        currencyRepo.addRate(
                "USD",
                BigDecimal.valueOf(1),
                LocalDateTime.now()
        );

        currencyRepo.addRate(
                "EUR",
                BigDecimal.valueOf(1.05),
                LocalDateTime.now()
        );

        currencyRepo.addRate(
                "BTC",
                BigDecimal.valueOf(90.55),
                LocalDateTime.now()
        );

        currencyRepo.addRate(
                "GBP",
                BigDecimal.valueOf(1.52),
                LocalDateTime.now()
        );


        Account account3 = accountRepo.createSystemAccount(
                "viktoriia@example.com",
                "BTC",
                ""
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


    /**
     * Временный метод для тестирования
     */
    private static void autoLogin(MainService service, String email) {
        service.setActiveUser(email);
    }

}
