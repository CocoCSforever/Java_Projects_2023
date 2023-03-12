package org.example.bank;

import org.example.bank.exceptions.NonSufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account testAccount;
    private static final BigDecimal testValue = new BigDecimal(20);
    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";
    public static final String ACCT_NUMBER = "1";
    public static final BigDecimal DEPOSIT_AMOUNT = new BigDecimal(5.55);

    @BeforeEach
    void setUp() {
        testAccount = new Account("Yijia","Ma","1");
        testAccount.deposit(testValue);
//        testAccount = new Account(FIRST_NAME, LAST_NAME, ACCT_NUMBER, DEPOSIT_AMOUNT);
    }

    @Test
    void testSingleDeposit() {
        assertEquals(testValue, testAccount.getBalance());
        testAccount.deposit(new BigDecimal(10));
        assertEquals(new BigDecimal(30), testAccount.getBalance());
        testAccount.deposit(new BigDecimal(20));
        assertEquals(new BigDecimal(50), testAccount.getBalance());
        testAccount.deposit(new BigDecimal(60));
        assertEquals(new BigDecimal(110), testAccount.getBalance());
    }

    @Test
    void testSingleWithdraw() throws NonSufficientFundsException {
        assertThrows(NonSufficientFundsException.class, () -> testAccount.withdraw(new BigDecimal(25)));
        assertEquals(new BigDecimal(10), testAccount.withdraw(new BigDecimal(10)));
        assertEquals(new BigDecimal(4), testAccount.withdraw(new BigDecimal(6)));
        assertEquals(new BigDecimal(0), testAccount.withdraw(new BigDecimal(4)));
    }

//    void testMultipleWithdraw() throws NonSufficientFundsException {
//       assertEquals(DEPOSIT_AMOUNT, testAccount.getBalance().intValue());
//    }

    @Test
    void getBalance() {
        assertEquals(testValue, testAccount.getBalance());
        testAccount.deposit(new BigDecimal(10));
        assertEquals(new BigDecimal(30), testAccount.getBalance());
        testAccount.deposit(new BigDecimal(20));
        assertEquals(new BigDecimal(50), testAccount.getBalance());
        testAccount.deposit(new BigDecimal(60));
        assertEquals(new BigDecimal(110), testAccount.getBalance());
    }

    @Test
    void getFirstName() {
        assertEquals("Yijia", testAccount.getFirstName());
        testAccount = new Account("Bob","Ma","2");
        assertEquals("Bob", testAccount.getFirstName());
        testAccount = new Account("Tom","Ma","3");
        assertEquals("Tom", testAccount.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Ma", testAccount.getLastName());
        testAccount = new Account("Bob","Chen","2");
        assertEquals("Chen", testAccount.getLastName());
        testAccount = new Account("Tom","Wang","3");
        assertEquals("Wang", testAccount.getLastName());
    }

    @Test
    void getAccountNumber() {
        assertEquals("1", testAccount.getAccountNumber());
        testAccount = new Account("Bob","Chen","2");
        assertEquals("2", testAccount.getAccountNumber());
        testAccount = new Account("Tom","Wang","3");
        assertEquals("3", testAccount.getAccountNumber());
    }

    @Test
    void clearFields() {
        testAccount.clearFields();
        assertNull(testAccount.getFirstName());
        assertNull(testAccount.getLastName());
        assertNull(testAccount.getAccountNumber());
        assertEquals(new BigDecimal(0), testAccount.getBalance());
    }
}