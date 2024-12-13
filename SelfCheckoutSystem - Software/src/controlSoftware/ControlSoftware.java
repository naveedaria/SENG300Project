package controlSoftware;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import attendant.AttendantProfile;
import attendant.AttendantProfileDatabase;
import attendant.StationControl;

public class ControlSoftware {
	public static BigDecimal paymentTotal = BigDecimal.ZERO;
	public BigDecimal change;
	private boolean coinProcessed = false;
	private boolean billProcessed = false;
	private int numProducts = 0;
	private ArrayList<Barcode> productBarcodes = new ArrayList<Barcode>();
	
	public Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;
	public Lookup lookup;
	public ElectronicScaleListenerStub electronicScaleStub;
	public StationControl stationControl;
	public double expectedWeightOnScale;
	public static boolean scanned = false;
	
	public ShoppingCart shoppingCart;
	
	
	
	/**
	 * Constructor that initializes the SelfCheckout Station hardware
	 * 
	 * @param currency
	 * 		  The currency type accepted
	 * @param banknoteDenominations
	 * 		  The denominations of bank notes accepted
	 * @param coinDenominations
	 * 		  The denominations of coins accepted
	 * @param scaleMaxWeight
	 * 		  The maximum weight permitted in the bagging area
	 * @param scaleSensitivity
	 * 		  The sensitivity of the scale. 
	 */
	public ControlSoftware(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations, int scaleMaxWeight, int scaleSensitivity) {
		this.currency = currency;
		this.banknoteDenominations = banknoteDenominations;
		this.coinDenominations = coinDenominations;
		this.scaleMaxWeight = scaleMaxWeight;
		this.scaleSensitivity = scaleSensitivity; 
		
		this.selfCheckout = new SelfCheckoutStation(this.currency, this.banknoteDenominations, this.coinDenominations, this.scaleMaxWeight, this.scaleSensitivity);
		
		this.stationControl = new StationControl();
		AttendantProfileDatabase users = new AttendantProfileDatabase();
		users.addProfile(new AttendantProfile("clerk", "seng2021"));
		stationControl.loadAttendantProfileDatabase(users);
		// Create shopping cart object
		shoppingCart = new ShoppingCart();
		lookup = new Lookup();
		
		
		
		// We already have a mainScanner as part of self-checkout
		//scannerObject = new BarcodeScanner();
		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		//scannerObject.register(stub);	
		//scannerObject.enable();
		
		selfCheckout.mainScanner.register(stub);
		selfCheckout.mainScanner.enable();
				
		
		this.electronicScaleStub = new ElectronicScaleListenerStub();
		
		selfCheckout.baggingArea.register(this.electronicScaleStub);
		
	}
	
	
	/**
	 * 
	 * @param barcodedItem
	 * 		  object of type BarcodedItem representing the physical item being scanned
	 * @param quantity
	 * 		  quantity of item being scanned
	 */
	public void scanProduct(Barcode barcode,int quantity) {
		
		BarcodedItem barcodedItem = BarcodedItemDatabase.BARCODED_ITEM_DATABASE.get(barcode);
		
		selfCheckout.mainScanner.scan(barcodedItem);
		
		expectedWeightOnScale = expectedWeightOnScale + (quantity * barcodedItem.getWeight()); //expected weight of scale incremented
		
		// For Iteration #3
		//if(!shoppingCart.doesItemNeedToBeWeighed(barcodedItem)) {
			// Pass execution flow back to user, and prompt to put item on scale (in main/driver/GUI)
			// This can be done by setting a return to True (changing return type to Boolean)
		//}else {
			shoppingCart.addToShoppingCart(barcodedItem, quantity);
		//}
		this.paymentTotal = shoppingCart.totalPayment;
		
		ControlSoftware.scanned = true;
	}
	
