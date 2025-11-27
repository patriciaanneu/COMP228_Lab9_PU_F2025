package com.pu.lab9;

public class Transaction {
	
	public static final int CREDIT = 1;
	public static final int DEBIT = 2;
	
	private final int type;
	private final double amount;
	
	public Transaction( int type, double amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public int getType() {
		return type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	private String typeLabel() {
		switch (type) {
		case CREDIT:
			return "CREDIT";
		case DEBIT:
			return "DEBIT";
		default:
			return "UNKNOWN";
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s %.2f", typeLabel(), amount);
	}
}
