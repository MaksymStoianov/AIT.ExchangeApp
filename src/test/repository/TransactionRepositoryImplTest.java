package test.repository;

import model.Transaction;
import model.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TransactionRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryImplTest {
    private TransactionRepositoryImpl transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositoryImpl();
    }

    /**
     * Проверяем создание транзакции
     */
    @Test
    void TestCreateTransaction() {
        Transaction transaction = transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user1@example.com",
                1,
                "USD",
                "user2@example.com",
                2,
                "USD",
                BigDecimal.valueOf(100));


        assertNotNull(transaction);
        assertEquals(TransactionType.TRANSFER, transaction.getType());
        assertEquals("user1@example.com", transaction.getUserEmailFrom());
        assertEquals("user2@example.com", transaction.getUserEmailTo());
        assertEquals(BigDecimal.valueOf(100), transaction.getAmount());

    }

    /**
     * Проверяет получение транзакции по ID.
     */
    @Test
    void getTransactionById() {
        Transaction transaction = transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user1@example.com",
                1,
                "USD",
                "user2@example.com",
                2,
                "USD",
                BigDecimal.valueOf(150)
        );

        Transaction retrieved = transactionRepository.getTransactionById(transaction.getId());
        assertNotNull(retrieved);
        assertEquals(transaction.getId(), retrieved.getId());
    }

    /**
     * Проверяет получение всех транзакций.
     */
    @Test
    void getAllTransactions() {
        transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user1@example.com",
                1,
                "USD",
                "user2@example.com",
                2,
                "USD",
                BigDecimal.valueOf(100)
        );

        transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user3@example.com",
                3,
                "EUR",
                "user4@example.com",
                4,
                "EUR",
                BigDecimal.valueOf(200)
        );

        List<Transaction> transactions = transactionRepository.getAllTransactions();
        assertEquals(2, transactions.size());

    }

    /**
     * Проверяет получение транзакций по дате.
     */
    @Test
    void getTransactionsByDate() {

        transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user1@example.com",
                1,
                "USD",
                "user2@example.com",
                2,
                "USD",
                BigDecimal.valueOf(100)
        );


        List<Transaction> transactions = transactionRepository.getTransactionsByDate(LocalDate.now());
        assertEquals(1, transactions.size());
    }

    /**
     * Проверяет фильтрацию транзакций по отправителю.
     */
    @Test
    void getTransactionsByUserFrom() {
        transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user1@example.com",
                1,
                "USD",
                "user2@example.com",
                2,
                "USD",
                BigDecimal.valueOf(100)
        );

        transactionRepository.createTransaction(
                TransactionType.TRANSFER,
                "user3@example.com",
                3,
                "EUR",
                "user4@example.com",
                4,
                "EUR",
                BigDecimal.valueOf(200)
        );

        List<Transaction> transactions = transactionRepository.getTransactionsByUserFrom("user1@example.com");
        assertEquals(1, transactions.size());
        assertEquals("user1@example.com", transactions.get(0).getUserEmailFrom());
    }


}