import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Extends super class CoinSorter
public class CoinSorterGUI extends CoinSorter 
{
	// Arrays to hold the main menu, sub menu and exclude coin type choices
	private String optionsMain[] = {"Coin calculator", "Multiple coin calculator", "Print coin list", "Set details", "Display program configurations", "Quit the program"};
	private String optionsSub[] = {"Set Currency", "Set minimum coin input value", "Set maximum coin input value", "Return to main menu"};
	private Integer[] optionsCoinType = {10,20,50,100,200};
	//Call the methods, display menu and sub menu and get position of users choice
	private int option = getMenuOption();
	private int optionSub = getMenuSubOption();
	
	// Constructor which calls the CoinSorter constructor in the super class 
	public CoinSorterGUI(String currencyIn, int minCoin, int maxCoin, List <Integer> coinList)
	{
		super(currencyIn, minCoin, maxCoin, coinList);
	}
	
	// Gets the position of the users menu choice and displays the main menu of choices
	public int getMenuOption()
    {
        int option = JOptionPane.showOptionDialog(null, "Please select an option","***Coin Sorter - Main Menu***",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsMain, optionsMain[0]);
        
        return option;
    }
    
    // Gets the position of the users choice in the sub menu and displays the menu of choices
	public int getMenuSubOption()
    {
        int optionSub = JOptionPane.showOptionDialog(null, "Please select an option","***Set Details - Sub Menu***",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsSub, optionsSub[0]);
        
        return optionSub;
    }
	
	// Actions made on each menu choice
    public void graphicalMenu()
    {
    	// Check that the exit option has not been selected by the user
       while (option != 5)
       {
    	   // Check which choice the user has made and call the methods inherited from the CoinSorter class
    	   if (option == 0)  
    	   {
    		   // Prompt user for total coin value
    		   validateTotalCoinValue();

    		   // Call the calculation method from super class and display in dialog box
    		   JOptionPane.showMessageDialog(null,coinCalculator(50));
    	   }
    	   else if (option == 1)
    	   {
    		   // Prompt for user input
    		   validateTotalCoinValue();
    		   validateExcludedCoin();
    		   
    		   //Display results
    		   JOptionPane.showMessageDialog(null,multiCoinCalculator());
    	   }     
    	   else if (option == 2)
    	   {
    		   // Print list of coin type
    		   JOptionPane.showMessageDialog(null,printCoinList());
    	   }   
    	   //Sub menu is chosen 
    	   else if (option == 3)
    	   {
    		   // Check to see if the user has chosen exit
    		   while (optionSub != 3)
    		   {
    			   // Change the settings and display change to the user
    			   if (optionSub == 0)
    			   {
    				   // Set the currency as per the brief
    				   setCurrency("Euro");
    				   JOptionPane.showMessageDialog(null,"You set currency to " + getCurrency());
    				   break;
    			   } 			   
    			   else if (optionSub == 1)
    			   {
    				   setMinCoinIn(10);
    				   JOptionPane.showMessageDialog(null,"You set minimum coin value to " + getMinCoinIn());
    				   break;
    			   }          	  
    			   else if (optionSub == 2)
    			   {
    				   setMaxCoinIn(1000);
    				   JOptionPane.showMessageDialog(null,"You set minimum coin value to " + getMaxCoinIn());
    			   }
    			   else
    			   {
    				   invalidChoice();
    			   }
    			   
    			   // Show the menu again after the choice has been made
    			   optionSub = getMenuSubOption();
    		   }
    	   }
    	   else if (option == 4)
    	   {
    		   JOptionPane.showMessageDialog(null,displayProgramConfigs());
    	   }
    	   // Default e.g pressing the x instead of exit button will show error message
    	   else 
    	   {
    		   	invalidChoice();
    	   }
    	   // Display the menu again after choice if not exit
    	   option=getMenuOption();  
        }
    }
    
    // Invalid message dialog box
    public void invalidChoice()
    {
    	JLabel label = new JLabel("Invalid choice");
		label.setFont(new Font("Arial", Font.BOLD,40));
		JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Prompt user for total coin value in the dialog box and validates, inherits from superclass
    @Override public void validateTotalCoinValue()
    	{
    		do
		    {
    			// Prompt user for value within range
    			String totalCoinValueStr = JOptionPane.showInputDialog(null,"What is your total coin value in pennies between " + getMinCoinIn() + " and " + getMaxCoinIn(),"Coin value", JOptionPane.QUESTION_MESSAGE);
    			// Check to see if a value has been input.
    			if ((totalCoinValueStr != null) && (totalCoinValueStr.length() > 0)) 
				{
    				// Convert string value to int
        			int totalCoinValue = Integer.parseInt(totalCoinValueStr);
        			
        			// Update totalCoinValue property from the super class
        			setTotalCoinValue(totalCoinValue);
    			}
    			else {
    				// Show error message to user
    				invalidChoice();
    			
    				// Redirect user to main menu
    				option = getMenuOption(); 
    			}
		     }
		      
		    //Check the user input is within the set range  
			while((getTotalCoinValue() < getMinCoinIn()) || (getTotalCoinValue() > getMaxCoinIn()));
    	}
    
   // Prompt user for coin type to exclude in the dialog box, inherits from superclass
    @Override public void validateExcludedCoin()
    {
    	// Drop down choice box of coin type options for user to choose from
    	Integer excludedCoin  = (Integer) JOptionPane.showInputDialog(null,"Which coin type in pennies would you like to exclude","Coin type", JOptionPane.QUESTION_MESSAGE, null, optionsCoinType, optionsCoinType[0]);
    			
    	if (excludedCoin != null)
    	{
    		// Update exclude coin variable in the superclass
    		setExcludedCoin(excludedCoin);
    	}
    	else
    	{
    		invalidChoice();
    		
    		// Return user to the main menu
    		option = getMenuOption(); 
    	}
    }
} 
