package org.example.bank.exceptions;

public class UnknownAccountException extends Exception
{
    public UnknownAccountException()
    {
    }

    public UnknownAccountException(String message)
    {
        super(message);
    }
}

/*
    Exception Syntax

    if(some condition){
        throw new exception_name(); // can also add String here
    }


    try{
        sth
    }catch(error e){
        throw new exception_name();
    }

 */


/*
@Test
test deposit{
    Account testAccount = new Account(f,l,no);
    testAccount.deposit(val);
    AssertEquals(testAccount.getBal(No), expected output);
    AssertFalse/AssertTrue
    assertThrow(NonSufficientFundsException.class, () -> testAccount.withdraw(testValue));
 */