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

public class CardSelectMethodScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardSelectMethodScreen() {
		
		//setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		//setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		JLabel lblNewLabel = new JLabel("Card Payment");

		JButton payWithTap = new JButton("Tap");
	
		
		JLabel cardMethodLabel = new JLabel("Please choose your method of card payment");
		
		JButton payWithSwipe = new JButton("Swipe");
		
		JButton goBack_btn = new JButton("Go Back");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(453, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(377))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addComponent(payWithTap, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cardMethodLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(296)
							.addComponent(payWithSwipe, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
					.addGap(77))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(goBack_btn)
					.addContainerGap(756, Short.MAX_VALUE))
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
						.addComponent(payWithTap, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
						.addComponent(payWithSwipe, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
					.addGap(96)
					.addComponent(goBack_btn)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

			

		
		
		class Swiping implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	
				GUIDriver.goToScreen("swipe");
			}
			
		}
		
		class Tapping implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				GUIDriver.goToScreen("tap");
			}
		}
		
		class GotoPrevious implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				GUIDriver.goToScreen("pay");
			}
		}
		
		payWithTap.addActionListener(new Tapping());
		payWithSwipe.addActionListener(new Swiping());
		goBack_btn.addActionListener(new GotoPrevious());
	}
}


	

