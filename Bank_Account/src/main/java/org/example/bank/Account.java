package org.example.bank;

import org.example.bank.exceptions.NonSufficientFundsException;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String firstName;
    private String lastName;
    private String acctNo;
    private BigDecimal balance;
    /**
     * Create a new account with the information passed in.
     * @param firstName First name of the account holder.
     * @param lastName Last name of the account holder.
     * @param acctNo Account number to create the account with.
     */
    public Account(String firstName, String lastName, String acctNo)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.acctNo = acctNo;
        balance = new BigDecimal(0);
    }

    protected Account(String firstName, String lastName, String acctNo, BigDecimal balance){
        this(firstName, lastName, acctNo);
        this.balance = balance;
    }

    /**
     * Deposit the amount passed into the function, the balance should reflect this deposit.
     * @param depositAmount Amount to deposit into the account.
     * @return The balance after the deposit.
     */
    public BigDecimal deposit(BigDecimal depositAmount)
    {
        if(depositAmount.compareTo(new BigDecimal(0)) == -1){
            return balance;
        }
        balance = balance.add(depositAmount);
        return balance;
    }

    /**
     * Withdraws the amount passed in from the bank account, the balance should reflect this withdrawal.
     * @param withdrawalAmount Amount to withdraw from the account.
     * @return The balance after the withdrawal.
     * @throws NonSufficientFundsException Thrown if there are insufficient funds in the account.
     */
    public BigDecimal withdraw(BigDecimal withdrawalAmount) throws NonSufficientFundsException
    {
        if(withdrawalAmount.compareTo(balance) > 0){
            throw new NonSufficientFundsException("No sufficient Funds.");
        }
        balance = balance.subtract(withdrawalAmount);
        return balance;
    }

    /**
     * Returns the balance of the account.
     * @return The balance of the account.
     */
    public BigDecimal getBalance()
    {
        return balance;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAccountNumber()
    {
        return acctNo;
    }

    public void clearFields() {
        firstName = null;
        lastName = null;
        acctNo = null;
        balance = new BigDecimal(0);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Account)) return false;
//        Account account = (Account) o;
//        return Objects.equals(getFirstName(), account.getFirstName()) && Objects.equals(getLastName(), account.getLastName()) && Objects.equals(acctNo, account.acctNo) && Objects.equals(getBalance(), account.getBalance());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getFirstName(), getLastName(), acctNo, getBalance());
//    }
}
