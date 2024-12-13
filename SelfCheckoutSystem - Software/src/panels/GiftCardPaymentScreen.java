package panels;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.lsmr.selfcheckout.Barcode;

import controlSoftware.Receipt;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import driver.GUIDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class GiftCardPaymentScreen extends JPanel {
	private JTextField giftcardNumInput;
	private String giftcardNum;
	private String amountAvail;
	private String newBalance;

	/**
	 * Create the panel.
	 */
	public GiftCardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Giftcard Payment");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Import ImageIcon - from stack 
		Image image = null;
		try {
			image = ImageIO.read(new File("GUI Images/gift-card.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(image != null) {
			image = image.getScaledInstance(-1, 100, Image.SCALE_DEFAULT);
			ImageIcon iconLogo = new ImageIcon(image);
			// In init() method write this code
			lblNewLabel.setIcon(iconLogo);
		}
		

		
		JLabel lblNewLabel_1 = new JLabel("Enter your card number to check the amount available:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		giftcardNumInput = new JTextField();
		giftcardNumInput.setColumns(10);
		//String giftcardNum = giftcardNumInput.getText();
		
		JButton btnTapToRedeem = new JButton("Tap to Redeem Gift Card!");
		btnTapToRedeem.setBackground(new Color(100, 149, 237));
		btnTapToRedeem.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_2 = new JLabel("Your remaining payment balance is: ");
		
		JTextPane remainingBalanceOutput = new JTextPane();
		
		JLabel lblNewLabel_3 = new JLabel("CAD $");
		
		JButton btnCompletePayment = new JButton("Continue Payment");
		btnCompletePayment.setBackground(new Color(100, 149, 237));
		
		JLabel lblNewLabel_4 = new JLabel("Current amount on Co-op GIft Card: ");
		
		JTextPane currentAmountAvailOutput = new JTextPane();
		
		JLabel lblNewLabel_3_1 = new JLabel("CAD $");
		
		JButton btnGoBack = new JButton("Go back");
		
		JButton btnCallAttendant = new JButton("Call Attendant");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(208, Short.MAX_VALUE)
					.addComponent(btnTapToRedeem)
					.addGap(182))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCompletePayment, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
											.addComponent(lblNewLabel_3)
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(26)
											.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnGoBack)
										.addComponent(lblNewLabel_1))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(currentAmountAvailOutput, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(remainingBalanceOutput, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(giftcardNumInput, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
					.addGap(55))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCallAttendant)
					.addGap(82)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCallAttendant)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(giftcardNumInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentAmountAvailOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3_1)
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addComponent(btnTapToRedeem)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(remainingBalanceOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnCompletePayment))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(btnGoBack)))
					.addGap(191))
		);
		setLayout(groupLayout);

		// tap to redeem button shouldn't go to new screen, only update the payment balance and print in text box 
		//btnNewButton.addActionListener(new GotoAttendantScreen());
		btnCompletePayment.addActionListener(new CheckIfComplete());
		
		btnGoBack.addActionListener(new GotoPaymentSelector());
		
		btnCallAttendant.addActionListener(new GotoAttendant());
		
		// Should happen when ENTER is pressed (by default). Maybe? Make sure that's actually how it works.
		giftcardNumInput.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	// LOGIC: retrieve the amount available from the database, maybe some error checking
            	// to see if the card actually exists in the database. Put that amount into the 
            	// amountAvail (String) variable, and then display it 
            	
            	giftcardNum = giftcardNumInput.getText();
				BigDecimal amountAvailNum= GUIDriver.controlSoftware.getAmountOnGiftCard(giftcardNum);
				if(amountAvailNum.compareTo(new BigDecimal(-1))==0) {
					amountAvail = "Invalid Gift Card";
				}else {
					amountAvail = amountAvailNum.toString();
				}
				currentAmountAvailOutput.setText(amountAvail);
			
            }});
		
		
		btnTapToRedeem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// LOGIC: subtract gift card balance from total balance, update giftcard balance and remaining balance.
				// Display remaining balance in remainingBalanceOutput JTextField.

				BigDecimal newBalanceNum;
				try {
					newBalanceNum = GUIDriver.controlSoftware.useGiftCard(giftcardNum);
					if(newBalanceNum.compareTo(new BigDecimal(-1))==0) {
						newBalance = "Invalid Gift Card";
					}else {
						newBalance = newBalanceNum.toString();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				remainingBalanceOutput.setText(newBalance);
				
				BigDecimal amountAvailNum= GUIDriver.controlSoftware.getAmountOnGiftCard(giftcardNum);
				amountAvail = amountAvailNum.toString();
				currentAmountAvailOutput.setText(amountAvail);
			}
		});
		
		//btnTapToRedeem.addActionListener(new UpdatePaymentBalance());
	}
	
	private class GotoPaymentSelector implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			GUIDriver.goToScreen("pay");
		}
	}
	
	private class GotoAttendant implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			GUIDriver.goToScreen("attendant");
		}
	}
	
	private class CheckIfComplete implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			BigDecimal newBalanceBD = new BigDecimal(newBalance);
			
			if (newBalanceBD.compareTo(new BigDecimal("0")) == 0){
				GUIDriver.goToScreen("thank");
			}
			else {
				GUIDriver.goToScreen("pay");
			}
			
		}
	}

	
	/*
	private class UpdatePaymentBalance implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// LOGIC: subtract gift card balance from total balance, update giftcard balance.
			// Get remaining balance, and display it in remainingBalanceOutput JTextField.
			
			GiftCardPaymentScreen.remainingBalanceOutput.setText(newBalance);
		}
	}
	*/
	
	/*
	private class GetGiftcardNum implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String giftcardNum = getCardNum();
			
			
		}
	}
	*/

}
