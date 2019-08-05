/**
 * 
 * @author Nick Bauer
 * @version View
 * ITEC 220
 * Project 5
 * 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * class View
 * 
 * sets the View for the Retirement Calculator
 */
public class View
{	
 	private ImageIcon card1;
	private ImageIcon card2;
	private ImageIcon dealerCard1;
	private ImageIcon dealerCard2;
	private ImageIcon newCard;
	
	private JButton jbBet = new JButton("Place Bet");
	private JButton jbQuit = new JButton("Quit");
	private JButton jbHit = new JButton("Hit");
	private JButton jbStay = new JButton("Stay");
	private JButton jbAllIn = new JButton("All-In");
	private JButton jbReset = new JButton("Reset");
	
	private JTextField betInput = new JTextField(20);
	private JTextField bank = new JTextField(20);
	
	private JLabel lfunds = new JLabel("Your Current Funds:");
	private JLabel lbet = new JLabel("Enter Your Bet:");
	private JLabel l1 = new JLabel("Your Hand:");
	private JLabel l2 = new JLabel("Dealer's Hand:");
	private JLabel outcomeLabel = new JLabel();
	private JLabel lresult = new JLabel("Outcome of Last Hand:");
	
	private JFrame menu = new JFrame("Blackjack Menu");
	private JFrame f = new JFrame("Blackjack!");
	private JFrame outcomeFrame = new JFrame("Outcome");
	
	private JPanel menuPanel = new JPanel();
	private JPanel p =  new JPanel();
	private JPanel outcomePanel = new JPanel();
	
	private int frameXSize = 500;
	private int frameYSize = 600;
	private int outcomeCount = 0;
	private int hitCount = 3;
	private int dealerHitCount = 3;
	
	/**
	 * invisibleButtons
	 * 
	 * sets hit and stay buttons to invisible 
	 */
	public void invisibleButtons()
	{
		jbHit.setVisible(false);
		jbStay.setVisible(false);
	}
	
	/**
	 * visibleButtons
	 * 
	 * sets hit and stay buttons to visible 
	 */
	public void visibleButtons()
	{
		jbHit.setVisible(true);
		jbStay.setVisible(true);
	}
	
	/**
	 * doStayClick
	 * 
	 * simulated clicking stay button
	 */
	public void doStayClick()
	{
		jbStay.doClick();
	}
	
	/**
	 * resetFrame
	 * 
	 * resets frame size to origin
	 */
	public void resetFrame()
	{
		frameXSize = 500;
		frameYSize = 600;
	}
	
	/**
	 * reset
	 * 
	 * resets hit counts and JPanel
	 */
	public void reset()
	{
		dealerHitCount = 3;
		hitCount = 3;
		f.remove(p);
		f.revalidate();
		f.pack();
		
		p =  new JPanel();
	}
	
	/**
	 * addHitCard
	 * 
	 * sets newCard by number passed from model
	 * @param hitCard
	 */
	public void addHitCard(int hitCard)
	{
		newCard = setImageIcon(hitCard);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(10,5,1,1);
		c.gridx = hitCount;
		c.gridy = 1;
		p.add(new JLabel(newCard), c);
		hitCount++;
	}
	
	/**
	 * addDealerHitCard
	 * 
	 * sets newCard by number passed from model
	 * @param hitCard
	 */
	public void addDealerHitCard(int hitCard)
	{
		newCard = setImageIcon(hitCard);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(10,5,1,1);
		c.gridx = dealerHitCount;
		c.gridy = 0;
		p.add(new JLabel(newCard), c);
		dealerHitCount++;
	}
	
	/**
	 * hitFrame
	 * 
	 * edits JFrame to correspond with user's new cards
	 */
	public void hitFrame()
	{
		frameXSize += 150;
		f.setPreferredSize(new Dimension(frameXSize, frameYSize));
		f.repaint();
		f.revalidate();
		f.pack();
	}
	
	/**
	 * adjustFrameSize
	 * 
	 * edits JFrame to correspond with user's and dealer's new cards
	 */
	public void adjustFrameSize()
	{
		if(hitCount > dealerHitCount)
			frameXSize = 500 + (hitCount - 3) * 150;
		else if(dealerHitCount > hitCount)
			frameXSize = 500 + (dealerHitCount - 3) * 150;
		f.setPreferredSize(new Dimension(frameXSize, frameYSize));
		f.repaint();
		f.revalidate();
		f.pack();
	}
	
