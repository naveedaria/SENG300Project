package panels;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import attendant.StationControl;
import driver.GUIDriver;


public class AttendantMenuScreen extends JPanel {

	public JLabel blockStateLbl;
	public JLabel paperStateLbl = new JLabel("Paper state: filled");
	
	public JLabel inkStateLbl;
	
	public JLabel coinStorageStateLbl;
	
	public JLabel banknoteStorageStateLbl;
	
	public JLabel coinDispenserStateLbl;
	public JLabel banknotesDispenserStateLbl;

	/**
	 * Create the panel.
	 */
	public AttendantMenuScreen() {
		
		JLabel lblNewLabel = new JLabel("Attendant Menu");
		
		JButton blockBtn = new JButton("Block station");
		
		JButton unblockBtn = new JButton("Unblock station");
		
		JButton refillBanknotesBtn = new JButton("Refill banknotes");
		
		JButton refillCoinsBtn = new JButton("Refill coins");
		
		JButton btnNewButton_4 = new JButton("Add ink");
		
		JButton btnNewButton_5 = new JButton("Add paper");
		
		JButton emptyCoinsBtn = new JButton("Empty coin storage");
		
		JButton emptyBankotesBtn = new JButton("Empty banknote storage");
		
		 blockStateLbl = new JLabel("Station state: unblocked");
		
		 paperStateLbl = new JLabel("Paper state: filled");
		
		 inkStateLbl = new JLabel("Ink state: filled");
		
		 coinStorageStateLbl = new JLabel("Coin Storage state: empty");
		
		 banknoteStorageStateLbl = new JLabel("Banknote Storage state: empty");
		
		 coinDispenserStateLbl = new JLabel("Coin dispenser state: filled");
		
		JButton btnNewButton_8 = new JButton("logout");
		
		 banknotesDispenserStateLbl = new JLabel("Banknote dispenser state: filled");
		
		JButton lookupItemBtn = new JButton("Look Up Item");
		
		JButton shutdownBtn = new JButton("Shutdown station");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(463, Short.MAX_VALUE)
							.addComponent(btnNewButton_8))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(215)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(blockBtn)
											.addGap(18)
											.addComponent(unblockBtn))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnNewButton_4)
											.addGap(18)
											.addComponent(btnNewButton_5))
										.addComponent(blockStateLbl)
										.addComponent(paperStateLbl)
										.addComponent(inkStateLbl))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(lookupItemBtn)
									.addGap(127)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(coinDispenserStateLbl)
								.addComponent(banknoteStorageStateLbl)
								.addComponent(coinStorageStateLbl)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(emptyCoinsBtn)
									.addGap(18)
									.addComponent(emptyBankotesBtn))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(refillCoinsBtn)
									.addGap(18)
									.addComponent(refillBanknotesBtn))
								.addComponent(banknotesDispenserStateLbl)
								.addComponent(shutdownBtn))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(refillBanknotesBtn)
						.addComponent(blockBtn)
						.addComponent(unblockBtn)
						.addComponent(refillCoinsBtn))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_5)
						.addComponent(emptyCoinsBtn)
						.addComponent(emptyBankotesBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lookupItemBtn)
						.addComponent(shutdownBtn))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(blockStateLbl)
						.addComponent(coinStorageStateLbl))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(paperStateLbl)
						.addComponent(banknoteStorageStateLbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(inkStateLbl)
						.addComponent(coinDispenserStateLbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(banknotesDispenserStateLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnNewButton_8)
					.addContainerGap())
		);
		setLayout(groupLayout);
		blockBtn.addActionListener(new BlockStation());
		unblockBtn.addActionListener(new UnblockStation());
		refillBanknotesBtn.addActionListener(new RefillBanknotesDispenser());
		refillCoinsBtn.addActionListener(new RefillCoinsDispenser());
		emptyCoinsBtn.addActionListener(new EmptyCoinStorage());
		emptyBankotesBtn.addActionListener(new EmptyBanknoteStorage());
		btnNewButton_8.addActionListener(new LogoutToMain());
		btnNewButton_4.addActionListener(new AddInk());
		btnNewButton_5.addActionListener(new AddPaper());
		lookupItemBtn.addActionListener(new GoToLookupScreen());
		shutdownBtn.addActionListener(new ShutdownStation());
		
	}
	
	private class BlockStation implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			blockStateLbl.setText("Station state: blocked");
			
			GUIDriver.isBlocked = true;
			MainScreen.status.setText("Station state: Blocked");
			MainScreen.status.setForeground(Color.red);
			GUIDriver.blockStation.isBlocked = true;
			
		}
		
	}
	
	
	private class UnblockStation implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			blockStateLbl.setText("Station state: unblocked");
			GUIDriver.isBlocked = false;
			MainScreen.status.setText("Station state: Unblocked");
			MainScreen.status.setForeground(Color.green);
			
			GUIDriver.blockStation.isBlocked = false;
			
		}
		
	}
	
	private class RefillBanknotesDispenser implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			banknotesDispenserStateLbl.setText("Banknote Dispenser state: filled");
			
		}
		
	}
	
	private class RefillCoinsDispenser implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			coinDispenserStateLbl.setText("Coin Dispenser state: filled");
			
		}
		
	}
	
	private class EmptyCoinStorage implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			coinStorageStateLbl.setText("Coin Storage state: empty");
			
		}
		
	}
	
	private class EmptyBanknoteStorage implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			banknoteStorageStateLbl.setText("Banknote Storage state: empty");
			
		}
		
	}
	
	private class GoToLookupScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GUIDriver.goToScreen("lookup");
			
		}
		
	}

	
	private class LogoutToMain implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GUIDriver.goToScreen("main");
			
		}
		
	}
	
	private class AddInk implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int inkAdded = 10;
			MainScreen.inkLevel += inkAdded;
			inkStateLbl.setText("Ink state: " + MainScreen.inkLevel);
			GUIDriver.controlSoftware.stationControl.addInkToStation(inkAdded);
			MainScreen.inkLabel.setText("Ink Level: " + MainScreen.inkLevel);
			
		}
		
	}
	
	private class AddPaper implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int paperAdded = 10;
			MainScreen.paperLevel += paperAdded;
			paperStateLbl.setText("Paper state: " + MainScreen.paperLevel);
			GUIDriver.controlSoftware.stationControl.addInkToStation(paperAdded);
			MainScreen.paperLabel.setText("Paper Level: " + MainScreen.paperLevel);
		}
		
	}
	
	private class ShutdownStation implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			GUIDriver.goToScreen("shutdown");
		}
		
	}
}
