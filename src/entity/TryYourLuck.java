package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

/**
 * Class to make a TryYourLuck-field.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class TryYourLuck extends Field {
	
	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name The name of this field
	 * @param gameBoard A reference to the gameboard that this field is created on
	 */
	public TryYourLuck(String name, GameBoard gameBoard) {
		super(name, gameBoard);
	}

	/**
	 * Method to take care of everything that should happen, when a player lands on this field.
	 * Draws a new TryYourLuck-card, and executes its action. If the card is a moneycard, the
	 * player will get the opportunity to buy/sell before paying. If the card is a movecard, the
	 * player will be moved, and land on the new field
	 */
	public void landOnField(Player player) {
		Actions action = null;
		
		while(action != Actions.END) {
			action = Graphic.showMenu(player.getName(), this.name, 0, 0, false, true, null);
			performStdActions(action, player);
		}
		
		gameBoard.getPile().nextCard();
		
		if(gameBoard.getPile().isMoneyCard()) {
			action = null;
			while(action != Actions.END) {
				action = Graphic.showMenu(player.getName(), this.name, 0, 0, false, false, gameBoard.getPile().getCardText());
				performStdActions(action, player);
			}
			gameBoard.getPile().effect(player);
		}
		else {
			Graphic.showCard(player.getName(), gameBoard.getPile().getCardText());
			gameBoard.getPile().effect(player);
			gameBoard.landOnField(player);
		}
	}

}
