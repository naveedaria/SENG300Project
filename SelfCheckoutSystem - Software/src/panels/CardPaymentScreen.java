package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import driver.GUIDriver;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;
import java.awt.Dimension;

public class CardPaymentScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardPaymentScreen() {
		//setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		//setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		JLabel lblNewLabel = new JLabel("Card Payment");
		
			
		
		JButton payWithCredit = new JButton("Credit Card");
		JButton payWithDebit = new JButton("Debit Card");
		
		JLabel cardMethodLabel = new JLabel("Please choose your method of card payment");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addComponent(payWithDebit, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
					.addComponent(payWithCredit, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(158))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(339, Short.MAX_VALUE)
					.addComponent(cardMethodLabel)
					.addGap(304))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(412, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(377))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cardMethodLabel)
					.addGap(89)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(payWithCredit, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addComponent(payWithDebit, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(163, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

			

		
		
		class PayUsingDebitCard implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				GUIDriver.goToScreen("selectmethod");
			}
			
		}
		
		
		
		class PayUsingCreditCard implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIDriver.goToScreen("selectmethod");
			}
		}
		
		payWithCredit.addActionListener(new PayUsingCreditCard());
		payWithDebit.addActionListener(new PayUsingDebitCard());
		
		
	}
	
	
	
}


	

