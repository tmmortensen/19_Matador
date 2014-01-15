package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boundaryToMatador.GUI;

import entity.*;

public class BreweryTest {
	private Player player, owner;
	private GameBoard gameBoard;

	@Before
	public void setUp() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(25000, "Anders And", 1);
		this.owner = new Player(25000, "Andersine", 2);
		((Ownable)gameBoard.getField(13)).diableMenu();
		this.player.setLocation(13);
	}

	@After
	public void tearDown() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(25000, "Anders And", 1);
		this.owner = new Player(25000, "Andersine", 2);
		((Ownable)gameBoard.getField(13)).diableMenu();
		GUI.close();
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.owner);
		Assert.assertNotNull(this.gameBoard);
	}

	@Test
	public void testLandOnField0Owned() {
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField1Owned() {
		((Ownable)this.gameBoard.getField(13)).buyField(this.owner);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000 - (1 * 100 * this.gameBoard.getDieCup().getSum());
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField2Owned() {
		((Ownable)this.gameBoard.getField(13)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(29)).buyField(this.owner);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000 - (2 * 100 * this.gameBoard.getDieCup().getSum());
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}