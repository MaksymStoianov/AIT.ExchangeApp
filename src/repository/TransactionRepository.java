package repository;

import model.Account;
import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface TransactionRepository {

    // Хранилище транзакций по счету
    Map<Integer, Account> transactions = new LinkedHashMap<>();


    /**
     * Возвращает транзакцию по ее id.
     *
     * @param id Уникальный идентификатор транзакции.
     * @return Транзакция.
     */
    Transaction getTransactionById(int id);


    /**
     * Возвращает список всех транзакций пользователя.
     *
     * @return Список всех транзакций пользователя.
     */
    List<Transaction> getAllTransactions();


    /**
     * Создает новую транзакцию.
     *
     * @param id Уникальный идентификатор транзакции.
     * @param type            Тип транзакции (например, перевод, обмен валют).
     * @param userEmailFrom   Электронная почта отправителя.
     * @param accountIdFrom   Идентификатор счета отправителя.
     * @param currencyFrom    Валюта счета отправителя.
     * @param userEmailTo     Электронная почта получателя.
     * @param accountIdTo     Идентификатор счета получателя.
     * @param currencyTo      Валюта счета получателя.
     * @param amount          Сумма транзакции.
     * @param course          Курс обмена (если есть).
     * @param comment
     */
    void createTransaction(int id, TransactionType type, String userEmailFrom, int accountIdFrom,
                           String currencyFrom, String userEmailTo, int accountIdTo,
                           String currencyTo, BigDecimal amount, BigDecimal course, String comment);

}
