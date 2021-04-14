package panels;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.lsmr.selfcheckout.Barcode;

import controlSoftware.Receipt;
import driver.CommandLineDriver;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class MainScreen extends JPanel {
	private JTextField txtEnterYourBarcode;
	public static JTextArea totalArea;
	public static JList itemList;
	public static JLabel status;
	public static JLabel inkLabel;
	public static JLabel paperLabel;
	// Randome level initialized for the levels
	public static int inkLevel = 100, paperLevel = 100;

	/**
	 * Create the panel.
	 */
	//Step 1: After you make a new panel, this code gets generated by going into the Design tab
	public MainScreen() {
		//Step 5 (optional) : if you have a UI element that you want to access the data of (i.e. text field) in an ActionListener, you need to make it a class field and make it public
		JButton nextButton = new JButton("Finish & Pay");
		
		itemList = new JList();
		
		totalArea = new JTextArea();
		totalArea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		totalArea.setText(CommandLineDriver.controlSoftware.paymentTotal.toString());
		totalArea.setEditable(false);
		txtEnterYourBarcode = new JTextField();
		txtEnterYourBarcode.setText("Enter your barcode and press \"Scan Barcode\"...");
		txtEnterYourBarcode.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Total:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Scanner:");
		
		JButton scanButton = new JButton("Scan Barcode");
		
		JLabel lblNewLabel_2 = new JLabel("Scan & Bag");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		
		JLabel announcementsLabel = new JLabel("     ");
		
		JButton lookupButton = new JButton("Look Up Item");
		
		JButton bagItemButton = new JButton("Bag Item");
		
		JButton skipBaggingBUtton = new JButton("Skip Bagging");
		
		JButton pluCodeButton = new JButton("Enter PLU Code");
		
		JButton attendantButton = new JButton("Call Attendant");
		
		
		JButton removeItemButton = new JButton("Remove Item");
		
		// Status label for blocked/unblocked
		status = new JLabel();
		status.setText("Is Blocked: " + CommandLineDriver.isBlocked);
		
		// If station is blocked status msg
		if(CommandLineDriver.isBlocked) {
			status.setForeground(Color.red);
			
			status.setText("Station state: Blocked");
			// If station is unblocked status msg
		}else {
			status.setForeground(Color.green);
			status.setText("Station state: Unblocked");
		}
		
		// Initializing the labels for the ink and paper
		inkLabel = new JLabel();
		inkLabel.setText("Ink Level: " + inkLevel);
		paperLabel = new JLabel();
		paperLabel.setText("Paper Level: "+ paperLevel);
		
		// Ink level color of msg 
		if(inkLevel > 99) {
			inkLabel.setForeground(Color.green);
		}
		else if(inkLevel < 99 &&  inkLevel > 30) {
			inkLabel.setForeground(Color.yellow);
		}else {
			inkLabel.setForeground(Color.red);
		}
		
		// Paper level color of msg 
		if(paperLevel > 99) {
			paperLabel.setForeground(Color.green);
		}
		else if(paperLevel < 99 &&  paperLevel > 30) {
			paperLabel.setForeground(Color.yellow);
		}else {
			paperLabel.setForeground(Color.red);
		}
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEnterYourBarcode))
						.addComponent(itemList, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(scanButton)
							.addPreferredGap(ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
							.addComponent(bagItemButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(skipBaggingBUtton))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
								.addComponent(announcementsLabel, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(attendantButton, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
										.addComponent(lookupButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(removeItemButton, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
										.addComponent(pluCodeButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(inkLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(paperLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))))
									.addComponent(nextButton, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addGap(18)
											.addComponent(status, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
					
					.addGap(24))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(441, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(273))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(itemList, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(announcementsLabel)
							.addGap(235)
							.addComponent(inkLabel)
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(status)
								.addComponent(paperLabel))
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(removeItemButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(attendantButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(pluCodeButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(lookupButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(totalArea, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtEnterYourBarcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scanButton)
						.addComponent(skipBaggingBUtton)
						.addComponent(bagItemButton))
					.addGap(20))
		);
		setLayout(groupLayout);
		//Step 3: Then add the listener to the button LIKE THIS
		nextButton.addActionListener(new GotoNextScreen());
		attendantButton.addActionListener(new GotoAttendantScreen());
		lookupButton.addActionListener(new GoToLookupScreen());
		pluCodeButton.addActionListener(new GoToPLUScreen());
		scanButton.addActionListener(new ScanItem());
		removeItemButton.addActionListener(new RemoveItem());
		
		txtEnterYourBarcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barcodeFieldMouseClicked(evt);
            }
        });
		itemList.setListData(Receipt.printReceipt(CommandLineDriver.controlSoftware));
	}
	//Step 2: if you have a button that you want to do something, you need to make an action listener LIKE THIS
	private class GotoNextScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!CommandLineDriver.isBlocked){
				// TODO Auto-generated method stub
				String bag = showBagsScreen();
				
				int bagNum = Integer.parseInt(bag);
				CommandLineDriver.controlSoftware.plasticBagsUsed(bagNum);
				updateTransactionFields();
				
				CommandLineDriver.goToScreen("pay");
			}
			
		}
		
	}
	private class GoToLookupScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!CommandLineDriver.isBlocked){
				LookupItemScreen.flag = "main";
				CommandLineDriver.goToScreen("lookup");
			}
			
		}
		
	}
	
	private class GoToPLUScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!CommandLineDriver.isBlocked){
				CommandLineDriver.goToScreen("plulookup");
			}
			
		}
		
	}
	
	
	private class GotoAttendantScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!CommandLineDriver.isBlocked){
				boolean approved = showLoginScreen();
				if(approved) {
					
					LookupItemScreen.flag = "attendant";
					CommandLineDriver.goToScreen("attendant");
				} else {
					showIncorrectMessage();
				}
			
			}
		}
		
	}
	
	private boolean showLoginScreen() {
		//https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog/6555051
		JPasswordField pwd = new JPasswordField(10);
		JTextField user = new JTextField();
		boolean app = false;
		Object[] message = {
			    "Username: ", user,
			    "Password: ", pwd
			};
		
		int option = JOptionPane.showConfirmDialog(
                this,
                message,"Attendant Login",
                JOptionPane.OK_CANCEL_OPTION
                );
		
		if(option == JOptionPane.OK_OPTION) {
			app = CommandLineDriver.controlSoftware.stationControl.logIn(user.getText().trim(), String.valueOf(pwd.getPassword()));
		}
		
		return app;
	}
	
	private void showIncorrectMessage() {
		JOptionPane.showMessageDialog(this,
			    "The passcode entered is incorrect. Please try again.",
			    "Incorrect Passcode",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	private String showBagsScreen() {
		String b = JOptionPane.showInputDialog(
                this,
                "How many bags would you like to purchase: ",
                "Enter Bags",
                JOptionPane.QUESTION_MESSAGE
                );
		if(b == null || b.trim() == "") {
			b = "0";
		}
		return b;
	}
	
	private void barcodeFieldMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        if (this.txtEnterYourBarcode.getText().equals("Enter your barcode and press \"Scan Barcode\"...")) {
            txtEnterYourBarcode.setText("");
        }
    } 
	
	public static void updateTransactionFields() {
		
		double totalAmt = CommandLineDriver.controlSoftware.shoppingCart.getTotalPayment().doubleValue();
		totalArea.setText(String.format("$%.2f", totalAmt));
		itemList.setListData(Receipt.printReceipt(CommandLineDriver.controlSoftware));
		
	}
	
	private class ScanItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!CommandLineDriver.isBlocked){
				String bar = txtEnterYourBarcode.getText();
				Barcode b = new Barcode(bar);
				CommandLineDriver.controlSoftware.scanProduct(b, 1);
				updateTransactionFields();
				txtEnterYourBarcode.setText("Enter your barcode and press \"Scan Barcode\"...");
			}
		}
		
	}
	
	private class RemoveItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!CommandLineDriver.isBlocked){
				int idx = itemList.getSelectedIndex();
				if(idx > 0) {
					boolean app = showLoginScreen();
					if(app) {
						String el = (String)itemList.getModel().getElementAt(idx);
						System.out.println(el);
						Receipt.getItemFromReceipt(el, CommandLineDriver.controlSoftware);
						updateTransactionFields();
					} else {
						showIncorrectMessage();
					}
				} else {
					showErrorScreen();
				}
			}
		}
		
	}
	
	private void showErrorScreen() {
		JOptionPane.showMessageDialog(this,
			    "No item has been selected, please select an item and try again.",
			    "No Item Selected",
			    JOptionPane.WARNING_MESSAGE);
	}
}



