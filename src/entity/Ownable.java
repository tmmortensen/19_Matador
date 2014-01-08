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

	/**
	 * Method to take care of everything that should happen, when a player lands on this field.
	 * This implementation is used by all the own able fields that inherits from this class.
	 */
	public void landOnField(Player player) {
		int rent = 0;
		int buyPrice = 0;
		boolean isBuildable = false;
		Actions action;
		
		// Calculate rent if field is owned by someone else
		if(owner != null && owner != player) {
			rent = getRent();
		}
		// Get the option to buy if field is not owned
		else if (owner == null) {
			buyPrice = price;
		}
		else if (owner == player) {
			isBuildable = isBuildable();
		}
		
		action = Graphic.showMenu(player.getName(), this.name, rent, buyPrice, isBuildable);
		performAction(action, player);
		
		// Transfer the rent
		if(rent != 0) {
			player.transferTo(owner, rent);
		}
	}

	/**
	 * Method to calculate rent. Has different implementation for different types of fields.
	 *
	 * @return
	 */
	public abstract int getRent();
	
	protected abstract void performAction(Actions action, Player player);
	
	protected abstract boolean isBuildable();
	
	/**
	 * Method to set owner of the field.
	 * 
	 * @param owner The player to set as owner.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	protected void buyField(Player player) {
		player.addToAccount(-1 * price);
		setOwner(player);
		Graphic.setOwner(player.getLocation(), player.getName());
	}
}