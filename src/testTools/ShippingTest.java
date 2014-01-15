package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boundaryToMatador.GUI;

import entity.*;

public class ShippingTest {
	private Player player, owner;
	private GameBoard gameBoard;

	@Before
	public void setUp() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(25000, "Anders And", 1);
		this.owner = new Player(25000, "Andersine", 2);
		((Ownable)gameBoard.getField(6)).diableMenu();
		this.player.setLocation(6);
	}

	@After
	public void tearDown() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(25000, "Anders And", 1);
		this.owner = new Player(25000, "Andersine", 2);
		((Ownable)gameBoard.getField(6)).diableMenu();
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
		((Ownable)this.gameBoard.getField(6)).buyField(this.owner);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000 - 500;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField2Owned() {
		((Ownable)this.gameBoard.getField(6)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(16)).buyField(this.owner);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000 - 1000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField3Owned() {
		((Ownable)this.gameBoard.getField(6)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(16)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(26)).buyField(this.owner);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000 - 2000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField4Owned() {
		((Ownable)this.gameBoard.getField(6)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(16)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(26)).buyField(this.owner);
		((Ownable)this.gameBoard.getField(36)).buyField(this.owner);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 25000 - 4000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}