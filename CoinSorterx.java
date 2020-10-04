import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to calculate the number of specified coins of certain denominations that
 * can be exchanged for a specified value in pennies. 
 * 
 * @version v0
 */

public class CoinSorter
{
	//class properties 
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private int totalCoinValue;
	private int excludedCoin;
	private List<Integer> coinList;
	
	//Constructor to initialise properties
	public CoinSorter(String currencyIn, int minCoin, int maxCoin, List <Integer> coinList)
	{
		currency = currencyIn;
		minCoinIn = minCoin;
		maxCoinIn = maxCoin;
		//Assigns the actual parameter list value to the coinList field
		this.coinList = coinList;
	}
	
	//Constructor (overloaded)
	public CoinSorter() {		
	}
	
	//Set the attributes; setter methods to assign the parameter value to the attribute
	public void setCurrency(String currencyIn)
	{
		this.currency = currencyIn;
	}
	
	public void setMinCoinIn(int minCoinIn)
	{
		this.minCoinIn = minCoinIn;
	}
	
	public void setMaxCoinIn(int maxCoinIn) 
	{
		this.maxCoinIn = maxCoinIn;
	}
	
	// Set method used to update totalCoinValue attribute in the subclass CoinSorterGUI
	public void setTotalCoinValue(int totalCoinValue)
	{
		this.totalCoinValue = totalCoinValue;
	}
	
	// Set method used to update excluded coin attribute in the subclass CoinSorterGUI
	public void setExcludedCoin(int excludedCoin)
	{
		this.excludedCoin = excludedCoin;
	}

	// Return the attribute values, get access to the properties from outside the class
	public String getCurrency()
	{
		return currency;
	}
	
	public int getMinCoinIn()
	{
		return minCoinIn;
	}
	
	public int getMaxCoinIn()
	{
		return maxCoinIn;
	}
	
	// To get the value to use in the subclass
	public int getTotalCoinValue() 
	{
		return totalCoinValue;
	}	
	
	// To get the value to use in the subclass
	public List<Integer> getCoinList() {
	    return new ArrayList<Integer>(this.coinList);   
	}

	//prints the coin denominations that are currently in circulation.
	public String printCoinList()
	{
		return "The current coin denominations are in circulation: " + coinList;
	}
	
	/* Prompt user for total amount of coins to be exchanged and validate that it is within the range set.
	 */
	public void validateTotalCoinValue() 
	{
	    // New scanner class  
		Scanner sc=new Scanner(System.in);
	      
	    // Prompt user for input and store result  
		do
	      {
	    	  System.out.print("Please enter total amount of money in pennies to be exchanged within the set margins: ");
	    	  //Set the value to user input
	    	  setTotalCoinValue(getTotalCoinValue() + sc.nextInt());
	      }
	    //Check the user input is within the set range  
		while((getTotalCoinValue() < minCoinIn) || (getTotalCoinValue() > maxCoinIn)); 	     
	}
	
	/* Prompt user for coin denominator to be excluded from the exchange and validate that the chosen coin is in 
	 * circulation.
	 */
	public void validateExcludedCoin() 
	{
		Scanner sc=new Scanner(System.in);
	     
		// To check if the users choice is in the circulating coins set, initialised to not in circulation
		boolean validated = false;

		 do {
			 	System.out.print("Please enter the coin denominations in pennies to be excluded from the exchange from the coin list: ");
			 	excludedCoin +=sc.nextInt();
			 		 	
			 	/* Iterate through the coin list checking if the user input matches any of the elements values, if it 
			 	 * does change the value of validate variable to true.
			 	 */
			 	for(int i=0; i<coinList.size(); i++) 
			 	{
			 		if (excludedCoin == coinList.get(i)) 
			 		{
			 			validated = true;
		 				break;
		 			}	 
		         }
		 // If the users choice is not in the list of coins prompt user again for new value    
		 }while (validated == false);
	}

	/* Calculates the total number of the coin type, value input in the method call, and the remainder that can be 
	 * exchanged from the total value input by the user
	 * only one parameter used as totalCoinValue's state is changed in validateTotalCoinValue()
	 */ 
	public String coinCalculator(int coinType)
	{
		// Get the total number of the coin type that can be exchanged
		int coinCount = getTotalCoinValue()/coinType;
		// Get the remainder
		int remainder = getTotalCoinValue() % coinType;
		
		return "A total of " + coinCount + " x " + coinType + "p coins can be exchanged, with a remainder of " + remainder + "p";	   
	}  
	
    /* Calculates the total number of each coin type available, excluding the chosen coin type by the user, that can be 
     * exchanged from the total value inputed. Assume that the coin denominators are not known in case it 
     * is changed in the constructor from the default value in the future, no parameters as total value and coin type
     * are updated in the validate methods.
     */
	public String multiCoinCalculator()
	{
		// Make a copy of the list because Arrays.asList returns a fixed sized list
		List<Integer> coinListcopy = new ArrayList<>(coinList);
			
		// Remove the excluded coin chosen by the user
		coinListcopy.remove(Integer.valueOf(excludedCoin));
			
		/* Sort list into ascending order so total value can be exchanged for the largest coin first in case the 
		 * values in constructor are not in order
		 */
		Collections.sort(coinListcopy);
			
		int i = 0;
		
		// Initialise a new array of size coin list to store the number of coins for each denominator
		int[]coinCount = new int[coinList.size()];
			
		/* Check to see if the total coin amount is greater than each coin denominator available no need to do the
		 * calculations on a coin that is smaller. 
		 */
		for (i = coinListcopy.size() - 1; i >= 0; i--)
		{
			if (getTotalCoinValue() >= coinListcopy.get(i))
			{
				// Calculates total amount of coins	
				int count =+ getTotalCoinValue()/coinListcopy.get(i);
				// Remaining value after exchange to exchange for next coin denominator that is smaller
				setTotalCoinValue(getTotalCoinValue() % coinListcopy.get(i));	
				// Add amount of coins for the coin type to the new array
				coinCount[i] = count;		
			}
			else
			{
				int count=0;
				coinCount[i] = count;
			}	
		}
		return "The coins exchanged are: "+ coinCount[3] + " x " + coinListcopy.get(3) + "p, " + coinCount[2] + " x " +
		coinListcopy.get(2) + "p, " + coinCount[1] + " x " + coinListcopy.get(1) + "p, " + coinCount[0] + 
		" x " + coinListcopy.get(0) + "p, with a remainder of " + getTotalCoinValue() +"p";
	}
	
	// Return the current currency and the current minimum and maximum value accepted as input as a string
	public String displayProgramConfigs()
	{
		return "The current currency is: " + getCurrency() + " The current minimum value is: " + getMinCoinIn() + 
				" The current maximum value is: " + getMaxCoinIn();
	}
}
