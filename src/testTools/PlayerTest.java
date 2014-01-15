package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boundaryToMatador.GUI;

import entity.Player;

public class PlayerTest {
	private Player player;

	@Before
	public void setUp() throws Exception {
		this.player = new Player(25000, "Anders And", 1);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player(25000, "Anders And", 1);
		GUI.close();
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
	}

	@Test
	public void testPlayerWithdrawOk() {
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.addToAccount(-5000);
		expected = 25000 - 5000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testPlayerWithdrawFail() {
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.addToAccount(-30000);
		expected = 0;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPlayerBankrupt() {
		boolean expected = false;
		boolean actual = this.player.isBankrupt();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.addToAccount(-30000);
		expected = true;
		actual = this.player.isBankrupt();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPlayerCrossStartBonus1() {
		this.player.setLocation(35);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.setLocation(5);
		expected = 25000 + 4000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPlayerCrossStartBonus2() {
		this.player.setLocation(35);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.moveFieldsForward(7);
		expected = 25000 + 4000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPlayerCrossStartBonus3() {
		this.player.setLocation(35);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.moveFieldsForward(-3);
		expected = 25000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPlayerTransferTo1() {
		Player player2 = new Player(25000, "Andersine", 2);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.player.transferTo(player2, 2500);
		expected = 25000 - 2500;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPlayerTransferTo2() {
		Player player2 = new Player(25000, "Andersine", 2);
		int expected = 25000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		player2.transferTo(this.player, 45000);
		expected = 25000 + 25000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}