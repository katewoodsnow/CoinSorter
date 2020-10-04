import java.util.Arrays;

public class testCoinSorterGUI
{
	public static void main(String[] args) 
	{
		// Create an object with default details
		CoinSorterGUI csg = new CoinSorterGUI("pound", 0,10000, Arrays.asList(10,20,50,100,200));
		
		csg.graphicalMenu();
 	}
}
