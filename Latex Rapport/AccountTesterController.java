package TestTools;

import Entity.Account;

/**
 * Class to test the Account class.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class AccountTesterController {
	Account account;
	
	/**
	 * Constructor to make a new account.
	 */
	public AccountTesterController() {
		account = new Account();
	}
	
	/**
	 * Method that tast the Account class and prints the results directly in the console.
	 */
	public void testAccount() {
		//Set test - Limits
		System.out.println("Set to 1 = " + account.setAccountValue(1));
		System.out.println("Set to 0 = " + account.setAccountValue(0));
		System.out.println("Set to -1 = " + account.setAccountValue(-1));
		System.out.println("Set to 1000000000 = " + account.setAccountValue(1000000000));
		System.out.println("Set to -1000000000 = " + account.setAccountValue(-1000000000));
		//Set test - Average values
		System.out.println("Set to 500 = " + account.setAccountValue(500));
		System.out.println("Set to -500 = " + account.setAccountValue(-500));
		System.out.println();
		
		//Add test - Limits
		account.setAccountValue(1000);
		System.out.println("Set to 1000, add -1000 = " + account.addToAccount(-1000));
		account.setAccountValue(1000);
		System.out.println("Set to 1000, add -1001 = " + account.addToAccount(-1001));
		account.setAccountValue(1001);
		System.out.println("Set to 1001, add -1000 = " + account.addToAccount(-1000));
		//Add test - Average values
		account.setAccountValue(1000);
		System.out.println("Set to 1000, add 500 = " + account.addToAccount(500));
		account.setAccountValue(1000);
		System.out.println("Set to 1000, add -500 = " + account.addToAccount(-500));
	}
}
