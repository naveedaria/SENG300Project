package controlSoftware;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import org.lsmr.selfcheckout.BlockedCardException;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.InvalidPINException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.TapFailureException;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.CardIssuer;


public class PaymentByCard {
	private CardReader cardReader;
	private Card inputCard;
	private String pin;
	private CardIssuer cardIssuer;
	private HashMap<String, String > cardDatabase = new HashMap<>();
	
	/**
	 * Constructor 
	 * @param 
	 * 		     
	 */
	// NOTE: Register card reader in driver *****
	public PaymentByCard(SelfCheckoutStation selfCheckout, String cardCompany) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		this.cardIssuer = new CardIssuer(cardCompany);
		
		
		//registering first card
		String firstCardNum = "123456789";
		String firstCardPin = "1234";
		
		cardDatabase.put(firstCardNum, firstCardPin);
		
		
		//registering second card
		String secondCardNum = "987654321";
		String secondCardPin = "4321";
				
		cardDatabase.put(firstCardNum, firstCardPin);
	}
	
	/**
	 * Detecting the card by creating a Card object for use in payment process
	 * @param type 
	 * 		  The type of card (credit or debit).
	 * @param number 
	 * 		  The card identification number.
	 * 	@param cardholder 
	 * 		  The name of the cardholder.
	 * 	@param cvv 
	 * 		  The card verification value (CVV), a 3- or 4-digit value often on
	 *            the back of the card. This can be null.
	 * 	@param pin 
	 * 		  The personal identification number (PIN) for access to the card.
	 *            This can be null if the card has no chip.
	 *  @param isTapEnabled
	 *            Whether this card is capable of being tapped.
	 *  @param hasChip
	 *            Whether this card has a chip.
	 * 	@param expiry
	 * 		  The expiry date of card.
	 * 	@param cardLimit 
	 * 		  For a credit card, this represents the credit limit. For a debit
	 *            card, this is how much money is available.
	 * @throws InvalidPINException 
	 */
	public void detectCard(String type, String number, String cardholder, String cvv, String pin, boolean isTapEnabled,
			boolean hasChip, Calendar expiry, BigDecimal cardLimit) throws InvalidPINException {
		
		
		if(cardDatabase.get(number).equals(pin)) {
			try {
				this.inputCard = new Card(type, number, cardholder, cvv, pin, isTapEnabled, hasChip);
				this.pin = pin;
				this.cardIssuer.addCardData(number, cardholder, expiry, cvv, cardLimit);
			}catch(SimulationException e) {
				throw e;
			}
		}else {
			throw new InvalidPINException();
		}
	}
	
	/**
	 * Method to tap to make payment.
	 * @param amount
	 * 		  The total balance amount for payment.
	 * @param insertCard
	 * 		  True if user requested to insert the card (pin validation) - false otherwise
	 * @param pinInput
	 * 		  The PIN the user enters - can be null if insertCard==false
	 * @return 
	 * 		  True if payment successfully processed, false otherwise. 
	 */
	public boolean tapToPay(BigDecimal amount, boolean insertCard, String pinInput) throws ChipFailureException, IOException {
		
		
		
			boolean paymentSuccessfullyProcessed = true; 
			try {
				CardData data= this.cardReader.tap(this.inputCard);
				if (data==null) {
					paymentSuccessfullyProcessed = false; 
					System.out.println("Tap is not enabled.\n");
					return paymentSuccessfullyProcessed;
				}
				if (insertCard==true) {
					validateCard(pinInput);
				}
				paymentSuccessfullyProcessed = authorizeCardPayment(data, amount);
				return paymentSuccessfullyProcessed;
			}catch(ChipFailureException e) {
				throw e;
			}catch(BlockedCardException e) {
				throw e; 
			}catch(InvalidPINException e) {
				throw e;
			}

		//set change to 0 in ControlSoftware if true is returned
	}
	
	/**
	 * Method to swipe to make payment.
	 * @param signature
	 * 		  Image of signature required for swiping - typically for credit cards.
	 * @param amount
	 * 		  The total balance amount for payment.
	 * @param insertCard
	 * 		  True if user requested to insert the card (pin validation) - false otherwise
	 * @param pinInput
	 * 		  The PIN the user enters - can be null if insertCard==false
	 * @return  
	 * 		  True if payment successfully processed, false otherwise. 
	 */
	public boolean swipeToPay(BufferedImage signature, BigDecimal amount, boolean insertCard, String pinInput) throws MagneticStripeFailureException, IOException{
		boolean paymentSuccessfullyProcessed = true; 
		
		
		
			try {
				CardData data= this.cardReader.swipe(this.inputCard, signature);
				
				if (insertCard==true) {
					validateCard(pinInput);
				}
				paymentSuccessfullyProcessed = authorizeCardPayment(data, amount);
				return paymentSuccessfullyProcessed;
			}catch(InvalidPINException e) {
				throw e;
			}catch(IOException e) {
				throw new MagneticStripeFailureException();
			}


			//set change to 0 in ControlSoftware if true is returned
	}
	
	/**
	 * Method to validate card by using pin verification if the card has a chip.
	 */
	private void validateCard(String pinInput) throws IOException {
		try {
			CardData data= this.cardReader.insert(this.inputCard, pinInput);
			this.cardReader.remove();
		}
		//Will be incorporated once GUI is created
		/*catch(SimulationException e) {
			throw e;
		}*/
		catch(ChipFailureException e) {
			System.out.println("Card does not have a chip or simulation probability exceeded.\n");
			throw e;
		}catch(InvalidPINException e) {
			System.out.println("Incorrect PIN entered.\n");
			throw e;
		}
	}
	
	/**
	 * Method to place the amount hold for company financial records and post the transaction.
	 * @param data
	 * 		  The card data for the card recently swiped or tapped. 
	 * @param actualAmount
	 * 		  The amount for the total balance payment.
	 * @return 
	 * 		  True if payment was successfully processed and posted for records - false otherwise. 
	 * @throws BlockedCardException 
	 */
	private boolean authorizeCardPayment(CardData data, BigDecimal actualAmount) throws BlockedCardException {
		try {
			int holdNumber = cardIssuer.authorizeHold(data.getNumber(), actualAmount);
			if (holdNumber == -1) {	
				System.out.println("Payment failed - card has insufficient funds, is blocked, or does not exist.\n");
				throw new BlockedCardException();
			}
			else {
				boolean successfulPayment = cardIssuer.postTransaction(data.getNumber(), holdNumber, actualAmount);
				return successfulPayment;
			}
		}catch(BlockedCardException e) {
			throw e;
		}
	}
	

	
}
