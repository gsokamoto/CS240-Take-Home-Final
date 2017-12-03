/*
 * A program that records all the data for In N Out, including:
 * number of customer lost, number of each wasted ingredients, 
 * number of each item sold, and a record of what item each customer ordered.
 * 
 * @author Grant Okamoto
 * @version 1.0
 * 
 */

import java.util.Iterator;

public class InNOut {

	public LinkedStack<Integer> buns;		
	private LinkedStack<Integer> patty;		
	private LinkedStack<Integer> lettuce;	
	private LinkedStack<Integer> tomato; 	
	private LinkedStack<Integer> onion;		
	private LinkedStack<Integer> cheese;	
	private ArrayQueue<Integer> queue;
	private SortedLinkedDictionary<Integer, Integer> records = new SortedLinkedDictionary<Integer, Integer>();
	private int wastedBuns;
	private int wastedPatty;
	private int wastedLettuce;
	private int wastedTomato;
	private int wastedOnion;
	private int wastedCheese;
	private int countItemOne;
	private int countItemTwo;
	private int countItemThree;
	private int countItemFour;
	private int countItemFive;
	private int countItemSix;
	private int customerNumber;
	private int date;
	private int customersLost;
	
	public InNOut()
	{
		buns = new LinkedStack<Integer>();
		patty= new LinkedStack<Integer>();
		lettuce = new LinkedStack<Integer>();
		tomato = new LinkedStack<Integer>();
		onion = new LinkedStack<Integer>();
		cheese = new LinkedStack<Integer>();
		queue = new ArrayQueue<Integer>(50);
		customerNumber = 1;
	}
	
	public void printDate()
	{
		System.out.println("Date: " + ((date - date % 100)/100) + "/" + date % 100);
	}
	
	public void printNumOfLostCustomers()
	{
		System.out.println("Number Of Customers Lost Today: " + customersLost);
	}
	
	public void printWastedIngredients()
	{
		System.out.println("Number Of Wasted Buns: " + wastedBuns);
		System.out.println("Number Of Wasted Patty: " + wastedPatty);
		System.out.println("Number Of Wasted Tomato: " + wastedTomato);
		System.out.println("Number Of Wasted Lettuce: " + wastedLettuce);
		System.out.println("Number Of Wasted Onion: " + wastedOnion);
		System.out.println("Number Of Wasted Cheese: " + wastedCheese);
	}
	
	public void printNumOfItemsOrdered()
	{
		System.out.println("Number Of Item One Ordered: " + countItemOne);
		System.out.println("Number Of Item Two Ordered: " + countItemTwo);
		System.out.println("Number Of Item Three Ordered: " + countItemThree);
		System.out.println("Number Of Item Four Ordered: " + countItemFour);
		System.out.println("Number Of Item Five Ordered: " + countItemFive);
		System.out.println("Number Of Item Six Ordered: " + countItemSix);
	}
	
	public void printRecords()
	{
		Iterator<Integer> customerIterator = getCustomerIterator();
		Iterator<Integer> orderIterator = getOrderIterator();
		while(customerIterator.hasNext())
		{
			System.out.println("Customer #" + customerIterator.next() + "->" + " Item #" + orderIterator.next());
		}
	}
	
	public boolean addCustomer(int orderNumber)
	{
		if(queue.isFull())
		{
			return false;
		}
		else
		{
			queue.enqueue(orderNumber);
			return true;
		}
		
	}
	
