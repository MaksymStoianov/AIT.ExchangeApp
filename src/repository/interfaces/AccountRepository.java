package repository.interfaces;

import model.Account;
import java.util.List;

public interface AccountRepository {

    /**
     * Создает новый счет для пользователя.
     *
     * @param userEmail    Идентификатор пользователя.
     * @param currencyCode Код валюты.
     * @return Созданный счет.
     */
    Account createAccount(String userEmail, String currencyCode);


    /**
     * Создает новый счет для пользователя.
     *
     * @param userEmail    Идентификатор пользователя.
     * @param currencyCode Код валюты.
     * @param title        Название счет.
     * @return Созданный счет.
     */
    Account createAccount(String userEmail, String currencyCode, String title);


    /**
     * Создает новый системный счет.
     *
     * @param userEmail    Email пользователя.
     * @param currencyCode Код валюты.
     * @param title        Название счет.
     * @return Счет.
     */
    Account createSystemAccount(String userEmail, String currencyCode, String title);


    /**
     * Получает счет по его идентификатору.
     *
     * @param id Идентификатор счет.
     * @return Найденный счет или {@code null}, если не найден.
     */
    Account getAccountById(int id);


    /**
     * Получает все счета пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    List<Account> getAllAccounts();


    /**
     * Возвращает список всех счетов пользователя.
     *
     * @return Список всех счетов пользователя.
     */
    List<Account> getAccountsByUserEmail(String userEmail) throws IllegalArgumentException;


    /**
     * Получает счет пользователя по коду валюты.
     *
     * @param userEmail    Идентификатор пользователя.
     * @param currencyCode Код валюты.
     * @return Список счетов с указанным кодом валюты.
     */
    List<Account> getAccountsByCurrencyCode(String userEmail, String currencyCode) throws IllegalArgumentException;


    /**
     * Удаляет счет пользователя по его идентификатору.
     *
     * @param id Идентификатор счета.
     */
    void removeAccount(int id) throws Exception;


    /**
     * Удаляет счет из списка счетов пользователя.
     *
     * @param account Счет.
     */
    void removeAccount(Account account) throws Exception ;


}
