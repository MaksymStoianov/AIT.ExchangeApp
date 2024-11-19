package repository;

import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepositoryImpl implements TransactionRepository {

    private List<Transaction> transactionList = new ArrayList<>();

    /**
     * Возвращает транзакцию по ее ID.
     *
     * @param id Уникальный идентификатор транзакции.
     * @return Транзакция с указанным ID или null, если транзакция не найдена.
     */
    @Override
    public Transaction getTransactionById(int id) {
        // Поиск транзакции по ID
        Optional<Transaction> transaction = transactionList.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
        return transaction.orElse(null);  // Возвращаем null, если транзакция не найдена
    }

    /**
     * Возвращает список всех транзакций.
     *
     * @return Список всех транзакций.
     */
    @Override
    public List<Transaction> getAllTransactions() {
        // Возвращаем все транзакции
        return new ArrayList<>(transactionList);
    }

    /**
     * Создает новую транзакцию и добавляет ее в хранилище.
     *
     * @param id              Уникальный идентификатор транзакции.
     * @param type            Тип транзакции (например, перевод, обмен валют).
     * @param userEmailFrom   Электронная почта отправителя.
     * @param accountIdFrom   Идентификатор счета отправителя.
     * @param currencyFrom    Валюта счета отправителя.
     * @param userEmailTo     Электронная почта получателя.
     * @param accountIdTo     Идентификатор счета получателя.
     * @param currencyTo      Валюта счета получателя.
     * @param amount          Сумма транзакции.
     * @param course          Курс обмена (если есть).
     * @param comment         Комментарий к транзакции (если есть).
     */
    @Override
    public void createTransaction(int id, TransactionType type, String userEmailFrom, int accountIdFrom,
                                  String currencyFrom, String userEmailTo, int accountIdTo,
                                  String currencyTo, BigDecimal amount, BigDecimal course, String comment) {
        // Создаем новую транзакцию с правильным порядком параметров
        Transaction newTransaction;
        if (course != null) {
            newTransaction = new Transaction(id, type, userEmailFrom, accountIdFrom, currencyFrom,
                    userEmailTo, accountIdTo, currencyTo, amount, course, comment);
        } else {
            newTransaction = new Transaction(id, type, userEmailFrom, accountIdFrom, currencyFrom,
                    userEmailTo, accountIdTo, currencyTo, amount);
        }
        // Добавляем транзакцию в список (или в базу данных)
        transactionList.add(newTransaction);
    }
}