package driver;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;

import javax.swing.JFrame;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import attendant.BlockStation;
import controlSoftware.BarcodedItemDatabase;
import controlSoftware.ControlSoftware;
import controlSoftware.PLUCodedItemDatabase;
import panels.*;

public class GUIDriver {
	
	public static JFrame mainFrame;
	
	public static MainScreen m;
	public static PaymentSelectorScreen pss;
	
	public static CardPaymentScreen card;
	public static CashPaymentScreen cash;

	public static  GiftCardPaymentScreen giftcard;
	public static  LookupItemScreen lookup;
	public static LookupPLUScreen plulookup;
	
	public static  ThankYouForShoppingScreen thank;
	public static  AttendantMenuScreen attendant;
	public static ControlSoftware controlSoftware;
	public static WelcomeScreen welcome;
	public static MembershipScreen membership;
	public static CardSelectMethodScreen selectmethod;
	public static TapCardScreen tap;
	public static SwipeCardScreen swipe;
	public static ShutDownScreen shutdown;
	
	public static Barcode b1 = new Barcode("1111");
    public static BarcodedItem bItem = new BarcodedItem(b1, 20);
    public static Barcode b2 = new Barcode("2222");
    public static BarcodedItem bItem2 = new BarcodedItem(b2, 20);
    public static Barcode b3 = new Barcode("3333");
    public static BarcodedItem bItem3 = new BarcodedItem(b3, 20);
    public static Barcode b4 = new Barcode("4444");
    public static BarcodedItem bItem4 = new BarcodedItem(b4, 20);
    public static Barcode b5 = new Barcode("5555");
    public static BarcodedItem bItem5 = new BarcodedItem(b5, 20);
    
   
    public static PriceLookupCode plucode1 = new PriceLookupCode("4011"); 
    public static PLUCodedItem pluItem1 = new PLUCodedItem(plucode1,200);
    public static PriceLookupCode plucode2 = new PriceLookupCode("2021"); 
    public static PLUCodedItem pluItem2 = new PLUCodedItem(plucode2,500);
    public static PriceLookupCode plucode3 = new PriceLookupCode("5552"); 
    public static PLUCodedItem pluItem3 = new PLUCodedItem(plucode3,1000);
    
    public static BlockStation blockStation;
    public static boolean isBlocked = false;
    public static int x;
    public static int y;
	

