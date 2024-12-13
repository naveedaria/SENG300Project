package controlSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class Receipt {
	
	
	
	public static String[] printReceipt(ControlSoftware cs) {
		// Some strings used to format the receipt 
		//String s1 = "------------------------------------";
		// 2-D Array from the shopping cart class
		String[][] cart = cs.shoppingCart.SHOPPING_CART_ARRAY;
		// Barcoded Item array from shopping cart class
		// A single barcoded temporary item
		// A barcoded product
		BarcodedProduct prod;
		// Printing 
		//System.out.println(s1);
		//String s2 = String.format("%-1s %1s %10s\n","Qty", "Item", "Price");
		//System.out.println(s2);
		String line;
		String[] transactionRecord = new String[cart.length];
		for (int i = 0; i < cart.length; i++) {
			try {
			// Accessing the barcoded item from the array
			BarcodedItem tempItem = cs.shoppingCart.BARCODEDITEM_ARRAY[i];
			// Getting the product from the Database
			prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(tempItem.getBarcode());
			// String format for each line printed in the receipt
			// Quantity and Name are accessed from the shopping cart array
			// Price is accessed from the product class
			line = String.format("%-1s %1s %10s\n", cart[i][1], cart[i][0], prod.getPrice());
			transactionRecord[i] = line;
			// Print line
			//System.out.println(line);
			}catch (NullPointerException e) {
			}
		}
		// Print end line
		//System.out.println(s1);
		return transactionRecord;
	} 
	
	public static void getItemFromReceipt(String string, ControlSoftware cs) {
        String[][] cart = cs.shoppingCart.SHOPPING_CART_ARRAY;
        String[] name = string.split("\\s+");
        for (int i = 0; i < cart.length; i++) {
        	if(cart[i][0] == null) {
        		break;
        	}
            if (name[1].compareTo(cart[i][0].toString()) == 0) {
                Barcode barcode = cs.shoppingCart.BARCODE_ARRAY[i];
                BarcodedItem bItem = BarcodedItemDatabase.BARCODED_ITEM_DATABASE.get(barcode);
                int quantity = Integer.parseInt(cart[i][1]);
                cs.shoppingCart.removeFromShoppingCart(bItem, quantity);
                cs.removeFromBaggingArea(bItem);
                break;
            }
        }


    }
	
	

}
