/*
 * A program that tests that all of the functions and working
 * as intended. Customers and food shipments have been randomly
 * generated here for testing purposes.
 * 
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Random;

public class InNOutTest {
	public static void main(String[] args)
	{
		//member variables
		Random rand = new Random();
		InNOut test = new InNOut();
		test.setDate(1201);
		test.setCustomersLost(0);
		int numOfCustomers = 0;
		int orderNumber = 0;
		int nextShipment = 1;
		int total;
		int buns;
		int patty;
		int tomato;
		int lettuce;
		int onion;
		int cheese;
		
		//loop for each day
		for(int i = test.getDate(); i <= 1231; i++)
		{
			nextShipment--;
			if(nextShipment == 0)
			{
				//creates new shipment
				total = rand.nextInt(301) + 700;	
				buns = 0;
				patty = 0;
				tomato = 0;
				lettuce = 0;
				onion = 0;
				cheese = 0;
				
				while(total != 0)
				{
					int item = rand.nextInt(6) + 1;
					if(item == 1) 						
					{
						buns++;
					}
					else if(item == 2)
					{
						patty++;
					}
					else if(item == 3)
					{
						lettuce++;
					}
					else if(item == 4)
					{
						tomato++;
					}
					else if(item == 5)
					{
						onion++;
					}
					else
					{
						cheese++;
					}
					total--;
				}
				//adds new shipment
				test.newShipment(buns, patty, lettuce, tomato, onion, cheese);
				nextShipment = rand.nextInt(4) + 3;
			}
			//loop for each hour
			for(int j = 10; j >= 1; j--)
			{
				//have the customers order
				numOfCustomers = rand.nextInt(100) + 1;
				int tempNumOfCustomers = numOfCustomers;
				while(tempNumOfCustomers != 0)
				{
					orderNumber = rand.nextInt(6) + 1;
					if(!test.addCustomer(orderNumber))
					{
						//increments lost customers if line is full for the hour
						test.setCustomersLost(test.getCustomersLost() + 1);
					}
					tempNumOfCustomers--;
				}
				if(numOfCustomers > 50)
				{
					numOfCustomers = 50;
				}
				while(numOfCustomers != 0)
				{
					if(!test.Order())
					{
						//increments lost customers if not enough ingredients 
						test.setCustomersLost(test.getCustomersLost() + 1);
					}
					numOfCustomers--;
				}
			}
			
			//print data
			test.printDate();
			test.printNumOfLostCustomers();
			
			//increment the date
			test.setDate(test.getDate() + 1);
			
			//throw away the expired food
			test.removeExpired();
			
			//continue printing data
			test.printWastedIngredients();
			test.printNumOfItemsOrdered();
			test.printRecords();
			System.out.println();
			
			//clear all of the day's records
			test.clearRecords();
			test.clearWastedFood();
			test.clearItems();
			test.setCustomersLost(0);

		}
	}
}
