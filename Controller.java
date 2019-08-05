/**
 * 
 * @author Nick Bauer
 * @version Controller
 * ITEC 220
 * Project 5
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class Controller
 * 
 * sets the Controller for the Blackjack
 */
public class Controller 
{
	private View theView;
	private Model theModel;
	
	/**
	 * Controller
	 * 
	 * sets theView and theModel instances
	 * @param theView from main method in Project5Driver
	 * @param theModel from main method in Project5Driver
	 */
	public Controller(View theView, Model theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		theView.setBank(theModel.getFunds());
		theView.setFrameStyle();
		theView.addMenuPanelElements();
		theView.configureMenuFrame();
			
		this.theView.hitListener(new hitListener());
		this.theView.stayListener(new stayListener());
		this.theView.betListener(new betListener());
		this.theView.quitListener(new quitListener());
		this.theView.allInListener(new allInListener());
		this.theView.resetListener(new resetListener());
	}

	/**
	 * class addListener
	 * 
	 * adds ActionListener for "Bet" JButton
	 */
	public class betListener  implements ActionListener
	{
		/**
		 * actionPerformed
		 * 
		 * sets bet and displays game frame
		 * @param ActionEvent's arg
		 */
		public void actionPerformed(ActionEvent arg)
		 {
			try 
			{
				if(theView.getBetInput() <= theModel.getFunds() && theView.getBetInput() > 0)
				{
					theModel.genCards();
					theView.resetFrame();
					theView.disposeMenu();
					setValues();
					theView.setFrameStyle();
					theView.addPanelElements();
					theView.hideDealerCard();
					theView.configureFrame();
					theModel.setFaces();
					theModel.updateHands();
					theModel.setAces();
					theModel.updateHands();
				}
				else if(theView.getBetInput() > theModel.getFunds())
					throw new ArithmeticException();
				else 
					throw new NumberFormatException();
			} catch (ArithmeticException e) {
				theView.insufficientFundsError();
			} catch (NumberFormatException e) {
				theView.invalidInputError();
			}
		 }
	}
	
	/**
	 * class quitListener
	 * 
	 * adds ActionListener for "Quit" JButton
	 */
	public class quitListener  implements ActionListener
	{
		/**
		 * actionPerformed
		 * 
		 * closes all frames and ends program
		 * @param ActionEvent's arg
		 */
		public void actionPerformed(ActionEvent arg)
		 {
				System.exit(0);		
		 }
	}
	
	/**
	 * class hitListener
	 * 
	 * adds ActionListener for "Hit" JButton
	 */
	public class hitListener implements ActionListener
	{
		/**
		 * actionPerformed
		 * 
		 * implements necessary actions for a user hit
		 * @param ActionEvent's arg
		 */
		public void actionPerformed(ActionEvent arg)
		 {
			try 
			{
				theModel.updateAce();
				if(theModel.getHand() < 21)
				{
					theView.hitFrame();
					theView.addHitCard(theModel.addHitCard());
					theModel.updateAce();

					if(theModel.getHand() > 21)
						theView.doStayClick();
				}
				else
					throw new ArithmeticException();
				
			} catch (ArithmeticException e) {
				theView.hitError();
			}
		 }
	}
	
	/**
	 * class stayListener
	 * 
	 * adds ActionListener for "Stay" Button
	 */
	public class stayListener implements ActionListener
	{
		/**
		 * actionPerformed
		 * 
		 * implements necessary actions for user ending their turn
		 * @param ActionEvent's arg
		 */
		public void actionPerformed(ActionEvent arg)
		 {
			theView.invisibleButtons();
			theModel.updateAce();
			while(theModel.getDealerHand() < 17)
			{
				theModel.updateAce();
				theView.addDealerHitCard(theModel.dealerHit());
				theModel.updateAce();
			}
			theView.adjustFrameSize();
			theModel.setAces();
			theView.showDealerCard(theModel.getDealerCard1());
			theModel.updateAce();
			theModel.outcome();
			
			theView.removeOutcomeLabel();
			theView.addOucomePanelElements(theModel.getOutcomeString());
		 }
	}
	
	/**
	 * class resetListener
	 * 
	 * adds ActionListener for "Reset" Button
	 */
	public class resetListener implements ActionListener
	{
		/**
		 * actionPerformed
		 * 
		 * resets game but keeps bank intact
		 * @param ActionEvent's arg
		 */
		public void actionPerformed(ActionEvent arg)
		 {
			try {
				theView.visibleButtons();
				theView.disposeJFrame();
				if(theModel.bankrupt())
					throw new NumberFormatException();
				theView.resetBet();
				theView.setBank(theModel.getFunds());
				theView.addMenuPanelElements();
				theView.configureMenuFrame();
				theView.reset();
				theView.disposeOutcomeFrame();
				
			} catch (NumberFormatException e) {
			 	theView.disposeOutcomeFrame();
				theView.noRemainingFunds();
				System.exit(0);
			}
		 }
	}
	
	/**
	 * class allInListener
	 * 
	 * adds ActionListener to button "All-In"
	 */
	public class allInListener implements ActionListener
	{
		/**
		 * actionPerformed
		 * 
		 * sets user's bet input to bank amount
		 * @param ActionEvent's arg
		 */
		public void actionPerformed(ActionEvent arg)
		 {
			theView.allIn();
		 }
	}
	
	/**
	 * setValues
	 * 
	 * sets values communicating between View and Model
	 */
	public void setValues()
	{
		theModel.setBetInput(theView.getBetInput());
		theView.setCard1(theModel.getCard1());
		theView.setCard2(theModel.getCard2());
		theView.setDealerCard1(theModel.getDealerCard1());
		theView.setDealerCard2(theModel.getDealerCard2());
	}
		
}
/*
 * https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
 * https://docs.oracle.com/javase/7/docs/api/java/awt/GridBagConstraints.html#insets
 * https://stackoverflow.com/questions/27587048/how-can-i-remove-jbutton-from-jframe
 * 
 */
