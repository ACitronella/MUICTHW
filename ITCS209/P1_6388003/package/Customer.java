//Name: Phuriwat Angkoondittaphong
//ID: 6388003
//Section: 1

import java.util.ArrayList;
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
	

	// will get this from FoodStall.COOKING_TIME and FoodStall.EAT_TIME combine
	private int waitForMakeFood = 0;
	private int waitForEating = 0;

	
	
	Customer(CanteenICT _canteen)
	{
		//******************* YOUR CODE HERE **********************
		this.canteen = _canteen; // love this dependency injection
		this.customerID = customerRunningNumber; 
		customerRunningNumber++;
		//*****************************************************
	}
	
	
	public void takeAction()
	{
		//************************** YOUR CODE HERE **********************//
		// does not have to use .equals, when this become true, both must point to the same object

		if(!this.canteen.isAlreadyShiftEnterQueue() && this == this.canteen.topOfWaitEnterQueue()){ 
			this.actionForWaitEnterQueue();
		}

		else if(this == this.canteen.topOfWaitSeatQueue()){
			
		}
		
		//**************************************************************//
	}

	public void actionForWaitEnterQueue(){
		// everything involve with side effect
		List<FoodStall> foodStallsList = this.canteen.getFoodStalls();
		int minIndex = findMinAndValidQueue(foodStallsList, this.requiredDishes);
		Customer changeQueue = this.canteen.popTopOfWaitEnterQueue();
		foodStallsList.get(minIndex).enQueue(changeQueue);
		this.canteen.setIsAlreadyShiftEnterQueue(true);
	}

	private static int findMinAndValidQueue(List<FoodStall> foodStallsList, List<FoodStall.Menu> requiredDishes){
		int minIndex = 0;
		for(int i = 0; i < foodStallsList.size(); i++){
			FoodStall f = foodStallsList.get(i);
			if(
				f.isQueueAble()
				&& f.getMenu().containsAll(requiredDishes) 
				&& f.getCustomerQueue().size() < foodStallsList.get(minIndex).getCustomerQueue().size()
			){
				minIndex = i;
			}
		}
		return minIndex;
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
