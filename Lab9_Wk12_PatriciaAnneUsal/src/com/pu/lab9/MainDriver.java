package com.pu.lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class MainDriver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Set<Account> accounts = new HashSet<>();

		System.out.println("Enter number of accounts to input:");
		int n = readInt(sc);
		for (int i = 0; i < n; i++) {
			System.out.println("Enter account number:");
			String acctNo = readLine(sc);
			System.out.println("Enter account holder name:");
			String name = readLine(sc);

			Account newAcct = new Account(acctNo, name);
			Account account;
			if (accounts.contains(newAcct)) {
				account = findAccount(accounts, acctNo);
				System.out.println("Duplicate account detected: using existing account instance for accountNumber=" + acctNo);
			} else {
				accounts.add(newAcct);
				account = newAcct;
				System.out.println("Added account: " + acctNo);
			}

			System.out.println("Enter number of transactions for this account:");
			int tcount = readInt(sc);
			for (int t = 0; t < tcount; t++) {
				System.out.println("Enter transaction (#" + (t + 1) + ") as 'C amount' or 'D amount':");
				String line = readLine(sc);
				if (line == null || line.trim().isEmpty()) { t--; continue; }
				String[] parts = line.trim().split("\\s+");
				if (parts.length < 2) { System.out.println("Invalid entry, skipping"); continue; }
				String type = parts[0].toUpperCase();
				double amt;
				try {
					amt = Double.parseDouble(parts[1]);
				} catch (NumberFormatException ex) {
					System.out.println("Invalid amount, skipping");
					continue;
				}

				if (type.equals("C")) {
					account.credit(amt);
				} else if (type.equals("D")) {
					account.debit(amt);
				} else {
					System.out.println("Unknown transaction type, use C or D");
				}
			}
		}

		System.out.println();
		System.out.println("--- Summary ---");
		Iterator<Account> it = accounts.iterator();
		int totalTransactions = 0;
		while (it.hasNext()) {
			Account a = it.next();
			System.out.println(a.toString());
			List<Transaction> txs = a.getTransactions();
			System.out.println("Number of transactions: " + txs.size());
			System.out.printf("Final balance: %.2f\n", a.getBalance());
			totalTransactions += txs.size();

			System.out.println("Transactions:");
			Iterator<Transaction> tit = txs.iterator();
			while (tit.hasNext()) {
				System.out.println("  " + tit.next());
			}
			System.out.println();
		}

		System.out.println("Total accounts stored in HashSet: " + accounts.size());
		System.out.println("Total transactions across accounts: " + totalTransactions);

		sc.close();
	}

	private static Account findAccount(Set<Account> set, String acctNo) {
		for (Account a : set) {
			if (a.getAccountNumber().equals(acctNo)) return a;
		}
		return null;
	}

	private static String readLine(Scanner sc) {
		try {
			return sc.nextLine();
		} catch (Exception e) {
			return null;
		}
	}

	private static int readInt(Scanner sc) {
		while (true) {
			String line = readLine(sc);
			if (line == null) return 0;
			line = line.trim();
			if (line.isEmpty()) continue;
			try {
				return Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("Invalid number, try again:");
			}
		}
	}
}

