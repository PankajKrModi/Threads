package Accounts;

public class Accounts {

	private int balance=10000;
	public void add(int amount) {
		balance += amount; 
	}
	public void withdraw(int amount) {
		balance -= amount;
	}
	public int getBalance() {
		return balance;
	}
	public static void transfer(Accounts acc1,Accounts acc2, int amount) {
		
		acc1.withdraw(amount);
		acc2.add(amount);
	}
}