	/**
	 * removeOutcomeLabel
	 * 
	 * removes the outcomeLabel from outcomeFrame
	 */
	public void removeOutcomeLabel()
	{
		if(outcomeCount >= 1)
			outcomePanel.remove(outcomeLabel);
		outcomeFrame.pack();
		outcomeFrame.setLocation(400,225);
		outcomeFrame.setVisible(true);
	}
	
	/**
	 * resetBet
	 * 
	 * resets bet input JTextField
	 */
	public void resetBet()
	{
		betInput.setText("");
	}
	
	/**
	 * disposeJFrame
	 * 
	 * disposes JFrame
	 */
	public void disposeJFrame()
	{
		f.dispose();
	}
	
	/**
	 * disposeMenu
	 * 
	 * disposes menu frame
	 */
	public void disposeMenu()
	{
		menu.dispose();
	}
	
	/**
	 * disposeOutcomeFrame
	 * 
	 * disposes of outcomeFrame
	 */
	public void disposeOutcomeFrame()
	{
		outcomeFrame.dispose();
	}
	
	/**
	 * getBetInput
	 * 
	 * gets user's bet input
	 * @return int bet
	 */
	public int getBetInput()
	{
		return Integer.parseInt(betInput.getText());
	}
	
	/**
	 * setFrameStyle
	 * 
	 * sets the style to JFrame
	 */
	public void setFrameStyle()
	{
		Font labelFont = new Font("Impact", Font.BOLD, 20);
		Dimension buttonSize = new Dimension(150,50);
		
		jbHit.setPreferredSize(buttonSize);
		jbStay.setPreferredSize(buttonSize);
		
		l1.setFont(labelFont);
		l2.setFont(labelFont);
		outcomeLabel.setFont(labelFont);
		
		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		outcomeLabel.setForeground(Color.white);
		
		Color felt = new Color(0, 130, 0);
		Color wood = new Color(120, 81, 21);
		p.setBackground(felt);
		p.setOpaque(true);
		p.setBorder(BorderFactory.createLineBorder(wood, 20));
	}
	
	/**
	 * setMenuFrameStyle
	 * 
	 * sets menu frame styles
	 */
	public void setMenuFrameStyle()
	{
		Font labelFont = new Font("Impact", Font.BOLD, 30);
		Font buttonFont = new Font("Helvetica", Font.BOLD, 20);
		Dimension buttonSize = new Dimension(150,50);
		Color felt = new Color(0, 130, 0);
		Color wood = new Color(120, 81, 21);
		
		lfunds.setFont(labelFont);
		lbet.setFont(labelFont);
		l1.setFont(labelFont);
		l2.setFont(labelFont);
		outcomeLabel.setFont(labelFont);
		jbBet.setFont(buttonFont);
		jbQuit.setFont(buttonFont);
		jbAllIn.setFont(buttonFont);
		jbHit.setFont(buttonFont);
		jbStay.setFont(buttonFont);
		jbQuit.setPreferredSize(buttonSize);
		jbAllIn.setPreferredSize(buttonSize);
		lfunds.setForeground(Color.white);
		lbet.setForeground(Color.white);
		
		menuPanel.setBackground(felt);
		menuPanel.setOpaque(true);
		menuPanel.setBorder(BorderFactory.createLineBorder(wood, 20));
	}
	
	/**
	 * setOutcomeFrameStyle
	 * 
	 * sets style for outcomeFrame
	 */
	public void setOutcomeFrameStyle()
	{
		Font labelFont = new Font("Impact", Font.BOLD, 30);
		Font buttonFont = new Font("Helvetica", Font.BOLD, 20);
		Dimension buttonSize = new Dimension(150,50);
		Color felt = new Color(0, 130, 0);
		Color wood = new Color(120, 81, 21);
		
		outcomeLabel.setForeground(Color.white);
		jbReset.setFont(buttonFont);
		jbReset.setSize(buttonSize);
		jbQuit.setSize(buttonSize);
		lresult.setFont(labelFont);
		outcomeLabel.setFont(labelFont);
		outcomePanel.setBackground(felt);
		outcomePanel.setOpaque(true);
		outcomePanel.setBorder(BorderFactory.createLineBorder(wood, 20));
	}
	
	/**
	 * setBank
	 * 
	 * sets bank amount
	 * @param int amount
	 */
	public void setBank(int amount)
	{
		bank.setText(Integer.toString(amount));
	}
	
	/**
	 * setCard1
	 * 
	 * sets user's first card
	 * @param num passed from Model
	 */
	public void setCard1(int num)
	{
		card1 = setImageIcon(num);
	}
	
