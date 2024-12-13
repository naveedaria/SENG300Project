package panels;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;

import driver.GUIDriver;

import javax.swing.JButton;

public class ShutDownScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShutDownScreen() {
		
		JLabel lblNewLabel = new JLabel("Station is shut down.");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 19));
		
		JButton startupBtn = new JButton("Start up.");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(93))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(105)
					.addComponent(startupBtn, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(141, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startupBtn, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(117, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		startupBtn.addActionListener(new StartupStation());
	}
	
	private class StartupStation implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
				boolean approved = showLoginScreen();
				if(approved) {
					
					
					GUIDriver.goToScreen("welcome");
				} else {
					showIncorrectMessage();
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
			app = GUIDriver.controlSoftware.stationControl.logIn(user.getText().trim(), String.valueOf(pwd.getPassword()));
		}
		
		return app;
	}
	
	private void showIncorrectMessage() {
		JOptionPane.showMessageDialog(this,
			    "The passcode entered is incorrect. Please try again.",
			    "Incorrect Passcode",
			    JOptionPane.WARNING_MESSAGE);
	}
}
