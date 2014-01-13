package tryYourLuck;

import tryYourLuck.MoveCards.MoveCardTypes;
import entity.Player;

/**
 * Bunken af kort
 * 
 * @author Gruppe 16
 * 
 */
public class PileOfCards {
	private Cards[] pile;
	private Cards[] shuffledPile;
	private int cardNumber;

	/**
	 * Konstruktør som opretter kortene i et array
	 */
	public PileOfCards() {
		pile = new Cards[34];
		shuffledPile = new Cards[34];
		cardNumber = -1;
				
		createCards();
		shufflePile();
	}

	/**
	 * Tager det næste kort frem i bunken
	 */
	public void nextCard() {
		cardNumber++;
		if (cardNumber > pile.length - 1) {
			cardNumber = 0;
		}
	}
	
	public boolean isMoneyCard() {
		return shuffledPile[cardNumber] instanceof MoneyCards;
	}

	/**
	 * Beregner effekten af kortet
	 * 
	 * @param player
	 *            Spillerarrayet
	 */
	public void effect(Player player) {
		shuffledPile[cardNumber].calcEffect(player);
	}

	/**
	 * Sender kortets tekst vdere
	 * 
	 * @return Returnerer kortets tekst
	 */
	public String getCardText() {
		return shuffledPile[cardNumber].getCardText();
	}

	
	private void createCards() {
		pile[0] = new MoneyCards("De har modtaget deres tandlæge regning. Betal kr. 2.000", -2000);
		pile[1] = new MoneyCards("De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken", 1000);
		pile[2] = new MoneyCards("Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken", 200);
		pile[3] = new MoneyCards("De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal kr. 200", 200);
		pile[4] = new MoneyCards("Modtag udbytte af deres aktier - kr. 1.000", 1000);
		pile[5] = new MoneyCards("Modtag udbytte af deres aktier - kr. 1.000", 1000);
		pile[6] = new MoneyCards("Deres præmieobligation er udtrukket. De modtager kr. 1.000 af banken", 1000);
		pile[7] = new MoneyCards("Deres præmieobligation er udtrukket. De modtager kr. 1.000 af banken", 1000);
		pile[8] = new MoneyCards("Betal kr. 3.000 for reparation af Deres vogn", -3000);
		pile[9] = new MoneyCards("Betal kr. 3.000 for reparation af Deres vogn", -3000);
		pile[10] = new MoneyCards("Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000", 1000);
		pile[11] = new MoneyCards("De har kørt frem for \"Fuldt stop\". Betal kr. 1.000 i bøde", -1000);
		pile[12] = new MoneyCards("De har fået en parkeringsbøde. Betal kr. 200", -200);
		pile[13] = new MoneyCards("De havde en række med elleve rigtige i tipning. Modtag kr. 1.000", 1000);
		pile[14] = new MoneyCards("Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000", 3000);
		pile[15] = new MoneyCards("Betal deres bilforsikring, kr. 1.000", -1000);
		pile[16] = new MoneyCards("De har vundet i klasse lotteriet. Modtag kr. 500", 500);
		pile[17] = new MoneyCards("De har vundet i klasse lotteriet. Modtag kr. 500", 500);
		pile[18] = new MoneyCards("Betal for vognvask og smørring, kr. 300", -300);
		pile[19] = new MoneyCards("Betal kr. 200 for levering af 2 kasser øl", -200);
		pile[20] = new MoneyCards("De har købt 4 nye dæk til Deres vogn. Betal kr. 4.000", -4000);
		pile[21] = new MoneyCards("De har solgt nogle gamle møbler på auktion. Modtag kr. 1.000 af banken", 1000);
		
		pile[22] = new MoveCards("Gå i fængesel. Ryk direkte til fængslet. Selv om De passerer \"START\" indkasserer de ikke kr. 4.000", MoveCardTypes.GOTOJAIL, 0);
		pile[23] = new MoveCards("Gå i fængesel. Ryk direkte til fængslet. Selv om De passerer \"START\" indkasserer de ikke kr. 4.000", MoveCardTypes.GOTOJAIL, 0);
		pile[24] = new MoveCards("Ryk brikken frem til Frederiksberg Allé. Hvis De passerer \"START\" indkassér da kr. 4.000", MoveCardTypes.MOVETO, 11);
		pile[25] = new MoveCards("Ryk tre felter frem", MoveCardTypes.MOVEFORWARD, 3);
		pile[26] = new MoveCards("Ryk tre felter tilbage", MoveCardTypes.MOVEFORWARD, -3);
		pile[27] = new MoveCards("Ryk tre felter tilbage", MoveCardTypes.MOVEFORWARD, -3);
		pile[28] = new MoveCards("Ryk frem til \"START\"", MoveCardTypes.MOVETO, 1);
		pile[29] = new MoveCards("Ryk frem til \"START\"", MoveCardTypes.MOVETO, 1);
		pile[30] = new MoveCards("Tag ind på Rådhuspladsen", MoveCardTypes.MOVETO, 40);
		pile[31] = new MoveCards("Ryk frem til Grønningen. Hvis De passerer \"START\", inkassér da kr. 4.000", MoveCardTypes.MOVETO, 25);
		pile[32] = new MoveCards("Ryk frem til Vimmelskaftet. Hvis De passerer \"START\", inkassér da kr. 4.000", MoveCardTypes.MOVETO, 33);
		pile[33] = new MoveCards("Ryk frem til Strandvejen. Hvis De passerer \"START\", inkassér da kr. 4.000", MoveCardTypes.MOVETO, 20);
	}
	
	/**
	 * Blander bunken
	 */
	private void shufflePile() {
		for (int i = 0; i < pile.length; i++) {
			int randNum = (int) (Math.random() * pile.length);
			while (shuffledPile[randNum] != null) {
				randNum = (int) (Math.random() * pile.length);
			}
			shuffledPile[randNum] = pile[i];
		}
	}

}
