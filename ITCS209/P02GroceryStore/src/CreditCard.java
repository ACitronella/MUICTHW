//Name:
//ID:
//Section:
package 
public class CreditCard{
	
	//**************************** DO NOT MODIFY **********************************//
	public static enum CardType{ VISA, AMERICANEXPRESS, JCB, MASTERCARD }; 
					// different types of credit card 
	
	public static final double CARDLIMIT = 5000.00;	
					// assume that for "each payment transaction" the maximum amount is 5,000
	
	private String number;		// card's number
	private CardType type;		// card's type
	//*****************************************************************************//
	
	/**
	 * Constructor initializes card number and card type
	 * @param number
	 * @param type
	 */
	public CreditCard(String number, CardType type) {
		//******************* YOUR CODE HERE ******************
		
		//*****************************************************
		
	}
	
	
	/**
	 * Verify the validity of the card information. Different card type has different format of card number as follow
	 * VISA -> the number must be 16 digits, and start with number 4
	 * AMERICANEXPRESS -> the number must be 15 digits, and start with either 34 or 37
	 * JCB -> the number must be 16 digits, and start with 3528 to 3589
	 * MASTERCARD -> the number must be 16 digits, and start with 51 or 52
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", this card is valid.
	 * If the card is JCB and has number "4234567890123456", this card is invalid.
	 * 
	 * @return True if the card is valid, otherwise return false
	 */
	public boolean isValid() {
		//******************* YOUR CODE HERE ******************
		
		return false;
		
		//*****************************************************
	}
	
	
	/**
	 * If the card is valid, formats the card's number according to the card's type.
	 * AMERICANEXPRESS (15 digits): #### ###### ##### (4-6-5)
	 * VISA, JCB, MASTERCARD (16 digits): #### #### #### #### (4-4-4-4)
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", 
	 * the string value "4234 5678 9012 3456" will be returned.
	 * 
	 * if the card is AMERICANEXPRESS and has number "343456789012345", 
	 * the string value "3434 567890 12345" will be returned.
	 * 
	 * If the card information is invalid, returns empty string.
	 * 
	 * @return a string of formatted card's number
	 */
	public String getFormattedCardNumber() {
		//******************* YOUR CODE HERE ******************
		
		return null;
		
		//*****************************************************
	}
	
	
	//**************************** DO NOT MODIFY **********************************//
	
	/**
	 * Get the value of card's type
	 * @return a String of card's type
	 */
	public String getType() {
		return type.name();
	}
	
	
	/**
	 * Get the raw card number before formatted
	 * @return a String of card's number
	 */
	public String getNumber() {
		return this.number;
	}
	
	//*****************************************************************************//

}
