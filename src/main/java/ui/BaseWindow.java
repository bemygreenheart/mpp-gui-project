package ui;

import javax.swing.*;

public abstract class BaseWindow extends JFrame {
	public abstract void init();
	public abstract boolean isInitialized();
	public abstract void isInitialized(boolean val);

	public void showWindow(BaseWindow window) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(!window.isInitialized()) {
					window.init();
					window.setVisible(true);
				}
			}
		});
	}
}
