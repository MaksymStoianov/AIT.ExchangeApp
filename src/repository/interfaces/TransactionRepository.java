package repository.interfaces;

import model.Account;
import model.Transaction;
import model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface TransactionRepository {

    // Хранилище транзакций по счету
    Map<Integer, Account> transactions = new LinkedHashMap<>();


    /**
     * Создает новую транзакцию и добавляет ее в хранилище.
     *
     * @param type          Тип транзакции (например, перевод, обмен валют).
     * @param userEmailFrom Электронная почта отправителя.
     * @param accountIdFrom Идентификатор счета отправителя.
     * @param currencyFrom  Валюта счета отправителя.
     * @param userEmailTo   Электронная почта получателя.
     * @param accountIdTo   Идентификатор счета получателя.
     * @param currencyTo    Валюта счета получателя.
     * @param amount        Сумма транзакции.
     * @return Созданная транзакция.
     */
    Transaction createTransaction(
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount
    );


    /**
     * Создает новую транзакцию и добавляет ее в хранилище.
     *
     * @param type          Тип транзакции (например, перевод, обмен валют).
     * @param userEmailFrom Электронная почта отправителя.
     * @param accountIdFrom Идентификатор счета отправителя.
     * @param currencyFrom  Валюта счета отправителя.
     * @param userEmailTo   Электронная почта получателя.
     * @param accountIdTo   Идентификатор счета получателя.
     * @param currencyTo    Валюта счета получателя.
     * @param amount        Сумма транзакции.
     * @param course        Курс обмена (если есть).
     * @return Созданная транзакция.
     */
    Transaction createTransaction(
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount,
            BigDecimal course
    );


    /**
     * Создает новую транзакцию и добавляет ее в хранилище.
     *
     * @param type          Тип транзакции (например, перевод, обмен валют).
     * @param userEmailFrom Электронная почта отправителя.
     * @param accountIdFrom Идентификатор счета отправителя.
     * @param currencyFrom  Валюта счета отправителя.
     * @param userEmailTo   Электронная почта получателя.
     * @param accountIdTo   Идентификатор счета получателя.
     * @param currencyTo    Валюта счета получателя.
     * @param amount        Сумма транзакции.
     * @param course        Курс обмена (если есть).
     * @param comment       Комментарий к транзакции (если есть).
     * @return Созданная транзакция.
     */
    Transaction createTransaction(
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount,
            BigDecimal course,
            String comment
    );


    /**
     * Возвращает транзакцию по ее ID.
     *
     * @param id Уникальный идентификатор транзакции.
     * @return Транзакция с указанным ID или null, если транзакция не найдена.
     */
     Transaction getTransactionById(int id);


    /**
     * Возвращает список всех транзакций.
     *
     * @return Список всех транзакций.
     */
     List<Transaction> getAllTransactions();


    /**
     * Возвращает список всех транзакций по дате.
     *
     * @return Список всех транзакций.
     */
    List<Transaction> getTransactionsByDate(LocalDate date);


    List<Transaction> getTransactionsByAccountId(int accountId);
}