	/**
	 * setCard2
	 * 
	 * sets user's second card
	 * @param num passed from Model
	 */
	public void setCard2(int num)
	{
		card2 = setImageIcon(num);
	}
	
	/**
	 * setDealerCard1
	 * 
	 * sets dealer's first card
	 * @param num passed from Model
	 */
	public void setDealerCard1(int num)
	{
		dealerCard1 = setImageIcon(num);
	}
	
	/**
	 * setDealerCard2
	 * 
	 * sets dealer's second card
	 * @param num passed from Model
	 */
	public void setDealerCard2(int num)
	{
		dealerCard2 = setImageIcon(num);
	}
	
	/**
	 * setImageIcon
	 * 
	 * sets corresponding ImageIcon to appropriate cards
	 * @param num passed from Model
	 * @return desired ImageIcon
	 */
	public ImageIcon setImageIcon(int num) {
		ImageIcon ii = new ImageIcon();
		if(num == 1)
			ii = new ImageIcon(this.getClass().getResource("/Source/A.png"), "cardA");
		else if(num == 2)
			ii = new ImageIcon(this.getClass().getResource("/Source/2.png"), "card2");
		else if(num == 3)
			ii = new ImageIcon(this.getClass().getResource("/Source/3.png"), "card3");
		else if(num == 4)
			ii = new ImageIcon(this.getClass().getResource("/Source/4.png"), "card4");
		else if(num == 5)
			ii = new ImageIcon(this.getClass().getResource("/Source/5.png"), "card5");
		else if(num == 6)
			ii = new ImageIcon(this.getClass().getResource("/Source/6.png"), "card6");
		else if(num == 7)
			ii = new ImageIcon(this.getClass().getResource("/Source/7.png"), "card7");
		else if(num == 8)
			ii = new ImageIcon(this.getClass().getResource("/Source/8.png"), "card8");
		else if(num == 9)
			ii = new ImageIcon(this.getClass().getResource("/Source/9.png"), "card9");
		else if(num == 10)
			ii = new ImageIcon(this.getClass().getResource("/Source/10.png"), "card10");
		else if(num == 11)
			ii = new ImageIcon(this.getClass().getResource("/Source/J.png"), "cardJ");
		else if(num == 12)
			ii = new ImageIcon(this.getClass().getResource("/Source/Q.png"), "cardQ");
		else if(num == 13)
			ii = new ImageIcon(this.getClass().getResource("/Source/K.png"), "cardK");
		else if(num == 14)
			ii = new ImageIcon(this.getClass().getResource("/Source/A.png"), "cardA");
		return scaleImageIcon(ii);	}
	
	/**
	 * scaleImageIcon
	 * 
	 * scales a card's ImageIcon
	 * @param unscaled ImageIcon
	 * @return scaled ImageIcon
	 */
	public ImageIcon scaleImageIcon(ImageIcon ii)
	{
		Image image = ii.getImage();
		Image newimg = image.getScaledInstance(150, 200,  java.awt.Image.SCALE_SMOOTH); 
		ii = new ImageIcon(newimg);
		return ii;	
	}
	
	/**
	 * allIn
	 * 
	 * sets bet input to be amount in bank
	 */
	public void allIn()
	{
		betInput.setText(bank.getText());
	}
	
	/**
	 * addPanelElements
	 * 
	 * adds cards and buttons to JPanel
	 */
	public void addPanelElements()
	{
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(10,5,1,1);
		c.gridx = 0;
		c.gridy = 0;
		p.add(l2, c);
		c.gridx = 1;
		p.add(new JLabel(dealerCard1), c);
		c.gridx = 2;
		p.add(new JLabel(dealerCard2), c);
		c.gridx = 0;
		c.gridy = 1;
		p.add(l1, c);
		c.gridx = 1;
		p.add(new JLabel(card1), c);
		c.gridx = 2;
		p.add(new JLabel(card2), c);
		c.gridx = 1;
		c.gridy = 2;
		p.add(jbHit, c);
		c.gridx = 2;
		p.add(jbStay, c);
	}
	
	/**
	 * addMenuPanelElements
	 * 
	 * adds all elements to menuPanel
	 */
	public void addMenuPanelElements()
	{
		menuPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(10,5,1,1);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		menuPanel.add(lfunds, c);
		c.gridy = 1;
		menuPanel.add(bank, c);
		c.gridy = 2;
		menuPanel.add(lbet, c);
		c.gridy = 3;
		menuPanel.add(betInput, c);
		c.gridwidth = 1;
		c.gridy = 4;
		menuPanel.add(jbBet, c);
		c.gridx = 1;
		c.gridy = 4;
		menuPanel.add(jbAllIn, c);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 5;
		menuPanel.add(jbQuit, c);
		bank.setEditable(false);
	}
	
