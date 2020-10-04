import java.util.Arrays;
import java.util.Scanner;

public class testCoinSorter 
{
	public static void main(String[] args) 
	{
		// Create CoinSorter object with default details from the brief
		CoinSorter cs = new CoinSorter("pound", 0,10000, Arrays.asList(10,20,50,100,200));
	
		// Selection menu
		int menu;
		int submenu;
			
		do
		{
			// Display menu
			Scanner sc = new Scanner(System.in);
			
			System.out.println("\n***Coin Sorter - Main Menu***");
		    System.out.println("1 - Coin Calculator \n" + "2 - Multiple coin calculator \n" + "3 - Print coin list \n" +
		    				"4 - Set details \n" + "5 - Display program configurations \n" + "6 - Quit the program \n");

		    // Prompt user for choice repeated until the user chooses to quit
		    System.out.println("Please choose menu item: ");
		    menu = sc.nextInt();		
		    
		    // Apply functionality to each menu option, call instance methods from CoinSorter
		    switch (menu) 
		    {
		    case 1:
		    	cs.validateTotalCoinValue();
		    	System.out.println(cs.coinCalculator(50));
		    	break;
		    case 2:
		    	cs.validateTotalCoinValue();
		    	cs.validateExcludedCoin();
		    	System.out.println(cs.multiCoinCalculator());
		    	break;
		    case 3:
		    	System.out.println(cs.printCoinList());
		    	break;
		    case 4:
		    	// Sub menu
		    	do 
		    	{
		    		System.out.println("***Set Details Sub-Menu***");
		    		System.out.println("1 - Set currency \n" + "2 - Set minimum coin input value \n" + "3 - Set maximum coin input value \n" +
		    			        "4 - Return to main menu \n");
		    		// Prompt user for choice repeated until the user chooses to quit
		    		System.out.println("Please choose menu item: ");
		    		submenu = sc.nextInt();
		    			         
		    		switch (submenu) 
		    		{
		    		case 1:
		    			cs.setCurrency("Euro");
		    			System.out.println(cs.getCurrency());
		    			break;
		    		case 2:
		    			cs.setMinCoinIn(10);
		    			System.out.println(cs.getMinCoinIn());
		    			break;
		    		case 3:
		    			cs.setMaxCoinIn(1000);
		    			System.out.println(cs.getMaxCoinIn());
		    		case 4:	    			
		    			break;
		    		default:
		    			System.out.println("Invalid action.");
		    			break;
		    		}
		    	// Check user has not chosen to quit	
		    	}while(submenu != 4);
		    	break;
		    case 5:
		    	System.out.println(cs.displayProgramConfigs());
		    	break;
		    case 6:
		    	break;
		    default:
		    	System.out.println("Invalid selection.");
		    	break;
		    }
		// Check user has no chosen quit    
		}while(menu != 6);
	}
}
