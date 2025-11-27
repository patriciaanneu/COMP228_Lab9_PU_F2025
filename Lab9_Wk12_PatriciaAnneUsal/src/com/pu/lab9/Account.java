package com.pu.lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
	private final String accountNumber;
	private String name;
	private double balance;
	private List<Transaction> transactions = new ArrayList<>();
	
	public Account(String accountNumber, String name) {
		if (accountNumber == null) {
			throw new IllegalArgumentException("accountNumber cannot be null");
		}
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = 0.0;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public void debit(double amt) {
		if (amt <= 0) return;
		Transaction t = new Transaction(Transaction.DEBIT, amt);
		transactions.add(t);
		balance -= amt;
	}
	
	public void credit(double amt) {
		if (amt <= 0) return;
		Transaction t = new Transaction(Transaction.CREDIT, amt);
		transactions.add(t);
		balance += amt;
	}
	
	@Override
	//compares the accountNumber to have the same
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() !=o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(accountNumber, account.accountNumber);
	}
	
	@Override
	//
	public int hashCode() {
		return Objects.hashCode(accountNumber);
	}
	
	@Override
	public String toString() {
		return String.format("Account[%s, %s, balance = %.2f, transactions = %d]", accountNumber, name, balance, transactions.size());
	}
}
