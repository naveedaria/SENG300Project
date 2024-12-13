package panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.GUIDriver;

import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomeScreen extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public WelcomeScreen() {
		
		JLabel lblNewLabel = new JLabel("Welcome to Co-op!");
		lblNewLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 14));
		lblNewLabel.setIcon(null);
		
		// Import ImageIcon - from stack 
		//Image image = 
		//ImageIcon iconLogo = new ImageIcon("GUI Images/gift-card.gif");
		// In init() method write this code
		//lblNewLabel.setIcon(iconLogo);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStart.setBackground(new Color(60, 179, 113));
		
		JButton btnBroughtOwnBag = new JButton("I brought my own bag");
		btnBroughtOwnBag.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBroughtOwnBag.setBackground(new Color(60, 179, 113));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(145, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(177))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addGap(145))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBroughtOwnBag, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
							.addGap(136))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(btnBroughtOwnBag, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		btnStart.addActionListener(new GotoMembershipScreen());
		btnBroughtOwnBag.addActionListener(new GotoBagPopup());
		

	}
	
	private class GotoMembershipScreen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			GUIDriver.goToScreen("membership");
		}
	}

	
	private class GotoBagPopup implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			confirmBagPlacement();
		}
	}
	
	
	private void confirmBagPlacement() {
		JOptionPane.showMessageDialog(this, "Press OK when finished placing your bags in the bagging area.");
		// after they click ok, we re-normalize the scale (however that's implemented)
	}

	/*
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
	}
	*/
}