	/**
	 * 
	 * @param pluCodedItem
	 * 		  object of type PLUCodedItem representing the physical item with the PLU Code
	 * @param quantity
	 * 		  quantity of item being scanned
	 */
	public void scanProductUsingPLUCode(PLUCodedItem pluCodedItem, int quantity) {
		
		// From GUI, get customer/attendee to enter PLUCode. Make a PLUCodedItem with the weight then call this function to add it to shopping cart
		
		// Note: No need to check if isPerUnit is true or false, because PLU items are always per kilogram
		shoppingCart.addToShoppingCart(pluCodedItem, quantity);
	}
	
	
	

	
	/**
	 * 
	 * @param barcodedItem
	 * 		  object of type BarcodedItem representing the physical item being weighed
	 */
		public double weighItem(BarcodedItem barcodedItem) {
			//selfCheckout.scale.add(barcodedItem);
			try {
				
				// This will throw an exception if item weight exceeds MAX scale weight
				selfCheckout.scale.add(barcodedItem);
				return selfCheckout.scale.getCurrentWeight();
				
			}catch(OverloadException e) {
				return 0;
			}
		}
		
		
		/**
		 * 
		 * @param barcodedItem
		 * 		  object of type BarcodedItem representing the physical item being removed from scale
		 */
		public boolean removeItemFromScale(BarcodedItem barcodedItem){
			
			try {
				// Remove item
				selfCheckout.scale.remove(barcodedItem);
				
				// If removal succeeds, add it to the shopping cart (uses overloaded method in ShoppingCart)
				shoppingCart.addToShoppingCart(barcodedItem, 1);
				
				return true;
				
			}catch (SimulationException e) {
				
				return false;
			}
		
		}

	
	
	
	public boolean addToScalePrompt(Item item) {
		System.out.println("Please add item to the bagging area \n");
		
		boolean addedToScale = false;
		
		while(addedToScale == false) {
			
			selfCheckout.mainScanner.disable();
			
			@SuppressWarnings("resource")
			Scanner scaleUserInput = new Scanner(System.in);
			
			System.out.println("Press 1 if you will not bag, 0 if you will \n");
			
			int userInput = scaleUserInput.nextInt();
			
			if (userInput == 0) {
				
				addedToScale = true;
				selfCheckout.mainScanner.enable();

								
			} else continue;
			
			// Disable scanner until input (GUI this would a button)
			// If weightChanged() returns something then we close out of the loop
			
		} return true;
		
	}
	
	// Use Case: Customer Has their Own Bag
	
	public BarcodedItem customerBag() {
		
		Barcode bag = new Barcode("1234");
		BarcodedItem customerBag = new BarcodedItem(bag, 5.00);
		
		return customerBag;
	}
	
	public boolean doneAddingItems(boolean ooo) {
		return ooo;
	}
	
	public boolean returnToAddingItems(boolean ooo) {
		return ooo;
	}
	
	public void addToBaggingArea(Barcode b) throws Exception {
		BarcodedItem item = BarcodedItemDatabase.BARCODED_ITEM_DATABASE.get(b);
		
		// Checking for null item
		if(item == null) throw new SimulationException("Null item.");
	
		
		// Do not do anything if bagging area is disabled
		if(selfCheckout.baggingArea.isDisabled()) return;
		
		// get the weight from the scale
		double weight = electronicScaleStub.getCurrentWeight();
		
		// Add item to the scale
		selfCheckout.baggingArea.add(item);
		ControlSoftware.scanned = false;
		// Get the total weight of the new item added 
		double newWeight = electronicScaleStub.getCurrentWeight();
		System.out.println(newWeight);
		// If the new weight is greater the maximum that the scale can measure
		if(newWeight > electronicScaleStub.maximumWeightInGrams) {
			// Set the overload flag
			electronicScaleStub.setOverload();
			// Throw Exception
			throw new Exception("Scale OverLoaded.");
		}
		// If flag in not set
		else if (!electronicScaleStub.isOverload) {
			// Checking if the new weight is the same as the item weight
			newWeight = newWeight - weight;
			//System.out.println("New Weight" + newWeight+ " Weight: " + weight);
			// New weight should not greater or less than the item weight 
			if(newWeight != item.getWeight()) {
				// Print statement for the non successful attempt
				//System.out.println("Weight has changed, item was not successfully added to bagging area.");
				// Call GUI to prompt the attendant 
				// Attendant should enter their number and by-pass this prompt 
			}
			else {
				// Print statement for the successful attempt
				//System.out.println("Weight has not changed, item was successfully added to bagging area.");
				
				// Successful prompt or do nothing
			}
		}
		
	}
	
