package test.repository;

import model.Account;
import org.junit.jupiter.api.*;
import repository.interfaces.AccountRepository;
import repository.AccountRepositoryImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountRepositoryImplTest {

    private AccountRepository accoutRepository;

    @BeforeEach
    public void setUp() {
        accoutRepository = new AccountRepositoryImpl();
        accoutRepository.createAccount("igor@gmail.com", "Euro account", "EUR");
        accoutRepository.createAccount("igor@gmail.com", "USA dollars account", "USD");
        accoutRepository.createAccount("igor@gmail.com", "English Pounds account", "GBP");

        accoutRepository.createAccount("maxim@gmail.com", "Euro account", "EUR");
        accoutRepository.createAccount("maxim@gmail.com", "USA dollars account", "USD");
        accoutRepository.createAccount("maxim@gmail.com", "English Pounds account", "GBP");

        accoutRepository.createAccount("angelika@gmail.com", "Euro account", "EUR");
        accoutRepository.createAccount("angelika@gmail.com", "USA dollars account", "USD");
        accoutRepository.createAccount("angelika@gmail.com", "English Pounds account", "GBP");
        accoutRepository.createAccount("angelika@gmail.com", "Another EUR account", "EUR");

        accoutRepository.createAccount("viktoria@gmail.com", "Euro account", "EUR");
        accoutRepository.createAccount("viktoria@gmail.com", "USA dollars account", "USD");
        accoutRepository.createAccount("viktoria@gmail.com", "English Pounds account", "GBP");
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accountList = accoutRepository.getAllAccounts();
        assertNotNull(accountList);
        assertEquals(13, accountList.size());
    }

    @Test
    public void createAccount(){
        //Prepare
        String userEmail = "newAccount@yahoo.com";
        String title = "USD account";
        String currency = "USD";
        Account newAccount = accoutRepository.createAccount(userEmail, title, currency);


        List<Account> accountList = accoutRepository.getAllAccounts();
        assertNotNull(accountList);
        assertEquals(14, accountList.size());
        assertEquals(userEmail, newAccount.getUserEmail());
        assertEquals(title, newAccount.getTitle());
        assertEquals(currency, newAccount.getCurrency());
    }




    @Test
    public void getAccountById(){
        //Prepare
        String userEmail = "newAccount@yahoo.com";
        String title = "USD account";
        String currency = "USD";
        Account newAccountToBeCreated = accoutRepository.createAccount(userEmail, title, currency);

        Account accountCreated = accoutRepository.getAccountById(newAccountToBeCreated.getId());
        assertNotNull(accountCreated);
        assertEquals(newAccountToBeCreated.getUserEmail(), accountCreated.getUserEmail());
        assertEquals(newAccountToBeCreated.getCurrency(), accountCreated.getCurrency());
        assertEquals(newAccountToBeCreated.getStatus(), accountCreated.getStatus());
    }



    @Test //get by ID negative
    public void getAccountByIdNegative(){
        //Prepare

        Account accountCreatedNull = accoutRepository.getAccountById(10000);

        assertNull(accountCreatedNull);

    }



    @Test
    public void getAccountsByUserEmail(){
        String userEmail4 = "angelika@gmail.com";
        String userEmail3 = "igor@gmail.com";
        List<Account> userAccounts4 = new ArrayList<>();
        List<Account> userAccounts3 = new ArrayList<>();
        try{
            userAccounts4 = accoutRepository.getAccountsByUserEmail(userEmail4);
            userAccounts3 = accoutRepository.getAccountsByUserEmail(userEmail3);
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
            return;
        }
        assertEquals(4, userAccounts4.size());
        assertEquals(3, userAccounts3.size());
    }

    @Test //negative
    public void getAccountsByUserEmailNegative(){
        List<Account> userAccountsNull = new ArrayList<>();
        try{
            userAccountsNull = accoutRepository.getAccountsByUserEmail(null);
            fail("No accounts expected from wrong email");
        }catch (IllegalArgumentException e){
            assertNull(userAccountsNull);
        }
    }


    @Test
    public void getAccountsByCurrencyCode(){
        String currencyCodeUSD = "USD";
        String currencyCodeEUR = "EUR";
        String userEmail = "angelika@gmail.com";

        List<Account> userEURaccounts = new ArrayList<>();
        List<Account> userUSDaccounts = new ArrayList<>();


        try{
            userUSDaccounts = accoutRepository.getAccountsByCurrencyCode(userEmail, currencyCodeUSD);
            userEURaccounts = accoutRepository.getAccountsByCurrencyCode(userEmail, currencyCodeEUR);
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
            return;
        }
        assertEquals(1, userUSDaccounts.size());
        assertEquals(2, userEURaccounts.size());
    }


    @Test //negative
    public void getAccountsByCurrencyCodeNegative(){
        String currencyCodeWrong = null;
        String currencyCodeEUR = "EUR";
        String userEmail = "angelika@gmail.com";
        String userEmailWrong = null;

        List<Account> userAccountsWrongCurrency = new ArrayList<>();
        List<Account> userAccountsWrongEmail = new ArrayList<>();


        try{
            userAccountsWrongCurrency = accoutRepository.getAccountsByCurrencyCode(userEmail, currencyCodeWrong);
            fail("expected exception, but did not occur");
        }catch (IllegalArgumentException e){
            assertNull(userAccountsWrongCurrency);
            return;
        }


        try{
            userAccountsWrongEmail = accoutRepository.getAccountsByCurrencyCode(userEmailWrong, currencyCodeEUR);
            fail("expected exception, but did not occur");
        }catch (IllegalArgumentException e){
            assertNull(userAccountsWrongEmail);
            return;
        }
    }


    @Test
    public void removeAccountByID(){
        List<Account> accountList = accoutRepository.getAllAccounts();
        Account accountToRemove = accountList.get(0);
        int accountId = accountToRemove.getId();
        try {
            accoutRepository.removeAccount(accountId);
        } catch (Exception e) {
            fail("Expected account " + accountId + " has been removed");
        }
    }

    @Test
    public void removeAccountByIDNegative(){
        try {
            accoutRepository.removeAccount(1000);
            fail("Expected exception as account does not exist");
        } catch (Exception e) {
            assertTrue(true); // exception was expected
        }
    }

    @Test
    public void removeAccountByObject(){
        List<Account> accountList = accoutRepository.getAllAccounts();
        Account accountToRemove = accountList.get(0);
        int accountId = accountToRemove.getId();
        try {
            accoutRepository.removeAccount(accountToRemove);
        } catch (Exception e) {
            fail("Expected account " + accountId + " has been removed");
        }
    }

    @Test
    public void removeAccountByObjectNegative(){
        Account accountToRemove = new Account(1016, "USD", new BigDecimal(1000.10), "someEmail@gmail.com");
        try {
            accoutRepository.removeAccount(accountToRemove);
            fail("Expected exception as account does not exist");
        } catch (Exception e) {
            assertTrue(true); // exception was expected
        }
    }

}
