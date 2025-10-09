package com.pu.lab4;

public class BankAccount_PU extends AbstractAccount_PU {

	public BankAccount_PU(String firstName, String lastName, String accountNumber, double balance) {
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
		
		balance -= amount;
		System.out.println("Withdrew $" + String.format("%.2f", amount) + " from account " + getAccountNo());
	}
	
	//method overloading
	
	//calculating simple interest given the principal and annual rate
	
	public double calculateInterest(double principal, double annualRate) {
		return principal * annualRate;
	}
	
	//compound interest
	public double calculateInterest(double principal, double annualRate, int periods) {
		return principal * Math.pow(1 + annualRate, periods) - principal;
	}

}
