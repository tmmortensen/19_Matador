package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

/**
 * Class that contains all the methods and values relevant for ownable fields.
 *
 * @author DTU 02312 Gruppe 19, 2014
 *
 */
public abstract class Ownable extends Field {
	protected int price;
	protected boolean isPledged;
	protected Player owner;
	private boolean useMenu;

	/**
	 * Constructor that set name and price.
	 *
	 * @param name Name of the field.
	 * @param price Price of the field.
	 * @param gameBoard A reference to the gameboard the field is created on.
	 */
	public Ownable(String name, int price, GameBoard gameBoard) {
		super(name, gameBoard);
		this.price = price;
		isPledged = false;
		owner = null;
		useMenu = true;
	}
	
	/**
	 * Method to take care of everything that should happen, when a player lands on this field.
	 * This implementation is used by all the own able fields that inherits from this class.
	 */
	public void landOnField(Player player) {
		int rent = 0, buyPrice;
		
		Actions action = null;
		while(action != Actions.END) {
			buyPrice = 0;
			
			// Calculate rent if field is owned by someone else, and not pledged
			if(owner != null && owner != player && !isPledged && !owner.isInJail()) {
				rent = getRent();
			}
			// Get the option to buy if field is not owned
			else if (owner == null) {
				buyPrice = price;
			}
			
			if(useMenu) {
				action = Graphic.showMenu(player.getName(), this.name, rent, buyPrice, false, false, null);
				performAction(action, player);
			}
			else {
				break;
			}
		}
		
		// Transfer the rent
		if(rent != 0) {
			player.transferTo(owner, rent);
		}
	}
	
	/**
	 * Buys the field. Takes the value of the field from the players account, and sets owner to the player.
	 * 
	 * @param player The player who buys the field
	 */
	public void buyField(Player player) {
		player.addToAccount(-1 * price);
		owner = player;
		Graphic.setOwner(player.getLocation(), player.getName());
	}
	
	/**
	 * Sells the field. Adds the fields value to the owners account, and sets owner of the field to null. 
	 */
	public void sellField() {
		owner.addToAccount(price);
		
		if(isPledged) {
			unpledgeField();
		}
		
		owner = null;
	}
	
	/**
	 * Pledges the field. Adds half the fields value to the owners account, and sets isPledged = true.
	 */
	public void pledgeField() {
		this.isPledged = true;
		owner.addToAccount(price/2);
	}
	
	/**
	 * Un-pledges the field. Takes half the fields value plus some interest from the owners account.
	 */
	public void unpledgeField() {
		this.isPledged = false;
		owner.addToAccount(-(price/2 + getPledgeIntrest()));
	}
	
	/**
	 * Sets the useMenu option to false. Useful for debug and test
	 */
	public void diableMenu() {
		useMenu = false;
	}

	
	/**
	 * Method to calculate rent. Has different implementation for different types of fields.
	 *
	 * @return The calculated rent as an integer
	 */
	protected abstract int getRent();
				
	
	private void performAction(Actions action, Player player) {
		performStdActions(action, player);
		
		if (action == Actions.BUY_FIELD) {
			buyField(player);
		}
	}
	
	private int getPledgeIntrest() {
		//Calc the intrest with all decimals
		double unrounded = (price/2) * 10 / 100;
		
		//Add 99.99, to go to the next 100's
		unrounded = unrounded + 99.99;
		
		//Divide by 100 and save as int - Java will throw away all decimals
		int rounded = (int)(unrounded / 100);
		
		//Multiply by 100 to get correct number of 00's
		rounded = rounded * 100;
		
		return rounded;
	}
}