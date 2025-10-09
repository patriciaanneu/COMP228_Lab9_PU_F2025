package com.pu.lab4;

public class SavingsAccount_PU extends BankAccount_PU {
	
	//setting the minimum balance to 50
	private static final double minBalance = 50.0;
	
	public SavingsAccount_PU(String firstName, String lastName, String accountNumber, double balance) {
		super(firstName, lastName, accountNumber, balance);
	}
	
	
	public void withdraw(double amount) {
		if (amount <= 0) {
			System.out.println("Withdrawal amount must be positive");
			return;
		}
		
		if (amount > balance) {
			System.out.println("Insufficient funds. Withdrawal cancelled");
			return;
		}
		
		double newBalance = balance - amount;
		
		//prevent withdrawals that would drop the balance below the minimum
		if (newBalance < minBalance) {
			System.out.println("Withdrawal denied. Must keep at least $" + minBalance);
			return;
		}
		
		balance = newBalance;
		System.out.println("Withdrew $ " + amount + ". New balance: " + balance);
	}
}
