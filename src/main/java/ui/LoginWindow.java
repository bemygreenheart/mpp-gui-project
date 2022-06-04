package ui;


import business.AuthType;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    JPanel mainPanel;
    JButton loginButton;
    JTextField passwordField, idField;

    public LoginWindow() {
        setTitle("Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setSize(GuiControl.SCREEN_WIDTH, GuiControl.SCREEN_HEIGHT);
        GuiControl.centerFrameOnDesktop(this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Welcome
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Login");
        topPanel.add(welcomeLabel);

        //id and password
        JPanel middlePanel = new JPanel(new BorderLayout());
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel idLabel = new JLabel("User ID");
        idField = new JTextField("Enter id", 20);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField("Enter password", 20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        middlePanel.add(idPanel, BorderLayout.NORTH);
        middlePanel.add(passwordPanel, BorderLayout.CENTER);

        //Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        bottomPanel.add(loginButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);

        handle();
    }

    void handle() {
        loginButton.addActionListener(e -> {
            String password = passwordField.getText();
            String id = idField.getText();
            try {
                if (!(password.isEmpty() && id.isEmpty()))
                    SystemController.getInstance().login(id, password, LoginWindow.this);

            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "Invalid id");
            }
        });
    }


    public void displayUI(AuthType authType) {
        MainView mainView = new MainView(authType);
        mainView.setVisible(true);
    }
    public void displayLoginError() {
        JOptionPane.showMessageDialog(this, "Invalid login credentials");
    }
}
