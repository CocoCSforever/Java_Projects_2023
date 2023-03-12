package org.example.bank.exceptions;

public class NonSufficientFundsException extends Exception
{
    public NonSufficientFundsException() {}
    public NonSufficientFundsException(String message) {super(message);}
}