	/**
	 * addOucomePanelElements
	 * 
	 * adds all elements to outcomePanel
	 * @param String passed in from Model
	 */
	public void addOucomePanelElements(String str)
	{
		outcomeCount++;
		outcomeFrame.setPreferredSize(new Dimension(500,400));
		outcomePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(10,5,1,1);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		outcomePanel.add(lresult);
		outcomeLabel = new JLabel(str);
		c.gridx = 0;
		c.gridy = 1;
		outcomePanel.add(outcomeLabel, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		outcomePanel.add(jbReset, c);
		c.gridx = 1;
		c.gridy = 2;
		outcomePanel.add(jbQuit, c);
		setOutcomeFrameStyle();
		outcomeFrame.add(outcomePanel);
		outcomeFrame.pack();
		outcomeFrame.setVisible(true);
		outcomeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * hideDealerCard
	 * 
	 * hides dealer's first card from user
	 */
	public void hideDealerCard()
	{
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/Source/cardBack.png"), "cardBack");
		Image image = ii.getImage();
		Image newimg = image.getScaledInstance(150, 200,  java.awt.Image.SCALE_SMOOTH); 
		dealerCard1.setImage(newimg);
	}
	
	/**
	 * showDealerCard
	 * 
	 * shows the dealer's first card
	 * @param num
	 */
	public void showDealerCard(int num)
	{
		ImageIcon ii = setImageIcon(num);
		Image image = ii.getImage();
		dealerCard1.setImage(image);
	}
	
	/**
	 * hitListener
	 * 
	 * adds listener to JButton "Hit"
	 * @param listener
	 */
	public void hitListener(ActionListener listener)
	{
		jbHit.addActionListener(listener);
	}
	
	/**
	 * stayListener
	 * 
	 * adds listener to JButton "Stay"
	 * @param listener
	 */
	public void stayListener(ActionListener listener)
	{
		jbStay.addActionListener(listener);
	}
	
	/**
	 * betListener
	 * 
	 * adds listener to JButton "Bet"
	 * @param listener
	 */
	public void betListener(ActionListener listener)
	{
		jbBet.addActionListener(listener);
	}
	
	/**
	 * quitListener
	 * 
	 * adds listener to JButton "Quit"
	 * @param listener
	 */
	public void quitListener(ActionListener listener)
	{
		jbQuit.addActionListener(listener);
	}
	
	/**
	 * allInListener
	 * 
	 * adds listener to JButton "All-In"
	 * @param listener
	 */
	public void allInListener(ActionListener listener)
	{
		jbAllIn.addActionListener(listener);
	}
	
	/**
	 * resetListener
	 * 
	 * adds listener to JButton "Reset"
	 * @param listener
	 */
	public void resetListener(ActionListener listener)
	{
		jbReset.addActionListener(listener);
	}
	
	/**
	 * configureFrame
	 * 
	 * configures game's JFrame
	 */
	public void configureFrame()
	{
		f.setPreferredSize(new Dimension(frameXSize,frameYSize));
		f.add(p);
    	f.pack();
    	f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * configureMenuFrame
	 * 
	 * configures menu's JFrame
	 */
	public void configureMenuFrame()
	{
		menu.setPreferredSize(new Dimension(400,400));
		setMenuFrameStyle();
		menu.add(menuPanel);
		menu.pack();
		menu.setVisible(true);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * insufficientFundsError
	 * 
	 * error to be used when user's bet exceeded amount in bank
	 */
	public void insufficientFundsError()
	{
		String str = "Error: Insufficient Funds,\nPlease Enter a Lower Bet";
		JOptionPane.showMessageDialog(new JFrame(), str, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * invalidInputError
	 * 
	 * error to be used when invalid input is entered by user
	 */
	public void invalidInputError()
	{
		String str = "Error: Invalid Input,\nPlease Enter a Positive\nNumerical Bet";
		JOptionPane.showMessageDialog(new JFrame(), str, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * noRemainingFunds
	 * 
	 * error to be used to notify user when they have ran out of funds
	 */
	public void noRemainingFunds()
	{
		String str = "You Have Ran, Out of Funds\nBetter Luck Next Time!";
		JOptionPane.showMessageDialog(new JFrame(), str, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * hitError
	 * 
	 * error to be used when user has busted or achieved a blackjack
	 */
	public void hitError()
	{
		String str = "Error: You Cannot Hit Anymore";
		JOptionPane.showMessageDialog(new JFrame(), str, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
