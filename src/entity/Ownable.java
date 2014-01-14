package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

/**
 * Class that contains all the methods and values relevant for ownable fields.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public abstract class Ownable extends Field {
	protected int price;
	protected boolean isPledged;
	protected Player owner;

	/**
	 * Constructor that set name and price.
	 *
	 * @param name Name of the field.
	 * @param price Price of the field.
	 */
	public Ownable(String name, int price, GameBoard gameBoard) {
		super(name, gameBoard);
		this.price = price;
		isPledged = false;
		owner = null;
	}

	public abstract boolean isBuildable();
	
	/**
	 * Method to take care of everything that should happen, when a player lands on this field.
	 * This implementation is used by all the own able fields that inherits from this class.
	 */
	public void landOnField(Player player) {
		int rent = 0, buyPrice;
		
		Actions action = null;
		while(action != Actions.END) {
			buyPrice = 0;
			
			// Calculate rent if field is owned by someone else, and not pleged
			if(owner != null && owner != player && !isPledged && !owner.isInJail()) {
				rent = getRent();
			}
			// Get the option to buy if field is not owned
			else if (owner == null) {
				buyPrice = price;
			}
			
			action = Graphic.showMenu(player.getName(), this.name, rent, buyPrice, false, false, null);
			performAction(action, player);
		}
		
		// Transfer the rent
		if(rent != 0) {
			player.transferTo(owner, rent);
		}
	}
	
	public void sellField(int thisFieldNumber) {
		owner.addToAccount(price);
		
		if(isPledged) {
			unpledgeField();
		}
		
		owner = null;
		Graphic.removeOwner(thisFieldNumber);
	}
	
	public void pledgeField() {
		this.isPledged = true;
		owner.addToAccount(price/2);
	}
	
	public void unpledgeField() {
		this.isPledged = false;
		owner.addToAccount(-(price/2 + getPledgeIntrest()));
	}
	
	/**
	 * Method to set owner of the field.
	 * 
	 * @param owner The player to set as owner.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	
	/**
	 * Method to calculate rent. Has different implementation for different types of fields.
	 *
	 * @return
	 */
	protected abstract int getRent();
			
	protected void buyField(Player player) {
		player.addToAccount(-1 * price);
		owner = player;
		Graphic.setOwner(player.getLocation(), player.getName());
	}
	
	
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