	public void removeExpired()
	{
		LinkedStack<Integer> tempBuns = new LinkedStack<Integer>();		//stack to hold the current item being check
		LinkedStack<Integer> tempPatty = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempLettuce = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempTomato = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempOnion = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempCheese = new LinkedStack<Integer>();	//stack to hold the current item being check
		int tempItem;
		//loop to check if buns are expired
		while(!buns.isEmpty())
		{
			tempItem = buns.pop();
			if(tempItem + 5 != date)
			{
				tempBuns.push(tempItem);
			}
			else
			{
				wastedBuns++;
			}
		}
		
		//loop to check if patties are expired
		while(!patty.isEmpty())
		{
			tempItem = patty.pop();
			if(tempItem + 4 != date)
			{
				tempPatty.push(tempItem);
			}
			else
			{
				wastedPatty++;
			}
		}
		
		//loop to check if the lettuce are expired
		while(!lettuce.isEmpty())
		{
			tempItem = lettuce.pop();
			if(tempItem + 3 != date)
			{
				tempLettuce.push(tempItem);
			}
			else
			{
				wastedLettuce++;
			}
		}
		
		//loop to check if tomatoes are expired
		while(!tomato.isEmpty())
		{
			tempItem = tomato.pop();
			if(tempItem + 3 != date)
			{
				tempTomato.push(tempItem);
			}
			else
			{
				wastedTomato++;
			}
		}
		
		//loop to check if onions are expired
		while(!onion.isEmpty())
		{
			tempItem = onion.pop();
			if(tempItem + 5 != date)
			{
				tempOnion.push(tempItem);
			}
			else
			{
				wastedOnion++;
			}
		}
		
		//loop to check if cheese is expired
		while(!cheese.isEmpty())
		{
			tempItem = cheese.pop();
			if(tempItem + 3 != date)
			{
				tempCheese.push(tempItem);
			}
			else
			{
				wastedCheese++;
			}
		}
		//put the items back on the original stack with the oldest items on top
		while(!tempBuns.isEmpty())
		{
			buns.push(tempBuns.pop());
		}
		while(!tempPatty.isEmpty())
		{
			patty.push(tempPatty.pop());
		}
		while(!tempLettuce.isEmpty())
		{
			lettuce.push(tempLettuce.pop());
		}
		while(!tempOnion.isEmpty())
		{
			onion.push(tempOnion.pop());
		}
		while(!tempTomato.isEmpty())
		{
			tomato.push(tempTomato.pop());
		}
		while(!tempCheese.isEmpty())
		{
			cheese.push(tempCheese.pop());
		}
	}
	
	public void newShipment(int bunsAmt, int pattyAmt, int lettuceAmt, int tomatoAmt, int onionAmt, int cheeseAmt)
	{
		LinkedStack<Integer> tempBuns = new LinkedStack<Integer>();		//stack to hold the current item being check
		LinkedStack<Integer> tempPatty = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempLettuce = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempTomato = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempOnion = new LinkedStack<Integer>();	//stack to hold the current item being check
		LinkedStack<Integer> tempCheese = new LinkedStack<Integer>();	//stack to hold the current item being check
		//loop to remove items from the top to add new items to the bottom
		while(!buns.isEmpty())
		{
			tempBuns.push(buns.pop());
		}
		while(!patty.isEmpty())
		{
			tempPatty.push(patty.pop());
		}
		while(!lettuce.isEmpty())
		{
			tempLettuce.push(lettuce.pop());	
		}
		while(!tomato.isEmpty())
		{
			tempTomato.push(tomato.pop());
		}
		while(!onion.isEmpty())
		{
			tempOnion.push(onion.pop());
		}
		while(!cheese.isEmpty())
		{
			tempCheese.push(cheese.pop());
		}
		
		//add new items to the bottom
		while(bunsAmt != 0)
		{
			buns.push(date);
			bunsAmt--;
		}
		while(pattyAmt != 0)
		{
			patty.push(date);
			pattyAmt--;
		}
		while(lettuceAmt != 0)
		{
			lettuce.push(date);
			lettuceAmt--;
		}
		while(tomatoAmt != 0)
		{
			tomato.push(date);
			tomatoAmt--;
		}
		while(onionAmt != 0)
		{
			onion.push(date);
			onionAmt--;
		}
		while(cheeseAmt != 0)
		{
			cheese.push(date);
			cheeseAmt--;
		}
		
		//put the items back on the original stack with the oldest items on top
		while(!tempBuns.isEmpty())
		{
			buns.push(tempBuns.pop());
		}
		while(!tempPatty.isEmpty())
		{
			patty.push(tempPatty.pop());
		}
		while(!tempLettuce.isEmpty())
		{
			lettuce.push(tempLettuce.pop());
		}
		while(!tempOnion.isEmpty())
		{
			onion.push(tempOnion.pop());
		}
		while(!tempTomato.isEmpty())
		{
			tomato.push(tempTomato.pop());
		}
		while(!tempCheese.isEmpty())
		{
			cheese.push(tempCheese.pop());
		}
	}
	
