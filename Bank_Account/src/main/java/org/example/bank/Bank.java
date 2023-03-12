package org.example.bank;


import org.example.bank.exceptions.UnknownAccountException;

import java.util.ArrayList;

public class Bank {

	ArrayList<Account> accountList= new ArrayList<>();
	/**
	 * Opens a new account based on the information passed in, adds it to the list of accounts.
	 * @param firstName First name of account holder.
	 * @param lastName Last name of account holder.
	 */
	public void openAccount(String firstName, String lastName)
	{
		String acctNo = generateAccountNumber();
		Account account = new Account(firstName, lastName, acctNo);
		addAccount(account);
	}

	/**
	 * Closes an account based on the account number, remove it from the list of accounts.
	 * @param accountNumber
	 */
	public void closeAccount(String accountNumber) throws UnknownAccountException
	{
		int index;
		try{
			index = Integer.parseInt(accountNumber) - 1;
		}catch(NumberFormatException e){
			throw new UnknownAccountException("Invalid account number.");
		}

		if(index < 0 || index >= accountList.size() || accountList.get(index) == null) throw new UnknownAccountException();
		accountList.set(index, null);
	}

	/**
	 * Returns the account based on the account number passed in.
	 * @param accountNumber  The account number to look up and return the account.
	 * @return
	 */
	public Account getAccount(String accountNumber) throws UnknownAccountException
	{
//		if we use hashCode to generate random acctNo:
//		for (Account a: accountList){
//			if(a != null && a.getAccountNumber().equals(accountNumber)) return a;
//		}
//		throw new UnknownAccountException();
		int index;
		try{
			index = Integer.parseInt(accountNumber)-1;
		}catch (NumberFormatException e){
			throw new UnknownAccountException();
		}
		if(index < 0 || index >= accountList.size() || accountList.get(index) == null) throw new UnknownAccountException();
		return accountList.get(index);
	}

	/**
	 * Return all accounts currently in the bank.
	 * @return A list of all accounts open in the bank.
	 */
	public Account[] getAllAccounts()
	{
		ArrayList<Account> curL = new ArrayList<>();
		for(int i = 0; i < accountList.size(); i++){
			Account curA = accountList.get(i);
			if(curA != null){
				curL.add(curA);
			}
		}
		//  convert list to array: x.toArray(new Account[x.size()]);
		return curL.toArray(new Account[curL.size()]);
	}

	private String generateAccountNumber()
	{
		//another way to generate accountNumber
		// private int accountCounter = 1;
		// String acctNo = String.valueOf(accountCounter);
		int index = accountList.size() + 1;
		String acctNo = String.valueOf(index);
		return acctNo;
	}

	private void addAccount(Account account)
	{
		accountList.add(account);
	}
}
