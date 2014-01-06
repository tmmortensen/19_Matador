package control;

import boundary.Graphic;
import entity.GameBoard;
import entity.Player;

/**
 * This is controller class in the game.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Game {
	private final int POINTS_TO_START_WITH = 30000;

	private GameBoard gameBoard;
	private Player[] players;

	private int numberOfPlayers;

	/**
	 * Game constructor. Creates new instances of the required classes.
	 */
	public Game() {
		gameBoard = new GameBoard(41);
		gameBoard.createFields();
	}

	/**
	 * Start the game.
	 */
	public void startGame() {
		int activePlayer = 0;

		numberOfPlayers = Graphic.getNumberOfPlayers();
		players = new Player[numberOfPlayers];

		createPlayers();

		// Start of the actual game-turns
		while (true) {
			Graphic.getOk(players[activePlayer].getName());
			gameBoard.shakeDieCup();
			players[activePlayer].actOnDice(gameBoard.getDieCupSum(), gameBoard.getDieCupIdentical());
			gameBoard.landOnField(players[activePlayer]);

			if (players[activePlayer].isBankrupt()) {
				loseTasks(activePlayer);
			}

			// Switch turn to the next player if he didn't get an extra turn
			if(!players[activePlayer].getsExtraTurn()) {
				activePlayer = getNextPlayerAlive(activePlayer);
			}
		}
	}

	private int getNextPlayer(int input) {
		if (input + 1 >= numberOfPlayers) {
			return 0;
		}

		return input + 1;
	}

	private int getNextPlayerAlive(int input) {
		int playerToTest = getNextPlayer(input);

		while (players[playerToTest].isBankrupt()) {
			playerToTest = getNextPlayer(playerToTest);
		}

		return playerToTest;
	}

	private void createPlayers() {
		int i;
		String userInput;

		// Ask for all player names and save them in the player objects.
		for (i = 0; i < numberOfPlayers; i++) {
			userInput = Graphic.getPlayerName();
			if ("".equals(userInput)) {
				userInput = "Spiller" + (i + 1);
			}

			players[i] = new Player(POINTS_TO_START_WITH, userInput, i);
		}
	}

	private int countPlayersLeft() {
		int i, playersLeft = 0;

		for (i = 0; i < numberOfPlayers; i++) {
			if (!players[i].isBankrupt()) {
				playersLeft++;
			}
		}

		return playersLeft;
	}

	private int getLastPlayerLeft() {
		int i;

		for (i = 0; i < numberOfPlayers; i++) {
			if (!players[i].isBankrupt()) {
				return i;
			}
		}

		return 0;
	}

	private void winTasks(int activePlayer) {
		Graphic.announceWinner(players[activePlayer].getName());
		cleanUp();
	}

	private void loseTasks(int activePlayer) {
		Graphic.printLoser(players[activePlayer].getName());
		Graphic.removePlayer(players[activePlayer].getName());
		gameBoard.clearFieldOwners(players[activePlayer]);

		if (countPlayersLeft() == 1) {
			winTasks(getLastPlayerLeft());
		}
	}

	private void cleanUp() {
		Graphic.close();
		System.exit(0);
	}
}