	public void removeFromBaggingArea(BarcodedItem item) {
		
		// Do not do anything if bagging area is disabled
		if(selfCheckout.baggingArea.isDisabled()) return;
		
		// get the weight from the scale
		double weight = electronicScaleStub.getCurrentWeight();
		
		// Checking for null item
		if(item == null) throw new SimulationException("Null item.");
		// If the scale is not overloaded 
		if(!electronicScaleStub.isOverload) {

			// Check if the weight are not less zero
			if(weight - item.getWeight() < 0) {
				throw new SimulationException("Item was not properly removed");
				// Call GUI to prompt the attendant for Item not being removed properly
				// Would probably have to remove all exception handling, and make it into GUI prompts
			}
			// If overloaded just remove the item
			else {
				// Remove Item
				selfCheckout.baggingArea.remove(item);
			}
			
		}
		
	}
	


	
	/**
	 * Method to check whether a coin is accepted (correct denomination and currency)
	 * 
	 * @param selfCheckout
	 *  	  SelfCheckout station input
	 * @param currency
	 * 		  The type of currency accepted
	 * @param coinDenominations
	 * 		  The denomination of coins accepted 
	 * @param someCoin
	 * 		  The coin checked and accepted for payment
	 * @return
	 * 		  Return coin value 
	 * @throws DisabledException
	 * 		   If the coin machine is filled
	 */
	public BigDecimal coinMethod(Coin someCoin) throws DisabledException {

		try {
			if (someCoin==null) {
				throw new SimulationException("Null coin entered.");
			}
			boolean validCoinVal = checkCoinVal(someCoin);
			if (validCoinVal) {
				// Aris comment: put in constructor
				CoinPaymentStub coinStub = new CoinPaymentStub();
				this.selfCheckout.coinSlot.register(coinStub);
				this.selfCheckout.coinSlot.accept(someCoin); 
				return someCoin.getValue();
			}else {
				throw new IllegalArgumentException("Invalid coin value");
			}
		}catch(SimulationException e) {
			throw new SimulationException("Invalid coin entered.");
		}catch (DisabledException e) {
			throw new DisabledException();
		}catch(IllegalArgumentException e) {
			System.out.println("Value of coin was invalid.");
			throw new IllegalArgumentException("Invalid coin value"); 
		}
	}
	
