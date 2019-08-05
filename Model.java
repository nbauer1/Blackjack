/**
 * 
 * @author Nick Bauer
 * @version Model
 * ITEC 220
 * Project 5
 * 
 */

import java.util.Random;

/**
 * class Model
 * 
 * sets the Model for the Blackjack
 */
public class Model 
{
	private int hand;
	private int dealerHand;
	private int card1;
	private int card2;
	private int dealerCard1;
	private int dealerCard2;
	private int newCard = 0;
	private int funds = 1000;
	private int bet;
	
	private String outcomeString = "<html>";
	
	/**
	 * bankrupt
	 * 
	 * checks funds to determine if user is bankrupt
	 * @return whether or not user is bankrupt
	 */
	public boolean bankrupt()
	{
		boolean bankrupt = false;
		if(funds <= 0)
		{
			bankrupt = true;
		}
		return bankrupt;
	}
	
	/**
	 * genRandNum
	 * 
	 * generates a random number (2-14) to set a new card
	 * @return int of new card
	 */
	public int genRandNum()
	{
		Random rand = new Random();
		int num = rand.nextInt(13) + 2;
		return num;
	}
	
	/**
	 * setBetInput
	 * 
	 * sets instance variable bet
	 * @param _bet
	 */
	public void setBetInput(int _bet)
	{
		bet = _bet;
	}

	/**
	 * setFaces
	 * 
	 * corrects Face values to 10 & Ace values to 1 or 14
	 */
	public void setFaces()
	{
		if(card1 > 10 && card1 < 14)
			card1 = 10;
		else if(card1 == 14)
			card1 = 0;
		if(card2 > 10 && card2 < 14)
			card2 = 10;
		else if(card2 == 14)
			card2 = 0;
		if(dealerCard1 > 10 && dealerCard1 < 14)
			dealerCard1 = 10;
		else if(dealerCard1 == 14)
			dealerCard1 = 0;
		if(dealerCard2 > 10 && dealerCard2 < 14)
			dealerCard2 = 10;
		else if(dealerCard2 == 14)
			dealerCard2 = 0;
	}
	
	/**
	 * setAces
	 * 
	 * sets correct Ace value for Hands
	 */
	public void setAces()
	{
		if(card1 == 0 && hand <= 10)
			card1 = 11;
		else if(card1 == 0 && hand > 10)
			card1 = 1;
		if(card2 == 0 && hand <= 10)
			card2 = 11;
		else if(card2 == 0 && hand > 10)
			card2 = 1;
		if(dealerCard1 == 0 && dealerHand <= 10)
			dealerCard1 = 11;
		else if(dealerCard1 == 0 && dealerHand > 10)
			dealerCard1 = 1;
		if(dealerCard2 == 0 && dealerHand <= 10)
			dealerCard2 = 11;
		else if(dealerCard2 == 0 && dealerHand > 10)
			dealerCard2 = 1;
	}

	/**
	 * updateAce
	 * 
	 * updates any Ace value to prevent busting
	 */
	public void updateAce()
	{
		if(card1 == 11 && hand > 21)
		{
			card1 = 1;
			hand -= 10;
		}
		if(card2 == 11 && hand > 21)
		{
			card2 = 1;
			hand -= 10;
		}
		if(dealerCard1 == 11 && dealerHand > 21)
		{
			dealerCard1 = 1;
			dealerHand -= 10;
		}
		if(dealerCard2 == 11 && dealerHand > 21)
		{
			dealerCard2 = 1;
			dealerHand -= 10;
		}
	}
	
	/**
	 * updateHands
	 * 
	 * adds card values to update hand totals
	 */
	public void updateHands()
	{
		hand = card1 + card2;
		dealerHand = dealerCard1 + dealerCard2;
	}
	
	/**
	 * genCards
	 * 
	 * generates random number for cards 
	 */
	public void genCards()
	{
		card1 = genRandNum();
		card2 = genRandNum();
		
		dealerCard1 = genRandNum();
		dealerCard2 = genRandNum();
	}
	
