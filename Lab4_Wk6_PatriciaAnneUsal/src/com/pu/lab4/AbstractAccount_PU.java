package com.pu.lab4;

public abstract class AbstractAccount_PU {
	private final String firstName;
	private final String lastName;
	private final String accountNo;
	protected double balance;
	
	public AbstractAccount_PU(String firstName, String lastName, String accountNo, double balance) {
		if (firstName == null || firstName.trim().isEmpty()) {
			throw new IllegalArgumentException("First Name required");
		}
		
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new IllegalArgumentException("Last Name required");
		}
		
		if (accountNo == null || accountNo.trim().isEmpty()) {
			throw new IllegalArgumentException("Account Number required");
		}
		
		if (balance < 0) {
			throw new IllegalArgumentException("Initial balance cannot be negative");
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	//static deposit
	public static void deposit(AbstractAccount_PU acct, double amount) {
		if (acct == null) {
			return;
		}
		if (amount <= 0) {
			System.out.println("Amount must be positive");
			return;
		}
		acct.balance += amount;
		System.out.println("Deposit $" + String.format("%.2f", amount) + (" into account ") + (acct.accountNo));
	}
	
	public abstract void withdraw(double amount);
	
	public double getBalance() {
		return balance;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s): balance = $%.2f", getFullName(), accountNo, balance);
	}

}
