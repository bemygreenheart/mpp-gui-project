package ui;

import javax.swing.*;

public class Start {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!LoginWindow.INSTANCE.isInitialized()) {
					LoginWindow addressFormWindow = LoginWindow.INSTANCE;
					addressFormWindow.setVisible(true);
				}

			}
		});
	}
	
}
