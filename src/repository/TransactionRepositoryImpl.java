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
     * @param date            Дата и время совершения транзакции.
     * @param type            Тип транзакции (например, перевод, обмен валют).
     * @param currencyFrom    Валюта отправителя.
     * @param currencyTo      Валюта получателя.
     * @param amountFrom      Сумма, отправляемая пользователем.
     * @param amountTo        Сумма, получаемая пользователем.
     * @param comment         Комментарий или описание транзакции.
     * @param accountId       Идентификатор счета, с которого выполняется транзакция.
     * @param userEmailFrom   Электронная почта отправителя.
     * @param userEmailTo     Электронная почта получателя.
     */
    @Override
    public void createTransaction(int id, LocalDateTime date, TransactionType type, String currencyFrom, String currencyTo, BigDecimal amountFrom, BigDecimal amountTo, String comment, int accountId, String userEmailFrom, String userEmailTo) {
        // Создаем новую транзакцию
        Transaction newTransaction = new Transaction(id, date, type, currencyFrom, currencyTo, amountFrom, amountTo, comment, accountId, userEmailFrom, userEmailTo);
        // Добавляем транзакцию в список (или в базу данных)
        transactionList.add(newTransaction);
    }
}