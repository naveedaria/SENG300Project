package controlSoftware;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Coin;

public class CalculateCoinPaymentTest {

	@Test
	public void testCalculateCoinPaymentOneCoin() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			String barcode = "1234";
			float weight = (float) 2.0;
			float price = (float) 1.50;
			String name = "apple";
			controlSoft.scanProduct(barcode, weight, price, name);
			controlSoft.setTotalBalance();
			
			BigDecimal coinValue = new BigDecimal(1);
			Coin someCoin = new Coin(coinValue,currency);
			BigDecimal resultValue = controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin);
			BigDecimal result = controlSoft.calculateCoinPayment(resultValue);
			BigDecimal expected = new BigDecimal(price).subtract(coinValue);
			assertFalse(expected.compareTo(result) == 0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testCalculateCoinPaymentMultipleCoins() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			String barcode = "1234";
			float weight = (float) 2.0;
			float price = (float) 1.50;
			String name = "apple";
			
			String barcode2 = "2468";
			float weight2 = (float) 2.0;
			float price2 = (float) 1.50;
			String name2 = "apple";
			
			controlSoft.scanProduct(barcode, weight, price, name); 
			controlSoft.scanProduct(barcode2, weight2, price2, name2);
			controlSoft.setTotalBalance();
			
			BigDecimal coinValue = new BigDecimal(1);
			Coin someCoin = new Coin(coinValue,currency);
			BigDecimal resultValue = controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin);
			
			BigDecimal coinValue2 = new BigDecimal(1);
			Coin someCoin2 = new Coin(coinValue2,currency);
			BigDecimal finalResult = controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin2);
			
			float expectedBalance = price+price2;
			BigDecimal totalCoinVal = coinValue.add(coinValue2);
			BigDecimal expected = (new BigDecimal(expectedBalance)).subtract(totalCoinVal);
			assertTrue(expected.compareTo(finalResult)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}

}