	public boolean Order()
	{
		int tempLettuce = 0;
		int orderNumber = queue.dequeue();
		//checks if there is still buns and patty in stock
		if(buns.isEmpty() || patty.isEmpty())
		{
			if(orderNumber != 3)
			{
				return false;
			}
		}
		//checks if there is still lettuce in stock
		if(lettuce.isEmpty())
		{
			return false;
		}
		//checks if there is still tomato in stock
		if(tomato.isEmpty())
		{
			if(orderNumber != 6)
			{
				return false;
			}
		}
		//checks if there is still onions in stock
		if(onion.isEmpty())
		{
			if(orderNumber != 4 || orderNumber != 5)
			{
				return false;
			}
		}
		//checks if there is still cheese in stock
		if(cheese.isEmpty())
		{
			if(orderNumber != 2 || orderNumber != 5)
			{
				return false;
			}
		}
		if(orderNumber == 1)
		{
			buns.pop();
			patty.pop();
			lettuce.pop();
			tomato.pop();
			onion.pop();
			countItemOne++;
		}
		else if (orderNumber == 2)
		{
			cheese.pop();
			buns.pop();
			patty.pop();
			lettuce.pop();
			tomato.pop();
			onion.pop();
			countItemTwo++;
		}
		else if(orderNumber == 3)
		{
			tempLettuce = lettuce.pop();
			if(lettuce.isEmpty())
			{
				lettuce.push(tempLettuce);
				return false;
			}
			lettuce.pop();
			tomato.pop();
			onion.pop();
			countItemThree++;
		}
		else if(orderNumber == 4)
		{
			buns.pop();
			patty.pop();
			lettuce.pop();
			tomato.pop();
			countItemFour++;
		}
		else if(orderNumber == 5)
		{
			cheese.pop();
			buns.pop();
			patty.pop();
			lettuce.pop();
			tomato.pop();
			countItemFive++;
		}
		else if(orderNumber == 6)
		{
			buns.pop();
			patty.pop();
			lettuce.pop();
			onion.pop();
			countItemSix++;
		}
		records.add(customerNumber, orderNumber);
		customerNumber++;
		return true;
	}
	//getters for food wasted
	public int getWastedBuns()
	{
		return wastedBuns;
	}
	public int getWastedPatty()
	{
		return wastedPatty;
	}
	public int getWastedLettuce()
	{
		return wastedLettuce;
	}
	public int getWastedTomato()
	{
		return wastedTomato;
	}
	public int getWastedOnion()
	{
		return wastedOnion;
	}
	public int getWastedCheese()
	{
		return wastedCheese;
	}
	//clears all records of wasted food
	public void clearWastedFood()
	{
		wastedBuns = wastedPatty = wastedLettuce = wastedTomato = wastedOnion = wastedCheese = 0;
	}
	//getters for counter for each item
	public int getCountItemOne()
	{
		return countItemOne;
	}
	public int getCountItemTwo()
	{
		return countItemTwo;
	}
	public int getCountItemThree()
	{
		return countItemThree;
	}
	public int getCountItemFour()
	{
		return countItemFour;
	}
	public int getCountItemFive()
	{
		return countItemFive;
	}
	public int getCountItemSix()
	{
		return countItemSix;
	}
	//clears records of all items
	public void clearItems()
	{
		countItemOne = countItemTwo = countItemThree = countItemFour = countItemFive = countItemSix = 0;
	}
	public Iterator<Integer> getCustomerIterator()
	{
		Iterator<Integer> customerIterator = records.getKeyIterator();
		return customerIterator;
	}
	public Iterator<Integer> getOrderIterator()
	{
		Iterator<Integer> orderIterator = records.getValueIterator();
		return orderIterator;
	}
	//clears records of purchase history
	public void clearRecords()
	{
		records.clear();
		customerNumber = 0;
	}
	//getters and setters for date
	public int getDate()
	{
		return date;
	}
	public void setDate(int newDate)
	{
		date = newDate;
	}
	//getters and setters for customer lost count
	public int getCustomersLost()
	{
		return customersLost;
	}
	public void setCustomersLost(int newCustomersLost)
	{
		customersLost = newCustomersLost;
	}
}
