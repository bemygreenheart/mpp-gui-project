package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataaccess.User;
import dataaccess.UserHolder;

import javax.swing.*;


public class MainMenuWindow extends BaseWindow {
	public static final MainMenuWindow INSTANCE = new MainMenuWindow();

	private JButton backBt;
	private JButton addBookBt;

	private JButton addBookCopyBt;

	private JButton addMemberBt;

	private JButton editMemberBt;

	private JButton checkoutBookBt;

	private JButton printCheckoutRecordBt;

	private JButton checkOutOverdueCopiesBt;

	private JLabel userNameLabel;
	
	private boolean isInitialized = false;
	
	@Override
	public void init() {
		UserHolder userHolder = UserHolder.getInstance();
		User loggedInUser = userHolder.getUser();

		String userType = switch (loggedInUser.getAuthType()) {
			case LIBRARIAN -> "Librarian";
			case ADMIN -> "Administrator";
			case BOTH -> "Administrator and Librarian";
		};

		setTitle("Welcome " + userType);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		setResizable(false);

		makeMainView();
		initialize();
		setupButtonActions();
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}


	private Component makeMainView() {
		JPanel panel = new JPanel();
//		panel.setBackground(new Color(205, 216, 173));
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);

		JPanel topView = makeTopView();
		panel.add(topView);
		layout.putConstraint(SpringLayout.WEST, topView, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, topView, -20, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.NORTH, topView, 12, SpringLayout.NORTH, panel);

		JPanel bottomView = makeBottomView();
		panel.add(bottomView);
		layout.putConstraint(SpringLayout.WEST, bottomView, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, bottomView, -20, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.NORTH, bottomView, 24, SpringLayout.SOUTH, topView);

		return getContentPane().add(panel);
	}

	private JPanel makeTopView() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		backBt = new JButton("<Back");
		panel.add(backBt, BorderLayout.WEST);

		userNameLabel= new JLabel("Hello");
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(userNameLabel, BorderLayout.EAST);

		return panel;
	}

	private JPanel makeButtonsView() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));

		addBookBt = new JButton("Add Book");
		addBookCopyBt = new JButton("Add Book Copy");
		addMemberBt = new JButton("Add Member");
		editMemberBt = new JButton("Edit Member");
		checkoutBookBt = new JButton("Checkout Book");
		printCheckoutRecordBt = new JButton("Print Checkouts");

		panel.add(addBookBt);
		panel.add(addBookCopyBt);
		panel.add(addMemberBt);
		panel.add(editMemberBt);
		panel.add(checkoutBookBt);
		panel.add(printCheckoutRecordBt);

		return panel;
	}

	private JPanel makeBottomView() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel buttons = makeButtonsView();
		panel.add(buttons, BorderLayout.NORTH);

		checkOutOverdueCopiesBt = new JButton("Checkout Overdue Copies");
		panel.add(checkOutOverdueCopiesBt, BorderLayout.SOUTH);

		return panel;
	}


	private void initialize() {
		UserHolder userHolder = UserHolder.getInstance();
		User loggedInUser = userHolder.getUser();
		userNameLabel.setText("Hello " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

		switch (loggedInUser.getAuthType()) {
			case LIBRARIAN:
				addBookBt.setVisible(false);
				addBookCopyBt.setVisible(false);
				addMemberBt.setVisible(false);
				editMemberBt.setVisible(false);
				break;
			case ADMIN:
				printCheckoutRecordBt.setVisible(false);
				checkoutBookBt.setVisible(false);
				checkOutOverdueCopiesBt.setVisible(false);
				break;
			case BOTH:
				break;
		}
	}

	private void setupButtonActions() {
		backBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onBackBtClick();
			}
		});

		addBookBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAddBookBtClick();
			}
		});

		addBookCopyBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAddBookCopyBtClick();
			}
		});

		addMemberBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAddMemberBtClick();
			}
		});

		editMemberBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onEditMemberBtClick();
			}
		});

		checkoutBookBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCheckoutBookBtClick();
			}
		});

		printCheckoutRecordBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onPrintCheckoutRecordBtClick();
			}
		});

		checkOutOverdueCopiesBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCheckOutOverdueCopiesBtClick();
			}
		});
	}

	private void onBackBtClick() {
		dispose();
	}

	private void onAddBookBtClick() {
//		Start.hideAllWindows();
//		BookWindowController.INSTANCE.init();
//		BookWindowController.INSTANCE.show();
	}

	private void onAddBookCopyBtClick() {
		// Show the error message.
//		Start.hideAllWindows();
//		BookCopyWindow.INSTANCE.init();
//		BookCopyWindow.INSTANCE.show();
	}

	private void onAddMemberBtClick() {
//		Start.hideAllWindows();
//		AddMemberWindow.INSTANCE.init();
//		AddMemberWindow.INSTANCE.show();
	}

	private void onEditMemberBtClick() {
//		Start.hideAllWindows();
//		EditMemberWindow.INSTANCE.init();
//		EditMemberWindow.INSTANCE.show();
	}

	private void onCheckoutBookBtClick() {
		SetStageForFXMLFile("CheckOutRecord.fxml");
	}

	private void onPrintCheckoutRecordBtClick() {
		SetStageForFXMLFile("CheckoutRecordPrint.fxml");
	}

	private void onCheckOutOverdueCopiesBtClick() {
		SetStageForFXMLFile("OverdueBookCopies.fxml");
	}

	private void SetStageForFXMLFile(String fileName) {
//		Start.hideAllWindows();

		try {

//			Stage primaryStage = getStage();
//			Parent root = FXMLLoader.load(getClass().getResource(fileName));
//			Scene scene = new Scene(root, 1000, 800);
//			scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Checkout Record");
//			primaryStage.show();
//
//			primaryStage.setOnCloseRequest(event -> {
//				MainMenuWindow.INSTANCE.show();
//			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
