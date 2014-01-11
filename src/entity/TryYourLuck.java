package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;
import TryYourLuck.Cards.Card;

public class TryYourLuck extends Field {
	
	public TryYourLuck(String name, GameBoard gameBoard) {
		super(name, gameBoard);
	}

	public void landOnField(Player player) {
		Actions action = null;
		
		while(action != Actions.END) {
			action = Graphic.showMenu(player.getName(), this.name, 0, 0, false, false, true, null);
			performStdActions(action, player);
		}
		
		gameBoard.nextCard();
		
		if(gameBoard.getCardType() == Card.DEPOSITCARD || gameBoard.getCardType() == Card.WITHDRAWCARD) {
			action = null;
			while(action != Actions.END) {
				action = Graphic.showMenu(player.getName(), this.name, 0, 0, false, false, false, gameBoard.getCardText());
				performStdActions(action, player);
			}
			gameBoard.cardEffect(player);
		}
		else {
			Graphic.showCard(player.getName(), gameBoard.getCardText());
			gameBoard.cardEffect(player);
			gameBoard.landOnField(player);
		}
	}

}
