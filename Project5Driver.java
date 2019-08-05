/**
 * 
 * @author Nick Bauer
 * @version Driver
 * ITEC 220
 * Project 5
 * 
 */

/**
 * class Project5Driver
 * 
 * contains main method to run Blackjack
 */
public class Project5Driver 
{
	/**
	 * main
	 * 
	 * main method creates View & Model to be a parameter for a Controller
	 * @param args
	 */
	public static void main(String[] args)
	{
		View theView = new View();
		Model theModel = new Model();
		new Controller(theView, theModel);
	}
}