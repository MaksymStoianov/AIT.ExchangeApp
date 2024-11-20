package repository;

import model.Account;
import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Реализация репозитория для управления транзакциями.
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    // Хранение списка всех транзакций.
    private final Map<Integer, Transaction> transactions;

    // Счетчик для генерации уникальных ID.
    private final AtomicInteger transactionIdCounter;


    public TransactionRepositoryImpl() {
        this.transactions = new LinkedHashMap<>();
        this.transactionIdCounter = new AtomicInteger(0);
    }


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
    @Override
    public Transaction createTransaction(
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount
    ) {
        int id = this.transactionIdCounter.getAndIncrement();

        Transaction transaction = new Transaction(
                id,
                type,
                userEmailFrom,
                accountIdFrom,
                currencyFrom,
                userEmailTo,
                accountIdTo,
                currencyTo,
                amount
        );

        transactions.put(id, transaction);

        return transaction;
    }


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
    @Override
    public Transaction createTransaction(
            TransactionType type,
            String userEmailFrom,
            int accountIdFrom,
            String currencyFrom,
            String userEmailTo,
            int accountIdTo,
            String currencyTo,
            BigDecimal amount,
            BigDecimal course
    ) {
        int id = this.transactionIdCounter.getAndIncrement();

        Transaction transaction = new Transaction(
                id,
                type,
                userEmailFrom,
                accountIdFrom,
                currencyFrom,
                userEmailTo,
                accountIdTo,
                currencyTo,
                amount,
                course
        );

        transactions.put(id, transaction);

        return transaction;
    }


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
    @Override
    public Transaction createTransaction(
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
    ) {
        int id = this.transactionIdCounter.getAndIncrement();

        Transaction transaction = new Transaction(
                id,
                type,
                userEmailFrom,
                accountIdFrom,
                currencyFrom,
                userEmailTo,
                accountIdTo,
                currencyTo,
                amount,
                course,
                comment
        );

        transactions.put(id, transaction);

        return transaction;
    }


    /**
     * Возвращает транзакцию по ее ID.
     *
     * @param id Уникальный идентификатор транзакции.
     * @return Транзакция с указанным ID или null, если транзакция не найдена.
     */
    @Override
    public Transaction getTransactionById(int id) {
        return this.transactions.getOrDefault(id, null);
    }


    /**
     * Возвращает список всех транзакций.
     *
     * @return Список всех транзакций.
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(this.transactions.values());
    }


    /**
     * Возвращает список всех транзакций по дате.
     *
     * @param date Дата.
     * @return Список транзакций.
     */
    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getDate().toLocalDate().equals(date)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * @param accountId
     * @return
     */
    @Override
    public List<Account> getTransactionsByAccountId(int accountId) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (transaction.getAccountIdFrom() == accountId) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по пользователю "из".
     *
     * @param userEmail Email пользователя.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserFrom(String userEmail) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailFrom().equals(userEmail)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по пользователю "из".
     *
     * @param userEmail Email пользователя.
     * @param accountId Id счета пользователя.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserFrom(String userEmail, int accountId) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailFrom().equals(userEmail)) {
                continue;
            }

            if (transaction.getAccountIdFrom() != accountId) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по пользователю "из".
     *
     * @param userEmail Email пользователя.
     * @param accountId Id счета пользователя.
     * @param date Дата.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserFrom(String userEmail, int accountId, LocalDate date) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailFrom().equals(userEmail)) {
                continue;
            }

            if (transaction.getAccountIdFrom() != accountId) {
                continue;
            }

            if (!transaction.getDate().toLocalDate().equals(date)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по дате по пользователю "из".
     *
     * @param userEmail Email пользователя.
     * @param date Дата.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserFrom(String userEmail, LocalDate date) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailFrom().equals(userEmail)) {
                continue;
            }

            if (!transaction.getDate().toLocalDate().equals(date)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по пользователю "в".
     *
     * @param userEmail Email пользователя.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserTo(String userEmail) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailTo().equals(userEmail)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по пользователю "в".
     *
     * @param userEmail Email пользователя.
     * @param accountId Id счета пользователя.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserTo(String userEmail, int accountId) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailTo().equals(userEmail)) {
                continue;
            }

            if (transaction.getAccountIdTo() != accountId) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по пользователю "в".
     *
     * @param userEmail Email пользователя.
     * @param accountId Id счета пользователя.
     * @param date Дата.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserTo(String userEmail, int accountId, LocalDate date) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailTo().equals(userEmail)) {
                continue;
            }

            if (transaction.getAccountIdTo() != accountId) {
                continue;
            }

            if (!transaction.getDate().toLocalDate().equals(date)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }


    /**
     * Возвращает список всех транзакций по дате по пользователю "в".
     *
     * @param userEmail Email пользователя.
     * @param date Дата.
     * @return Список транзакций пользователя.
     */
    public List<Transaction> getTransactionsByUserTo(String userEmail, LocalDate date) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : this.getAllTransactions()) {
            if (!transaction.getUserEmailTo().equals(userEmail)) {
                continue;
            }

            if (!transaction.getDate().toLocalDate().equals(date)) {
                continue;
            }

            result.add(transaction);
        }

        return result;
    }

}