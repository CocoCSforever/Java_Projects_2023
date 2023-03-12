package org.example.bank;

import org.example.bank.exceptions.UnknownAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.example.bank.AccountTest.FIRST_NAME;
import static org.example.bank.AccountTest.LAST_NAME;
import static org.example.bank.AccountTest.ACCT_NUMBER;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    public static final String FIRSTNAME = "Yijia";
    public static final String LASTNAME = "Ma";
    public Bank testBank;
    public final Account testAccount = new Account("Yijia","Ma","1");


    @BeforeEach
    void setUp() {
        testBank = new Bank();
    }

    @Test
    void openSingleAccount() {
        testBank.openAccount(FIRSTNAME, LASTNAME);
        assertEquals(1, testBank.accountList.size());
        assertEquals("1", testBank.accountList.get(0).getAccountNumber());
//        option1: use primitive data types for assertion statements
//        option2: override equals method, otherwise it will compare address info
    }

    @Test
    void openMultipleAccount() {
        for (int i = 0; i < 100; i++){
            testBank.openAccount(FIRSTNAME, LASTNAME);
            assertEquals(String.valueOf(i+1), testBank.accountList.get(i).getAccountNumber());
        }
        assertEquals(100, testBank.accountList.size());
    }

    @Test
    void closeSingleAccount() throws UnknownAccountException {
        for (int i = 0; i < 100; i++){
            testBank.openAccount(FIRSTNAME, LASTNAME);
        }
        assertThrows(UnknownAccountException.class, () -> testBank.closeAccount("0"));
        assertThrows(UnknownAccountException.class, () -> testBank.closeAccount("101"));
        testBank.closeAccount("50");
        assertThrows(UnknownAccountException.class, () -> testBank.closeAccount("50"));
    }

    @Test
    void closeMultipleAccount() throws UnknownAccountException {
        for (int i = 0; i < 100; i++) {
            testBank.openAccount(FIRSTNAME, LASTNAME);
        }
        Random r = new Random();
        Set<Integer> index = new HashSet<>();
        for(int i = 0; i < 10; i++){
            Integer ind = r.nextInt(100)+1;
            while(index.add(ind) != true){
                ind = r.nextInt(100)+1;
            }
            String s = String.valueOf(ind);
            testBank.closeAccount(s);
        }
        assertEquals(90, testBank.getAllAccounts().length);
    }

    @Test
    void indexFormatException() {
        assertThrows(UnknownAccountException.class, () -> testBank.closeAccount("1.5"));
        assertThrows(UnknownAccountException.class, () -> testBank.closeAccount("word"));
    }

    @Test
    void getAccount() throws UnknownAccountException{
        testBank.openAccount(FIRSTNAME, LASTNAME);
        Account a = testBank.getAccount("1");
        assertEquals(FIRSTNAME, a.getFirstName());
        assertEquals(LASTNAME, a.getLastName());
        assertEquals(new BigDecimal(0), a.getBalance());
    }

    @Test
    void getAllAccounts() throws UnknownAccountException {
        for (int i = 0; i < 100; i++){
            testBank.openAccount(FIRSTNAME, LASTNAME);
        }
        assertEquals(100, testBank.getAllAccounts().length);
        Random r = new Random();
        Set<String> index = new HashSet<>();
        for(int i = 0; i < 10; i++){
            String s = String.valueOf(r.nextInt(100)+1);
            while(index.add(s) != true){
                s = String.valueOf(r.nextInt(100)+1);
            }
            testBank.closeAccount(s);
        }
        assertEquals(90, testBank.getAllAccounts().length);
        for(String s: index){
            assertThrows(UnknownAccountException.class, () -> testBank.getAccount(s));
        }
    }
}