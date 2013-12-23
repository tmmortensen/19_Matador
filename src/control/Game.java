package control;

import java.util.Scanner;

import boundary.Graphic;
import boundary.TUI;
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

	private Scanner scanner;
	private GameBoard gameBoard;
	private Player[] players;

	private int numberOfPlayers;

	/**
	 * Game constructor. Creates new instances of the required classes.
	 */
	public Game() {
		scanner = new Scanner(System.in);
		gameBoard = new GameBoard(41);
		gameBoard.createFields(scanner);
		// setupGuiFields();
	}

	/**
	 * Start the game.
	 */
	public void startGame() {
		int activePlayer = 0;
		String userInput;

		TUI.printRules();
		numberOfPlayers = TUI.getNumberOfPlayers(scanner);
		players = new Player[numberOfPlayers];

		createPlayers();

		// Start of the actual game-turns
		while (true) {
			// Write whos turn it is and wait for input
			TUI.printTurn(players[activePlayer].getName());
			userInput = TUI.getUserInput(scanner);

			// Exit game if user inputs "q"
			if ("q".equals(userInput)) {
				cleanUp();
			}

			gameBoard.shakeDieCup();
			players[activePlayer].moveFieldsForward(gameBoard.getDieCupSum());
			Graphic.moveCar(players[activePlayer].getName(),
					players[activePlayer].getLocation());
			Graphic.setDice(gameBoard.getDieValue1(), gameBoard.getDieValue2());
			TUI.printFieldName(players[activePlayer].getLocation(),
					gameBoard.getName(players[activePlayer].getLocation()));
			gameBoard.landOnField(players[activePlayer]);

			if (players[activePlayer].isOnBuyableField()) {
				fieldBuyOption(players[activePlayer]);
			}

			statusTasks();

			if (players[activePlayer].isBankrupt()) {
				loseTasks(activePlayer);
			}

			// Switch turn to the next player
			activePlayer = getNextPlayerAlive(activePlayer);
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
			TUI.printNameRequest(i);

			userInput = TUI.getUserInput(scanner);
			if ("".equals(userInput)) {
				userInput = "Player" + (i + 1);
			}

			players[i] = new Player(POINTS_TO_START_WITH, userInput);
			Graphic.addPlayer(players[i].getName(),
					players[i].getAccountValue(), i);
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

	private void fieldBuyOption(Player player) {
		TUI.printBuyOption(gameBoard.getName(player.getLocation()),
				gameBoard.getPrice(player.getLocation()));
		boolean wantToBuy = TUI.getYesNo(scanner);

		if (wantToBuy) {
			player.addToAccount(-1 * gameBoard.getPrice(player.getLocation()));
			gameBoard.setOwner(player);
			Graphic.setOwner(player.getLocation(), player.getName());
		}

		player.setIsOnBuyableField(false);
	}

	private void removeGuiOwner(int activePlayer) {
		int i;
		for (i = 0; i <= 21; i++) {
			if (gameBoard.getOwner(i) == players[activePlayer]) {
				Graphic.removeOwner(i);
			}
		}
	}

	// private void setupGuiFields() {
	// int i;
	//
	// for (i = 1; i <= 21; i++) {
	// Graphic.createField(gameBoard.getName(i), "", i);
	// }
	//
	// // Remove unused fields from GUI
	// for (i = 22; i < 41; i++) {
	// Graphic.createField("", "", i);
	// }
	// }

	private void statusTasks() {
		int i;
		TUI.printScoreIntro();
		for (i = 0; i < players.length; i++) {
			TUI.printStatus(players[i].getName(), players[i].getAccountValue());
			Graphic.updatePlayer(players[i].getName(),
					players[i].getAccountValue());
		}
	}

	private void winTasks(int activePlayer) {
		TUI.printWinner(players[activePlayer].getName(),
				players[activePlayer].getAccountValue());
		TUI.getUserInput(scanner);
		cleanUp();
	}

	private void loseTasks(int activePlayer) {
		TUI.printLoser(players[activePlayer].getName(),
				players[activePlayer].getAccountValue());
		removeGuiOwner(activePlayer);
		Graphic.removePlayer(players[activePlayer].getName());
		gameBoard.clearFieldOwners(players[activePlayer]);

		if (countPlayersLeft() == 1) {
			winTasks(getLastPlayerLeft());
		}
	}

	private void cleanUp() {
		Graphic.close();
		scanner.close();
		System.exit(0);
	}
}