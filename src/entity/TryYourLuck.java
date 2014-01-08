package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

public class TryYourLuck extends Field {
	
	public TryYourLuck(String name, GameBoard gameBoard) {
		super(name, gameBoard);
	}

	public void landOnField(Player player) {
		gameBoard.nextCard();
		//TODO: Måske en pænere visning af beskeden fra kortet ifm. menu'en...?
		Graphic.showCardMessage(gameBoard.getCardText(), player.getName());
		Actions action = Graphic.showMenu(player.getName(), this.name, 0, 0, false);
		performStdActions(action, player);
		gameBoard.cardEffect(player);
		
		if(player.getLandedOnNewField()) {
			player.setLandedOnNewField(false);
			gameBoard.landOnField(player);
		}
	}

}
