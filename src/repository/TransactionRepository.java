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
   * @param id
   * @param date
   * @param type
   * @param currencyFrom
   * @param currencyTo
   * @param amountFrom
   * @param amountTo
   * @param comment
   * @param accountId
   * @param userEmailFrom
   * @param userEmailTo
   */
  void createTransaction(int id, LocalDateTime date, TransactionType type, String currencyFrom,
                      String currencyTo, BigDecimal amountFrom, BigDecimal amountTo,
                      String comment, int accountId, String userEmailFrom, String userEmailTo);

}
