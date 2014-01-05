package entity;

import boundary.Graphic;

/**
 * Class that contains all the methods and values relevant for ownable fields.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public abstract class Ownable extends Field {
	protected int price;
	protected Player owner;

	/**
	 * Constructor that set name and price.
	 *
	 * @param name Name of the field.
	 * @param price Price of the field.
	 */
	public Ownable(String name, int price) {
		super(name);
		this.price = price;
		owner = null;
	}

	/**
	 * Method to take care of everything that should happen, when a player lands on this field.
	 * This implementation is used by all the own able fields that inherits from this class.
	 */
	public void landOnField(Player player) {
		if (owner == null) {
			buyFieldOption(player);
		}
		else if(owner != player) {
			int rent = getRent();
			player.transferTo(owner, rent);
		}
	}

	/**
	 * Method to calculate rent. Has different implementation for different types of fields.
	 *
	 * @return
	 */
	public abstract int getRent();
	
	/**
	 * Method to set owner of the field.
	 * 
	 * @param owner The player to set as owner.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	private void buyFieldOption(Player player) {
		String input = Graphic.getSelection();
		
		if ("buy".equals(input)) {
			player.addToAccount(-1 * price);
			setOwner(player);
			Graphic.setOwner(player.getLocation(), player.getName());
		}
	}
}