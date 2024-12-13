package panels;

import javax.swing.JPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.GUIDriver;
import javax.swing.JScrollPane;

import javax.swing.JButton;
import javax.swing.JList;

public class LookupItemScreen extends JPanel {
	public JTextField textField;
	JList<String> itemLst;
	public static String flag;
	
	/**
	 * Create the panel.
	 */
	public LookupItemScreen() {
		
		JLabel lblNewLabel = new JLabel("Look Up an Item");
		
		JLabel searchLbl = new JLabel("Add item:");
		
		textField = new JTextField();
		textField.setText("Enter exact item name and press \"Add\"...");
		textField.setColumns(10);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfBarcodedProduct(GUIDriver.bItem));
        listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfBarcodedProduct(GUIDriver.bItem2));
        listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfBarcodedProduct(GUIDriver.bItem3));
        listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfBarcodedProduct(GUIDriver.bItem4));
        listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfBarcodedProduct(GUIDriver.bItem5));

        itemLst = new JList<>(listModel);
        add(new JScrollPane(itemLst));
		JButton searchBtn = new JButton("Add");
		
		JButton goBackBtn = new JButton("Go Back");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(itemLst, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(172))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(searchLbl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(goBackBtn)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchLbl)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(itemLst, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(goBackBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		searchBtn.addActionListener(new SearchItem());
		goBackBtn.addActionListener(new GoBackToMain());
		textField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldMouseClicked(evt);
            }
        });

	}
	private class GoBackToMain implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GUIDriver.goToScreen(flag);
			
		}
		
	}
	private class SearchItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().equalsIgnoreCase("Banana")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b1, 1);
				MainScreen.updateTransactionFields();
				textField.setText("Item added succsessfully!");
				
				
			}
			
			else if(textField.getText().equalsIgnoreCase("Milk")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b2, 1);
				MainScreen.updateTransactionFields();
				textField.setText("Item added succsessfully!");
				
				
			}
			
			else if(textField.getText().equalsIgnoreCase("Cereal")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b3, 1);
				MainScreen.updateTransactionFields();
				textField.setText("Item added succsessfully!");
				
				
			}

			
			else if(textField.getText().equalsIgnoreCase("Wagyu Beef")||textField.getText().equalsIgnoreCase("WagyuBeef")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b4, 1);
				MainScreen.updateTransactionFields();
				textField.setText("Item added succsessfully!");
				
				
			}

			else if(textField.getText().equalsIgnoreCase("500 Year Old Wine") || textField.getText().equalsIgnoreCase("500YearOldWine")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b5, 1);
				MainScreen.updateTransactionFields();
				textField.setText("Item added succsessfully!");
				
				
			}
			
			else {
				
				textField.setText("Item does not exist! Enter exact item name.");
			}

			
		}
		
	}
	private void textFieldMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        if (this.textField.getText().equals("Enter exact item name and press \"Add\"...")) {
           this.textField.setText("");
        }
        
        if (this.textField.getText().equals("Item added succsessfully!")) {
            this.textField.setText("");
        }
        
        if (this.textField.getText().equals("Item does not exist! Enter exact item name.")) {
            this.textField.setText("");
        }
}
}
