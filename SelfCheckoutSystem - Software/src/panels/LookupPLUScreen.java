package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import driver.GUIDriver;


import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LookupPLUScreen extends JPanel {
	private JTextField txtEnterPlu;
	

	/**
	 * Create the panel.
	 */
	public LookupPLUScreen() {
		
		JLabel lblNewLabel = new JLabel("Search item by PLU ");
		
		JButton searchButton = new JButton("Add");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton backButton = new JButton("Back");
		
		txtEnterPlu = new JTextField();
		txtEnterPlu.setText("Enter item PLU Code and press \"Add");
		txtEnterPlu.setColumns(10);
	
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfPLUProduct(GUIDriver.pluItem1));
		listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfPLUProduct(GUIDriver.pluItem2));
		listModel.addElement(GUIDriver.controlSoftware.shoppingCart.getDescriptionOfPLUProduct(GUIDriver.pluItem3));
		
		JList<String> list = new JList<>(listModel);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(77)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(121)
					.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(174)
					.addComponent(lblNewLabel)
					.addContainerGap(181, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(77)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtEnterPlu, Alignment.LEADING)
						.addComponent(list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lblNewLabel)
					.addGap(11)
					.addComponent(txtEnterPlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backButton)
						.addComponent(searchButton))
					.addGap(24))
		);
		setLayout(groupLayout);
		backButton.addActionListener(new GoBackToMain());
		searchButton.addActionListener(new SearchItem());
		txtEnterPlu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldMouseClicked(evt);
            }
		}); 
	}
	
	private class GoBackToMain implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GUIDriver.goToScreen("main");
			
		}
	}
	private class SearchItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(txtEnterPlu.getText().equalsIgnoreCase("4011")) {
			
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b1, 1);
				MainScreen.updateTransactionFields();
				txtEnterPlu.setText("Item added succsessfully!");
				}
			else if(txtEnterPlu.getText().equalsIgnoreCase("2021")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b4, 1);
				MainScreen.updateTransactionFields();
				txtEnterPlu.setText("Item added succsessfully!");
				
			}

			else if(txtEnterPlu.getText().equalsIgnoreCase("5552")) {
				
				GUIDriver.controlSoftware.scanProduct(GUIDriver.b5, 1);
				MainScreen.updateTransactionFields();
				txtEnterPlu.setText("Item added succsessfully!");
			}	
			else {
				
				txtEnterPlu.setText("Item does not exist! Enter exact item name.");
			}
			}
	}
	private void textFieldMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        if (this.txtEnterPlu.getText().equals("Enter item PLU Code and press \"Add")) {
           this.txtEnterPlu.setText("");
        }
        
        if (this.txtEnterPlu.getText().equals("Item added succsessfully!")) {
            this.txtEnterPlu.setText("");
        }
        
        if (this.txtEnterPlu.getText().equals("Item does not exist! Enter exact item name.")) {
            this.txtEnterPlu.setText("");
        }
}
}