package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

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
		
		gameBoard.getPile().nextCard();
		
		if(gameBoard.getPile().isMoneyCard()) {
			action = null;
			while(action != Actions.END) {
				action = Graphic.showMenu(player.getName(), this.name, 0, 0, false, false, false, gameBoard.getPile().getCardText());
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
