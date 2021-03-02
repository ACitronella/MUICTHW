//Name: Phuriwat Angkoondittaphong
//ID: 6388003
//Section: 1

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Customer {
	
	//*********************** DO NOT MODIFY ****************************//
	public static enum CustomerType{DEFAULT, STUDENT, PROFESSOR, ATHLETE, ICTSTUDENT};	//Different types of customers 
	private static int customerRunningNumber = 1;	//static variable for assigning a unique ID to a customer
	private CanteenICT canteen = null;	//reference to the CanteenICT object
	private int customerID = -1;		//this customer's ID
	protected CustomerType customerType = CustomerType.DEFAULT;	//the type of this customer, initialized with a DEFAULT customer.
	protected List<FoodStall.Menu> requiredDishes = new ArrayList<FoodStall.Menu> ();	//List of required dishes
	//*****************************************************************//

	private static List<FoodStall.Menu> defaultDishes = Arrays.asList(FoodStall.Menu.values());
	private static List<FoodStall.Menu> studentDishes = Arrays.asList(new FoodStall.Menu[]{FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT});
	private static List<FoodStall.Menu> professorDishes = Arrays.asList(new FoodStall.Menu[]{FoodStall.Menu.NOODLES, FoodStall.Menu.BEVERAGE});
	private static List<FoodStall.Menu> athleteDishes = Arrays.asList(new FoodStall.Menu[]{FoodStall.Menu.MEAT, FoodStall.Menu.MEAT, FoodStall.Menu.MEAT, FoodStall.Menu.SALAD, FoodStall.Menu.BEVERAGE});

	private boolean isAlreadyOrder = false; 
	private FoodStall stallQueuing = null;
	private int expectToLeaveFromTable = -1;
	private Table onThisTable = null;
	
	Customer(CanteenICT _canteen)
	{
		//******************* YOUR CODE HERE **********************
		this(_canteen, 'D');
		//*****************************************************
	}


	public Customer(CanteenICT _canteen, char role){
		this.canteen = _canteen; // is this dependency injection? sure it is.
		this.customerID = customerRunningNumber; 
		customerRunningNumber++;

		switch(role)
		{	
			case 'D': 
				this.customerType = Customer.CustomerType.DEFAULT;
				this.requiredDishes = defaultDishes;
				break;
			case 'S': 
				this.customerType = Customer.CustomerType.STUDENT;
				this.requiredDishes = studentDishes;
				break;
			case 'P': 
				this.customerType = Customer.CustomerType.PROFESSOR; 
				this.requiredDishes = professorDishes;
				break;
			case 'A': 
				this.customerType = Customer.CustomerType.ATHLETE; 
				this.requiredDishes = athleteDishes;
				break;
			case 'I': 
				this.customerType = Customer.CustomerType.ICTSTUDENT; 
				this.requiredDishes = studentDishes;
				break;
			//******************************************************************************************//
		}
		
	}
	
	
	public void takeAction()
	{
		//************************** YOUR CODE HERE **********************//
		if(
			!this.canteen.isAlreadyShiftEnterQueue()  // we can find a foodstall for a customer per a timestep only
			&& this.canteen.isTopOfWaitEnterQueue(this) // ensure this instance is always at the top of enterQueue list.
		){ 
			
			// this part consider customer who at top of waiting queue and not update waiting queue yet
			this.actionForWaitEnterQueue();
		}
		else if(
			this.stallQueuing != null 
			&& this.stallQueuing.isTopOfMakingFoodQueue(this) // ensure this instance is always at the top of customerQueue list.
			&& !this.isAlreadyOrder
			&& !this.stallQueuing.isActionComplete()
		){
			
			// section for unordered customers
			// ordering
			this.stallQueuing.takeOrder(this.requiredDishes);
			this.isAlreadyOrder = true;
			this.stallQueuing.setActionComplete(true);
		}
		else if(
			this.stallQueuing != null 
			&& this.stallQueuing.isTopOfMakingFoodQueue(this)
			&& this.stallQueuing.isReadyToServe()
			// && this.isAlreadyOrder // unnessesary check
		){
			
			// remove from waiting food queue
			this.stallQueuing.serve();

			// add to queue table
			this.stallQueuing.popTopOfCustomerQueue();
			this.stallQueuing.setActionComplete(true);
			this.stallQueuing = null;
			this.canteen.enQueueForSeat(this);
			if(this instanceof ICTStudent){
				this.expectToLeaveFromTable = this.getTimeToLeave(); 
			}
		}
		else if(
			this instanceof ICTStudent // only instance of ICTStudent
			&& this.canteen.getWaitToEnterQueue().contains(this) // in waitingtable queue
			&& this.expectToLeaveFromTable == this.canteen.getCurrentTime() // time to leave
		){

			this.canteen.popTopOfWaitSeatQueue(this);
			this.canteen.addToDoneQueue(this);
		}
		else if(
			this.canteen.isTopOfWaitSeatQueue(this) // ensure this instance is always at the top of seatQueue list.
			&& !this.canteen.isAlreadyShiftTableQueue() // we can find a seat for a customer per a timestep only
			&& this.canteen.getAreSeatsReady() 
		){
			
			// get table
			this.canteen.setIsAlreadyShiftTableQueue(true); 
			this.canteen.popTopOfWaitSeatQueue();
			this.onThisTable = this.canteen.findASeat(this);

			// set eating time
			this.expectToLeaveFromTable = this.getTimeToLeave(); 
			// plus one since this instance will start to eat next timestep, i dont think it is cheating though
		}
		else if(
			this.onThisTable != null // on table at any position
			&& this.expectToLeaveFromTable == this.canteen.getCurrentTime() // is it time to get out
		){
			this.onThisTable.popFromTable(this);
			this.onThisTable = null; // unnecessary
			this.canteen.addToDoneQueue(this);
		}
		
		//**************************************************************//
	}


	private int getTimeToLeave() {
		return this.canteen.getCurrentTime() + FoodStall.calculateEatingTime(this.requiredDishes) + 1;
	}
	
	// other class should not involve with these twos methods
	private void actionForWaitEnterQueue(){
		// everything involve with side effect
		List<FoodStall> foodStallsList = this.canteen.getFoodStalls();
		int minQueueIndex = this.findMinAndValidQueue(foodStallsList, this.requiredDishes);
		if(minQueueIndex != -1){
			Customer changeQueue = this.canteen.popTopOfWaitEnterQueue();
			FoodStall f = foodStallsList.get(minQueueIndex);
			f.enQueue(changeQueue);
			this.stallQueuing = f;
			this.canteen.setIsAlreadyShiftEnterQueue(true);
		}
	}

	private int findMinAndValidQueue(List<FoodStall> foodStallsList, List<FoodStall.Menu> requiredDishes){
		int minIndex = 0;
		boolean anyQueueAble = false;
		for(int i = 0; i < foodStallsList.size(); i++){
			FoodStall f = foodStallsList.get(i);
			if(
				f.getQueueAble()
				&& f.getMenu().containsAll(requiredDishes) 
				&& f.getCustomerQueue().size() <= foodStallsList.get(minIndex).getCustomerQueue().size()
			){
				minIndex = i;
				anyQueueAble = true;
			}
		}
		if(anyQueueAble){
			return minIndex;
		}
		return -1;
	}



	//***************For hashing, equality checking, and general purposes. DO NOT MODIFY **************************//	
	
	public CustomerType getCustomerType()
	{
		return this.customerType;
	}
	
	public int getCustomerID()
	{
		return this.customerID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerID != other.customerID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerType=" + customerType +"]";
	}

	public String getCode()
	{
		return this.customerType.toString().charAt(0)+""+this.customerID;
	}
	
	/**
	 * print something out if VERBOSE is true 
	 * @param str
	 */
	public void jot(String str)
	{
		if(CanteenICT.VERBOSE) System.out.println(str);
		
		if(CanteenICT.WRITELOG) CanteenICT.append(str, canteen.name+"_state.log");
	}
	
	//*************************************************************************************************//
	
}
