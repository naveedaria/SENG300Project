package panels;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;

import driver.GUIDriver;

public class PaymentSelectorScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public PaymentSelectorScreen() {
		
		JLabel lblNewLabel = new JLabel("Please Select a Payment option");
		
		JButton backButton = new JButton("<-");
		
		JButton cardButton = new JButton("Card");
		


		
		
		JButton giftcardButton = new JButton("Giftcard");
		
		JButton cashButton = new JButton("Cash");
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(57)
							.addComponent(cardButton)
							.addGap(55)
							.addComponent(giftcardButton)
							.addGap(55)
							.addComponent(cashButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(177)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(backButton)))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cardButton)
						.addComponent(giftcardButton)
						.addComponent(cashButton))
					.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
					.addComponent(backButton)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		backButton.addActionListener(new GotoPrevScreen());
		cardButton.addActionListener(new GotoCard());
		giftcardButton.addActionListener(new GotoGiftcard());
		cashButton.addActionListener(new GotoCash());
	}
	
	private class GotoPrevScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	
			try {
				GUIDriver.controlSoftware.finishedAddingItems();
			} catch (IOException | DisabledException | OverloadException e1) {
			
				e1.printStackTrace();
			}
			GUIDriver.goToScreen("main");
			
		}
		
	}
	
	
	private class GotoCard implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				GUIDriver.controlSoftware.finishedAddingItems();
			} catch (IOException | DisabledException | OverloadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GUIDriver.goToScreen("card");
		}
		
	}
	
	
	
	private class GotoGiftcard implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				GUIDriver.controlSoftware.finishedAddingItems();
			} catch (IOException | DisabledException | OverloadException e1) {
				
				e1.printStackTrace();
			}
			GUIDriver.goToScreen("giftcard");
		}
		
	}
	
	
	private class GotoCash implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				GUIDriver.controlSoftware.finishedAddingItems();
			} catch (IOException | DisabledException | OverloadException e1) {
				
				e1.printStackTrace();
			}
			
			if(GUIDriver.controlSoftware.shoppingCart.getTotalPayment().compareTo(new BigDecimal(0)) == 0) {
				GUIDriver.cash.lblNewLabel_8.setText(new BigDecimal(-1.0).multiply(GUIDriver.cash.balance).setScale(2, RoundingMode.HALF_UP).toString());
				GUIDriver.cash.btnNewButton.setEnabled(false);
				GUIDriver.cash.btnNewButton_1.setEnabled(false);
				GUIDriver.cash.btnNewButton_2.setEnabled(false);
				GUIDriver.cash.btnNewButton_3.setEnabled(false);
				GUIDriver.cash.btnNewButton_4.setEnabled(false);
				GUIDriver.cash.btnNewButton_5.setEnabled(false);
				GUIDriver.cash.btnNewButton_6.setEnabled(false);
				GUIDriver.cash.btnNewButton_7.setEnabled(false);
				GUIDriver.cash.btnNewButton_8.setEnabled(false);
				GUIDriver.cash.btnNewButton_9.setEnabled(false);
				GUIDriver.cash.btnNewButton_10.setEnabled(true);
			}
			
			GUIDriver.cash.lblNewLabel_2.setText(GUIDriver.controlSoftware.shoppingCart.getTotalPayment().toString());
			GUIDriver.cash.lblNewLabel_3.setText(GUIDriver.controlSoftware.shoppingCart.getTotalPayment().toString());
			GUIDriver.cash.balance = GUIDriver.controlSoftware.shoppingCart.getTotalPayment();
			
			GUIDriver.goToScreen("cash");
		}
	
	
	}
}
