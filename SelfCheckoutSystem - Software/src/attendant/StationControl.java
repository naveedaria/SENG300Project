package attendant;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Scanner;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import controlSoftware.ControlSoftware;
import driver.GUIDriver;

public class StationControl extends AttendantLogIn_Out{
	
	private Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;
	double expectedWeight;
	int units;
	int quantity;
	

	private ReceiptPrinterListener receiptListener = new ReceiptPrinterListener () {

		private boolean enabled = false;
		private int units;
		private int quantity;
		
		public void setUnit(int unit) {
			this.units = unit;
		}
		
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			enabled   = true;
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			enabled   = false;
		}

		@Override
		public void outOfPaper(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// prompt the attendant panel
			// maybe block station
			
			System.out.println("Station is out of receipt paper.");
			
			addPaperToStation(units);
		}

		@Override
		public void outOfInk(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// prompt the attendant panel
			// maybe block station
			
			System.out.println("Station is out of receipt ink.");
			selfCheckout.printer.addInk(quantity);
		}

		@Override
		public void paperAdded(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// Reset the paperCounter
			// Enable the station
			
		}

		@Override
		public void inkAdded(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// Reset the InkVolume
			// Enable the station
		}
		
		
	};

	
	public void turnOnStation(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations, int scaleMaxWeight, int scaleSensitivity) {
		this.currency = currency;
		this.banknoteDenominations = banknoteDenominations;
		this.coinDenominations = coinDenominations;
		this.scaleMaxWeight = scaleMaxWeight;
		this.scaleSensitivity = scaleSensitivity; 
		
		this.selfCheckout = new SelfCheckoutStation(this.currency, this.banknoteDenominations, this.coinDenominations, this.scaleMaxWeight, this.scaleSensitivity);
	}
	
	public void turnOffStation(SelfCheckoutStation selfCheckout) {
		
		this.selfCheckout = null;
		
	}
	
	public void addPaperToStation(int units) {
		if (units < 0) {
			throw new IllegalArgumentException("Wrong units added");
		}
		GUIDriver.controlSoftware.selfCheckout.printer.addPaper(units);
	}
	
	public void addInkToStation(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Wrong units added");
		}
		
		GUIDriver.controlSoftware.selfCheckout.printer.addInk(quantity);
	}
	
	public void emptyCoinStorageUnit() {
		GUIDriver.controlSoftware.selfCheckout.coinStorage.unload();
	}
	
	public void emptyBanknoteStorageUnit() {
		GUIDriver.controlSoftware.selfCheckout.banknoteStorage.unload();
	}
	
	public void attendantRemoveItem(ControlSoftware cs, BarcodedItem item, int quantity) {
		cs.shoppingCart.removeFromShoppingCart(item, quantity);
	}
	
	public boolean attendantApproveWeight() throws OverloadException {
		//Attendant input
		AttendantLogIn_Out attendant = new AttendantLogIn_Out();


//		Scanner s = new Scanner(System.in); 
		//manually approve heavier bags by entering attendant ID and Password(default "12345678" and "12345678")
   		System.out.println("Enter Attendent ID"); 
   		String ID = "Bob";
   		System.out.println("Enter Attendent Password"); 
   		String passWord = "hunter2";
   		
   		AttendantProfile profile = new AttendantProfile(ID, passWord);
   		
   		
	//use logIn method to check if correct username/password is given
   		if ((attendant.logIn(ID, passWord)) == true){
   			//update expected weight if inputs are correct(manually approve weight discrepancy)
				expectedWeight = selfCheckout.scale.getCurrentWeight();
				return true;
			}	
   		else {
   			//Call this method again for attendant to enter username/password
   			System.out.println("Wrong password, try again");
   			return false;
   			}
		
   		}
	
	
	
}