	/**
	 * addHitCard
	 * 
	 * adds random card when user hits
	 * @return card number
	 */
	public int addHitCard()
	{
		int temp = genRandNum();
		newCard = temp;
		if(newCard > 10 && newCard < 14)
			newCard = 10;
		else if(newCard == 14 && hand <= 10)
			newCard = 11;
		else if(newCard == 14 && hand > 10)
			newCard = 1;
		hand += newCard;
		return temp;
	}
	
	/**
	 * dealerHit
	 * 
	 * adds random card when dealer hits
	 * @return card number
	 */
	public int dealerHit()
	{
		int temp = genRandNum();
		newCard = temp;
		if(newCard > 10 && newCard < 14)
			newCard = 10;
		else if(newCard == 14 && dealerHand <= 10)
			newCard = 11;
		else if(newCard == 14 && dealerHand > 10)
			newCard = 1;
		dealerHand += newCard;
		return newCard;
	}
	
	/**
	 * bust
	 * 
	 * checks to see if user has busted
	 * @return boolean if user has busted
	 */
	public boolean bust()
	{
		boolean result = false;
		if(hand > 21)
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * dealerBust
	 * 
	 * checks to see if dealer has busted
	 * @return boolean if dealer has busted
	 */
	public boolean dealerBust()
	{
		boolean result = false;
		if(dealerHand > 21)
		{
			result = true;
		}
		return result;
	}
	/**
	 * outcome
	 * 
	 * determines outcome of the match
	 */
	public void outcome()
	{
		outcomeString = "";
		outcomeString += "<html>";
		boolean ifWon = false;
		boolean ifLost = false;
		if(bust())
		{
			outcomeString += "Your Hand Total is: " + hand + "<br/>You've busted.<br/>";
			lose();
			ifLost = true;
		}
		if(dealerBust() && !bust())
		{
			outcomeString += "The dealer busted.<br/>";
			win();
			ifWon = true;
		}
		if(hand == dealerHand && !bust() && !dealerBust())
			outcomeString += "You Tied With the Dealer<br/>";
		if(hand == 21)
			outcomeString += "Congratulations, you got <br/>a Blackjack!<br/>";
		if(hand > dealerHand && !bust() && !ifWon)
			win();
		else if(dealerHand > hand && !dealerBust() && !ifLost)
			lose();
		outcomeString += "</html>";
	}
	
	/**
	 * win
	 * 
	 * sets results of winning
	 */
	public void win()
	{
		funds += bet;
		outcomeString += "Congratulations, you won $" + bet + "!<br/>";
	}
	
	/**
	 * lose
	 * 
	 * sets results of losing
	 */
	public void lose()
	{
		funds -= bet;
		outcomeString += "Sorry, you lost.<br/>";
	}
	
	/**
	 * getOutcomeString
	 * 
	 * gets outcomeString to display
	 * @return String with outcome
	 */
	public String getOutcomeString()
	{
		return outcomeString;
	}
	
	/**
	 * getFunds
	 * 
	 * gets funds amount
	 */
	public int getFunds()
	{
		return funds;
	}
	
	/**
	 * getCard1
	 * 
	 * gets user's first card
	 * @return int of first card
	 */
	public int getCard1()
	{
		return card1;
	}
	
	/**
	 * getCard2
	 * 
	 * gets user's second card
	 * @return int of second card
	 */
	public int getCard2()
	{
		return card2;
	}
	
	/**
	 * getDealerCard1
	 * 
	 * gets dealer's first card
	 * @return int of first card
	 */
	public int getDealerCard1()
	{
		return dealerCard1;
	}
	
	/**
	 * getDealerCard2
	 * 
	 * gets dealer's second card
	 * @return int of second card
	 */
	public int getDealerCard2()
	{
		return dealerCard2;
	}
	
	/**
	 * getHand
	 * 
	 * gets user's hand total
	 * @return int of total
	 */
	public int getHand()
	{
		return hand;
	}
	
	/**
	 * getDealerHand
	 * 
	 * gets dealer's hand total
	 * @return int of total
	 */
	public int getDealerHand()
	{
		return dealerHand;
	}
}