	/**
	 * Method returns the value of the coin
	 * 
	 * @param someCoin
	 * 		  A type of coin
	 * @param coinDenominations
	 * 		  The denomination of coins accepted
	 * @return
	 */
	private boolean checkCoinVal(Coin someCoin) {

		for (int i = 0; i<coinDenominations.length; i++) {
			if (this.coinDenominations[i].compareTo(someCoin.getValue())==0) {
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Method to verify the bank note is accepted and value
	 * 
	 * @param selfCheckout
	 *  	  SelfCheckout station input
	 * @param currency
	 * 		  The type of currency accepted
	 * @param banknoteDenominations
	 * 		  The denomination of bank notes accepted 
	 * @param someBanknote
	 * 		  The bank note checked and accepted for payment
	 * @return
	 * @throws OverloadException
	 * 		   If the coin machine is filled
	 * @throws DisabledException
	 * 		   If the machien is disabled
	 */
	public int banknoteMethod(Banknote someBanknote) throws OverloadException, DisabledException {

		try {
			if (someBanknote==null) {
				throw new SimulationException("Null banknote entered.");
			}
			boolean validCoinVal = checkBanknoteVal(someBanknote, this.banknoteDenominations);
			if (validCoinVal) {
				banknotePaymentStub billStub = new banknotePaymentStub();
				this.selfCheckout.banknoteInput.register(billStub);
				//selfCheckout.banknoteInput.enable();
				this.selfCheckout.banknoteInput.accept(someBanknote);
				return someBanknote.getValue();
			}else {
				throw new IllegalArgumentException("Invalid banknote value");
			}
		}catch(OverloadException e) {
			throw new OverloadException("A banknote is dangling from the slot. Remove that before adding another.");
		}catch(SimulationException e) {
			throw new SimulationException("Invalid banknote entered.");
		}catch (DisabledException e) {
			throw new DisabledException();
		}catch(IllegalArgumentException e) {
			System.out.println("Value of banknote was invalid.");
			throw new IllegalArgumentException("Invalid banknote value");
		}
	}
	
	/**
	 * Method returns the value of the bank note
	 * 
	 * @param someBankote
	 * 		  A type of bank note
	 * @param banknoteDenominations
	 * 		  The denomination of bank notes accepted
	 * @return
	 */
	private static boolean checkBanknoteVal(Banknote someBanknote, int[] banknoteDenominations) {

		for (int i = 0; i<banknoteDenominations.length; i++) {
			if (banknoteDenominations[i] == someBanknote.getValue()) {
				return true; 
			}
		}
		return false; 
	}
	
	
	/**
	 * Setter for the Customers Change
	 */
	public void setChange(double change) {

		this.change = new BigDecimal(change).setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Method to calculate payment by coin
	 * @param coinValue
	 * 		  The value of coin used to pay 
	 */
	public void calculateCoinPayment(BigDecimal coinValue) {

		if (coinProcessed==false) {
			BigDecimal balance = this.paymentTotal;
			this.change = balance.subtract(coinValue);
			this.coinProcessed = true; 
		}else if (coinProcessed==true) {
			this.change = this.change.subtract(coinValue);
		}
	}
	
	/**
	 * Method for calculating payment by bill
	 * @param banknoteValue
	 * 		  The value of bank note used
	 * @param billProcessed
	 * 		  Checks whether the bill is acceptable
	 */
	public void calculateBillPayment(int banknoteValue) {
		BigDecimal bankNoteVal = new BigDecimal(banknoteValue);
		if (billProcessed==false) {
			BigDecimal balance = this.paymentTotal; 
			this.change = balance.subtract(bankNoteVal);
			billProcessed = true; 
		}else {
			this.change = this.change.subtract(bankNoteVal);
		}
	}
	
	/**
	 * 
	 * @param 
	 */
	public String useMembershipCard(String cardNumber, String cardHolder) throws IOException {
		ScanMembershipCard membershipCardReader = new ScanMembershipCard(this.selfCheckout);
		String validatedCardNumber = membershipCardReader.tapMembershipCard(cardNumber, cardHolder);
		return validatedCardNumber;
	} 
	
	public String getMemberName(String cardNumber, String cardHolder) {
		ScanMembershipCard membershipCardReader = new ScanMembershipCard(this.selfCheckout);
		membershipCardReader.detectCard(cardNumber, cardHolder);
		return membershipCardReader.getMemberName(cardNumber);
	}
	
	/**
	 * 
	 * @param 
	 */
	public void finishedAddingItems() throws IOException, DisabledException, OverloadException {
		
		if(expectedWeightOnScale != selfCheckout.baggingArea.getCurrentWeight()) { //if scanned weight does not equal actual weight, then can't finish 
			
			System.out.println("Please place all items on bagging area then Try Again");
			return;
		}
		//proceed to pay by cash, tap, swipe, giftcard etc. 
	}
	
	/**
	 * 
	 * @param 
	 */
	//returns the updated paymentBalance and sets the class variable paymentTotal to this new value 
	//-1 means error encountered, or the giftcard is not valid 
	public BigDecimal useGiftCard(String giftcardNumber) throws IOException {
		PaymentByGiftcard giftcardPaymentHandler = new PaymentByGiftcard(this.selfCheckout);
		giftcardPaymentHandler.detectCard(giftcardNumber, true);
		BigDecimal amountRemaining = giftcardPaymentHandler.tapToRedeem(giftcardNumber, this.shoppingCart.getTotalPayment(), true);
		
		//if the amount returned is not -1 
		if(amountRemaining.compareTo(new BigDecimal (-1))!=0) {
			paymentTotal = amountRemaining;
			shoppingCart.totalPayment = amountRemaining;
		}
		
		System.out.println("new payment total: " + paymentTotal);
		
		return amountRemaining; 
	}
	
	public BigDecimal getAmountOnGiftCard(String giftcardNumber) {
		PaymentByGiftcard giftcardPaymentHandler = new PaymentByGiftcard(this.selfCheckout);
		giftcardPaymentHandler.detectCard(giftcardNumber, true);
		return giftcardPaymentHandler.getAmount(giftcardNumber);
	}
	
	/**
	 * 
	 * @param 
	 */
	//Assume user enters more than necessary cash, handle case of less cash than balance through GUI in Iteration 3
	public void cashToPay(Coin[] coins, Banknote[] banknotes) throws DisabledException, OverloadException {
		for (int i=0; i<coins.length; i++) {
			BigDecimal coinVal = coinMethod(coins[i]);
			calculateCoinPayment(coinVal);
		}
		
		for (int i=0; i<banknotes.length; i++) {
			int billVal = banknoteMethod(banknotes[i]);
			calculateBillPayment(billVal);
		}
		
		DispenseChange changeDispenser = new DispenseChange(this.selfCheckout, this.change);
		this.change = changeDispenser.calculateChangeDenominations();
		//change is now 0, proceed to receipt 
	
	}
	
	/**
	 * 
	 * @param 
	 */
	public void tapToPay(String cardCompany, String type, String number, String cardholder, String cvv, String pinInput, boolean isTapEnabled,
			boolean hasChip, Calendar expiry, BigDecimal cardLimit, boolean insertCard) throws ChipFailureException, IOException {
		PaymentByCard cardPaymentHandler = new PaymentByCard(this.selfCheckout, cardCompany);
		cardPaymentHandler.detectCard(type, number, cardholder, cvv, pinInput, isTapEnabled, hasChip, expiry, cardLimit);
		
		cardPaymentHandler.tapToPay(this.paymentTotal, insertCard, pinInput);
		this.change = new BigDecimal(0);
	}
	
	/**
	 * 
	 * @param 
	 * @throws IOException 
	 * @throws MagneticStripeFailureException 
	 */
	public void swipeToPay(String cardCompany, String type, String number, String cardholder, String cvv, String pinInput, boolean isTapEnabled,
			boolean hasChip, Calendar expiry, BigDecimal cardLimit, BufferedImage signature, boolean insertCard) throws MagneticStripeFailureException, IOException {
		PaymentByCard cardPaymentHandler = new PaymentByCard(this.selfCheckout, cardCompany);
		cardPaymentHandler.detectCard(type, number, cardholder, cvv, pinInput, isTapEnabled, hasChip, expiry, cardLimit);
		
		cardPaymentHandler.swipeToPay(signature, this.paymentTotal, insertCard, pinInput);
		this.change = new BigDecimal(0);
	}
	
	/**
	 * 
	 * @param 
	 */
	public void plasticBagsUsed(int quantity) {
		double amtToAdd = 0.05 * quantity;
		this.shoppingCart.updatePayment(BigDecimal.valueOf(amtToAdd));
		
	}
	
	
	
	
	
	
}
