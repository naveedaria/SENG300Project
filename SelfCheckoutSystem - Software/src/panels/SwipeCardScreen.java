package panels;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.lsmr.selfcheckout.devices.SimulationException;

import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;

import driver.GUIDriver;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.ImageIcon;

import java.util.Calendar;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import java.awt.Canvas;

public class SwipeCardScreen extends JPanel {
	
	private JTextField cardNumber_textField;
	private JTextField expiry_textField;
	private JTextField cardCompany_textField;
	private JTextField cvv_textField;
	private JTextField customerName_textField;
	private JPasswordField pin_PasswordField;

	/**
	 * Create the panel.
	 */
	public SwipeCardScreen() {
		
		JLabel card_Payment_Label = new JLabel( "Swipe Payment");
		card_Payment_Label.setBackground(new Color(255, 255, 255));
		card_Payment_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
	

		
		JLabel cardNumber_Label = new JLabel("Card Number:");
		cardNumber_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		cardNumber_textField = new JTextField();
		cardNumber_textField.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumber_textField.setColumns(10);
		
		JLabel cvv_label = new JLabel("CVV:");
		
		JLabel pin_Label = new JLabel("Pin:");
		
		JButton btnCompletePayment = new JButton("Continue Payment");
		btnCompletePayment.setBackground(new Color(100, 149, 237));
		
		JLabel lblNewLabel_4 = new JLabel("Please enter card details below");
		
		JButton btnGoBack = new JButton("Go Back");
		
		expiry_textField = new JTextField();
		expiry_textField.setColumns(10);
		
		cardCompany_textField = new JTextField();
		cardCompany_textField.setHorizontalAlignment(SwingConstants.CENTER);
		cardCompany_textField.setColumns(10);
		
		JLabel card_Company_Label = new JLabel("Card Company:");
		card_Company_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel expiry_Label = new JLabel("Expiry (MM/YY):");
		
		cvv_textField = new JTextField();
		cvv_textField.setColumns(10);
		
		customerName_textField = new JTextField();
		customerName_textField.setHorizontalAlignment(SwingConstants.CENTER);
		customerName_textField.setColumns(10);
		
		JLabel CustomerName_Label = new JLabel("Customer Name:");
		CustomerName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		pin_PasswordField = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(285)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(cardNumber_Label)
										.addComponent(card_Company_Label))
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cardNumber_textField, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
										.addComponent(cardCompany_textField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addComponent(customerName_textField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addComponent(expiry_Label, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(expiry_textField, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addGap(55)
									.addComponent(pin_Label)
									.addGap(18)
									.addComponent(pin_PasswordField, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(87)
									.addComponent(cvv_label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(cvv_textField, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(327)
							.addComponent(card_Payment_Label, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(299)
							.addComponent(CustomerName_Label, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4)))
					.addContainerGap(272, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(btnGoBack)
					.addPreferredGap(ComponentPlacement.RELATED, 527, Short.MAX_VALUE)
					.addComponent(btnCompletePayment)
					.addGap(138))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(card_Payment_Label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CustomerName_Label)
						.addComponent(customerName_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cardCompany_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cardNumber_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cardNumber_Label)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(card_Company_Label)))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(expiry_Label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(expiry_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(pin_Label)
								.addComponent(pin_PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cvv_label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(cvv_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(84)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack)
						.addComponent(btnCompletePayment))
					.addGap(154))
		);
		setLayout(groupLayout);

		
		
		
		
		
		
	
		
		
		
		
		
		
		
		// tap to redeem button shouldn't go to new screen, only update the payment balance and print in text box 
		//btnNewButton.addActionListener(new GotoAttendantScreen());
		 
		btnCompletePayment.addActionListener(new CheckIfComplete());
		
		btnGoBack.addActionListener(new GotoPreviousScreen());
		
		
	}
	
	




	
	private class GotoPreviousScreen implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				eraseAll();
				GUIDriver.goToScreen("selectmethod");
			}
		}
	
	


	 
	 

	 
	 
	private class CheckIfComplete implements ActionListener{
		 
		
		@Override
		public void actionPerformed(ActionEvent e) {
				
			
			
			try {
//				
				String[] expiry = expiry_textField.getText().split("/"); 
				
				String expiryM = expiry[0];
				String expiryY = expiry[1];
				
				int expiryMonth = Integer.parseInt(expiryM);
				int expiryYear = Integer.parseInt(expiryY);
				
				expiryYear = 2000 + expiryYear;
				
				Calendar expiryCal = Calendar.getInstance();
				
				expiryCal.set(Calendar.YEAR, expiryYear);
				expiryCal.set(Calendar.MONTH, expiryMonth);
				
			
			
				char[] pin = pin_PasswordField.getPassword();
				String pinString = String.valueOf(pin);
				
		
	
				
				GUIDriver.controlSoftware.swipeToPay(cardCompany_textField.getText(), "credit",cardNumber_textField.getText(), customerName_textField.getText(), 
						cvv_textField.getText(),pinString,true,true,expiryCal, new BigDecimal(1000),null ,true);
					
				GUIDriver.goToScreen("thank");
			} catch (IOException | ArrayIndexOutOfBoundsException | SimulationException e1) {
				
				warningDialog("Invalid Card","The card swiped was invalid please try again");
				eraseAll();
			
			}
	
		

		
		}
	}
	


	private void eraseAll() {
			
		cardNumber_textField.setText("");
		cardCompany_textField.setText("");
		expiry_textField.setText("");
		
		cvv_textField.setText("");
		
		customerName_textField.setText("");
		pin_PasswordField.setText("");
	}



	 private void warningDialog(String title,String message) {
			JOptionPane.showMessageDialog(this,message,title,JOptionPane.WARNING_MESSAGE);
		}
}