	public static void main(String[] args) {
        System.out.println("Self-Checkout Station turning on...");
        System.out.println("Initializing Control Software v.1......");
       
        
        /*===============================================================
         *                INITIALIZE PRODUCT DATABASE
         *===============================================================*/
        
        Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
        Map<Barcode, BarcodedItem> barcodedItemDatabase = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;

        
       // Barcode b1 = new Barcode("1111");
      //  BarcodedItem bItem = new BarcodedItem(b1, 5);
        BarcodedProduct bp1 = new BarcodedProduct(b1, "Banana", new BigDecimal(1.00));
        
       // Barcode b2 = new Barcode("2222");
    //    BarcodedItem bItem2 = new BarcodedItem(b2, 5);
        BarcodedProduct bp2 = new BarcodedProduct(b2, "Milk", new BigDecimal(2.50));
        
     //   Barcode b3 = new Barcode("3333");
     //   BarcodedItem bItem3 = new BarcodedItem(b3, 5);
        BarcodedProduct bp3 = new BarcodedProduct(b3, "Cereal", new BigDecimal(5.00));
        
      //  Barcode b4 = new Barcode("4444");
      //  BarcodedItem bItem4 = new BarcodedItem(b4, 5);
        BarcodedProduct bp4 = new BarcodedProduct(b4, "WagyuBeef", new BigDecimal(50.00));
        
     //   Barcode b5 = new Barcode("5555");
    //    BarcodedItem bItem5 = new BarcodedItem(b5, 5);
        BarcodedProduct bp5 = new BarcodedProduct(b5, "500YearOldWine", new BigDecimal(52.00));
        
        
        barcodedItemDatabase.put(b1, bItem);
        barcodedItemDatabase.put(b2, bItem2);
        barcodedItemDatabase.put(b3, bItem3);
        barcodedItemDatabase.put(b4, bItem4);
        barcodedItemDatabase.put(b5, bItem5);
        
        
        db.put(b1, bp1);
        db.put(b2, bp2);
        db.put(b3, bp3);
        db.put(b4, bp4);
        db.put(b5, bp5);
        
        
        Map<PriceLookupCode,PLUCodedProduct> pluDb = ProductDatabases.PLU_PRODUCT_DATABASE;
        Map<PriceLookupCode,PLUCodedItem> pluCodedItemDatabase = PLUCodedItemDatabase.PLUCoded_ITEM_DATABASE;
        PLUCodedProduct plu1 = new PLUCodedProduct(plucode1,"Banana [4011]", new BigDecimal(1.00));
        PLUCodedProduct plu2 = new PLUCodedProduct(plucode2,"Wagyu Beef [2021]", new BigDecimal(50.00));
        PLUCodedProduct plu3 = new PLUCodedProduct(plucode3,"500 Year Old Wine [5552]", new BigDecimal(52.00));
        
        
        pluCodedItemDatabase.put(plucode1, pluItem1);
        pluDb.put(plucode1, plu1);
        pluCodedItemDatabase.put(plucode2, pluItem2);
        pluDb.put(plucode2, plu2);
        pluCodedItemDatabase.put(plucode3, pluItem3);
        pluDb.put(plucode3, plu3);
        
        
        // System.out.println("The product is: " + db.get(b1).getPrice());
        
        /*===============================================================
         *                INITIALIZE CONTROL SOFTWARE
         *===============================================================*/
        
        // Initialize Control Software
        final Currency c1 = Currency.getInstance("CAD");
    	final int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
    	final BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.25).setScale(2, RoundingMode.HALF_UP), new BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP), new BigDecimal(2.00).setScale(2, RoundingMode.HALF_UP)};
    	final int scaleMaximumWeight = 500; // Don't know the units of the scale, will figure out later
    	final int scaleSensitivity = 1; // Don't know the units also
        controlSoftware = new ControlSoftware(c1, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
        blockStation = new BlockStation(controlSoftware.selfCheckout);
        System.out.println("Self-checkout is ready! Scan your item...");
        
        /*===============================================================
         *                START OF CONTROL FLOW: Scanning
         *===============================================================*/
        
         // Run of Use-Case #1: User wants to buy 2 bananas
        
        //BarcodedItem customerItem1 = new BarcodedItem(b1, 500);
        //int customerItem1quantity = 1;
        
        //Card card = new Card("Visa", "1234 5678 9102 2212", "Aris", String pin, boolean isTapEnabled, boolean hasChip)
        
        //controlSoftware.scanProduct(customerItem1, customerItem1quantity);
        
        // if scanProduct returns True, it will be added to shopping cart
        // else if scanProduct returns False, it is an item which needs to be weighed.
        
        //double customerItem1weight = controlSoftware.weighItem(customerItem1);
        
        // Remove item from scale. This event should trigger add to shopping cart
        
        
        // If checkout button not pressed, loop and prompt the scan for next item
        // else, invoke logic for payment
        
        
        // Run of Use-case #2: User wants to buy Reese's Pieces (iteration 3)
        
         //PLUCodedItem pluCodedItem1 = new PLUCodedItem(plu1, 100);
         //int customerItem2quantity = 1;
//        
//        controlSoftware.scanProductUsingPLUCode(pluCodedItem1, customerItem2quantity);
        
        // Run of Use-case #3: User wants to look up the price via barcode
        
        //controlSoftware.lookupProductUsingBarcode(customerItem1);
        
        // Run of Use-case #3: User wants to look up the price via barcode
        
        //controlSoftware.lookup.lookupUsingPLU(plu1);
        
        
        /*===============================================================
         *                START OF CONTROL FLOW: Checkout/Payment (TODO)
         *===============================================================*/
        
         
        
        
        /*===============================================================
         *                END OF CONTROL FLOW: Print Receipt (TODO)
         *===============================================================*/
        
        
        m = new MainScreen();
        pss = new PaymentSelectorScreen();
        card = new CardPaymentScreen();;
        cash = new CashPaymentScreen();
        giftcard = new GiftCardPaymentScreen();   
        lookup = new LookupItemScreen();
        plulookup = new LookupPLUScreen();
        thank = new ThankYouForShoppingScreen();
        attendant = new AttendantMenuScreen();
        welcome = new WelcomeScreen();
        membership = new MembershipScreen();
        
        
        selectmethod = new CardSelectMethodScreen();
        tap = new TapCardScreen();
        swipe = new SwipeCardScreen();
        shutdown = new ShutDownScreen();
        
        mainFrame = controlSoftware.selfCheckout.screen.getFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(600,500);
        mainFrame.setContentPane(welcome);
        mainFrame.pack();
       
        controlSoftware.selfCheckout.screen.setVisible(true);
        
        mainFrame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                    x = e.getX();
                    y = e.getY();
            }
        });
        mainFrame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                    int left = mainFrame.getLocation().x;
                    int top = mainFrame.getLocation().y;
                    mainFrame.setLocation(left + e.getX() - x, top + e.getY() - y);
            }
        });
        
        
	}
	
	public static void goToScreen(String idx) {
		
		if (idx == "welcome") { 
			mainFrame.setContentPane(welcome);
			mainFrame.pack();
		}
		if(idx == "main") { //Main Screen
			
			mainFrame.setContentPane(m);
			mainFrame.pack();
		} 
		if(idx == "pay") { //PaymentSelectorScreen
			//mainFrame.removeAll();
			mainFrame.setContentPane(pss);
			mainFrame.pack();
		}
		if (idx == "card") { 
			mainFrame.setContentPane(card);
			mainFrame.pack();
		}
		if (idx == "cash") { 
			mainFrame.setContentPane(cash);
			mainFrame.pack();
		}
		if (idx == "giftcard") { 
			mainFrame.setContentPane(giftcard);
			mainFrame.pack();
		}
		if (idx == "lookup") { 
			mainFrame.setContentPane(lookup);
			mainFrame.pack();
		}
		if (idx == "thank") { 
			mainFrame.setContentPane(thank);
			mainFrame.pack();
		}
		if (idx == "attendant") { 
			mainFrame.setContentPane(attendant);
			mainFrame.pack();
		}
		if (idx == "membership") { 
			mainFrame.setContentPane(membership);
			mainFrame.pack();
		}
		//For PLU 
		if (idx =="plulookup") {
			mainFrame.setContentPane(plulookup);
			mainFrame.pack();
		}
		if (idx =="selectmethod") {
			mainFrame.setContentPane(selectmethod);
			mainFrame.pack();
		}
		if (idx =="tap") {
			mainFrame.setContentPane(tap);
			mainFrame.pack();
		}
		if (idx =="swipe") {
			mainFrame.setContentPane(swipe);
			mainFrame.pack();
		}
		
		if (idx == "shutdown") {
			
			mainFrame.setContentPane(shutdown);
			mainFrame.pack();
		}
	}
}
