package com.pu.lab4;

import java.util.Scanner;
import java.math.BigDecimal;

public class DriverMainClass_PatriciaAnneUsal {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		//account creation
		System.out.println("Create an Account:");
		
		System.out.print("First Name: ");
		String firstName = scanner.nextLine().trim();
		
		System.out.print("Last Name: ");
		String lastName = scanner.nextLine().trim();
		
		System.out.print("Account Number: ");
		String accNo = scanner.nextLine().trim();
		
		System.out.print("Initial Balance:");
		double bal;
		while (true) {
			String line = scanner.nextLine().trim();
			try {
				BigDecimal bd = new BigDecimal(line);
				bal = bd.doubleValue();
				if (bal < 0) {
					System.out.print("Initial balance cannot be negative. Enter again: ");
					continue;
				}
				break;
			} catch (NumberFormatException ex) {
				System.out.print("Please enter a valid numeric balance: ");
			}
		}
		
		BankAccount_PU account = new SavingsAccount_PU(firstName, lastName, accNo, bal);
		
		//deposit/withdrawal loop
		System.out.println("\n--- Menu ---");
		
		while (true) {
			System.out.printf("Current balance: $%.2f%n", account.getBalance());
            System.out.println("Choose: deposit | withdraw | interest | exit");
            System.out.print("Action: ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "exit": {
                    System.out.println("Exiting. Goodbye.");
                    scanner.close();
                    return;
                }

                case "deposit": {
                    double amount;
                    while (true) {
                        System.out.print("Enter deposit amount: ");
                        String amtLine = scanner.nextLine().trim();
                        try {
                            BigDecimal bd = new BigDecimal(amtLine);
                            amount = bd.doubleValue();
                            if (amount <= 0) {
                                System.out.println("Amount must be positive.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number");
                        }
                    }
                    AbstractAccount_PU.deposit(account, amount);
                    break;
                }

                case "withdraw": {
                    double amount;
                    while (true) {
                        System.out.print("Enter withdrawal amount: ");
                        String amtLine = scanner.nextLine().trim();
                        try {
                            BigDecimal bd = new BigDecimal(amtLine);
                            amount = bd.doubleValue();
                            if (amount <= 0) {
                                System.out.println("Amount must be positive.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number");
                        }
                    }
                    account.withdraw(amount);
                    break;
                }
                
                case "interest": {
                	
                	double principal = account.getBalance();
                	
                	//simple or compound options
                	System.out.println("Interest options: simple | compound | exit");
                	System.out.print("Action: ");
                	String choiceInterest = scanner.nextLine().trim().toLowerCase();
                	
                	if (choiceInterest.equals("exit")) {
                		System.out.println("Exiting. Goodbye");
            			scanner.close();
            			return;
                	} else if (choiceInterest.equals("simple")) {
                		double annualRate;
            			
            			while (true) {
            				System.out.print("Annual Rate: ");
            				String rLine = scanner.nextLine().trim();
            				
            				try {
            					BigDecimal bd = new BigDecimal(rLine);
            					annualRate = bd.doubleValue() / 100.0;
            					
            					if (annualRate < 0) {
            						System.out.println("Rate cannot be negative");
            						continue;
            					}
            					break;
            				} catch (NumberFormatException e) {
            					System.out.println("Please enter a valid rate");
            				}
            			}
            			
            			double interest = account.calculateInterest(principal, annualRate);
            			System.out.println("Simple Interest: $" + String.format("%.2f", interest));
            			System.out.println("Exiting. Goodbye");
            			scanner.close();
            			return;
                	} else if (choiceInterest.equals("compound")) {
                		double annualRate;
            			
            			while (true) {
            				System.out.print("Annual Rate: ");
            				String rLine = scanner.nextLine().trim();
            				
            				try {
            					BigDecimal bd = new BigDecimal(rLine);
            					annualRate = bd.doubleValue() / 100.0;
            					
            					if (annualRate < 0) {
            						System.out.println("Rate cannot be negative");
            						continue;
            					}
            					break;
            				} catch (NumberFormatException e) {
            					System.out.println("Please enter a valid rate.");
            				}
            			}
            			
            			int periods;
            			
            			while (true) {
            				System.out.print("Enter number of periods: ");
            				String tLine = scanner.nextLine().trim();
            				try {
            					BigDecimal bd = new BigDecimal(tLine);
            					periods = bd.intValueExact();
            					if (periods <= 0) {
            						System.out.println("Periods must be a positive integer");
            						continue;
            					}
            				} catch (NumberFormatException e) {
            					System.out.println("Please enter a valid integer for periods.");
            				}
            			}
            			
            			double interest = account.calculateInterest(principal, annualRate, periods);
            			System.out.println("Compund interest for " + periods + " periods: $" + String.format("%.2f", interest));
            			System.out.println("Exiting. Goodbye.");
            			scanner.close();
            			return;
            			
                	} else {
                		System.out.println("Unknown option.");
                	}
                	break;
                	
                }

                default:
                    System.out.println("Unknown action. Please type deposit, withdraw, or exit.");
                    break;
            }
        }

	}

}
