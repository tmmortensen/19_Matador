package entity;

import boundary.Graphic;

/**
 * Class to make a Tax-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Tax extends Field {
	private int taxAmount;
	private int taxRate;
	private GameBoard gameBoard;

	/**
	 * 1 of 2 constructors. Used for fields that has only fixed amount of tax.
	 * 
	 * @param name
	 *            The name of this field.
	 * @param taxAmount
	 *            The amount of tax to pay.
	 */
	public Tax(String name, int taxAmount) {
		super(name);
		this.taxAmount = taxAmount;
		taxRate = -1;
	}

	/**
	 * 2 of 2 constructors. Used for fields that has both fixed amount and
	 * percentage of assets as tax. Takes more arguments, to be able to get
	 * other fields, and to be able to ask user for fixed or percentage.
	 * 
	 * @param name
	 *            The name of this field.
	 * @param taxAmount
	 *            The amount of tax to pay, if fixed amount i chosen.
	 * @param taxRate
	 *            The percentage of assets to pay, if percentage is chosen.
	 * @param gameBoard
	 *            The gameboard that this field is on.
	 * @param scanner
	 *            A scanner to use for console input.
	 */
	public Tax(String name, int taxAmount, int taxRate, GameBoard gameBoard) {
		super(name);
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
		this.gameBoard = gameBoard;
	}

	/**
	 * Method to take care of everything that should happen, when a player lands
	 * on this field. Ask player for fixed/percentage if available, and
	 * subtracts score accordingly.
	 */
	public void landOnField(Player player) {
		int taxToPay;

		if (taxRate != -1) {
			int taxFromPct = get10PctTax(player);
			boolean payPct = Graphic.taxPctChoice(taxRate, taxFromPct, taxAmount, player.getName());

			if (payPct) {
				taxToPay = get10PctTax(player);
			} else {
				taxToPay = taxAmount;
			}
		} else {
			taxToPay = taxAmount;
		}

		player.addToAccount(-1 * taxToPay);
	}

	private int getAssets(Player player) {
		int i, assets = 0;

		for (i = 1; i <= 21; i++) {
			if (gameBoard.getOwner(i) == player) {
				assets = assets + gameBoard.getPrice(i);
			}
		}

		return assets + player.getAccountValue();
	}

	private int get10PctTax(Player player) {
		return getAssets(player) * taxRate / 100;
	}
}