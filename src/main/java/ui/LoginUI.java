package ui;


import Contoller.SystemController;
import Entity.Role;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    JPanel mainPanel;
    JButton submit;
    JTextField passwordField, idField;

    public LoginUI() {
        setTitle("Welcome");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setSize(GuiControl.SCREEN_WIDTH, GuiControl.SCREEN_HEIGHT);
        GuiControl.centerFrameOnDesktop(this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Welcome
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome");
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
        submit = new JButton("Submit");
        bottomPanel.add(submit);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);

        handle();
    }

    void handle() {
        submit.addActionListener(e -> {
            String password = passwordField.getText();
            String id = idField.getText();
            int integerId;
            try {
                integerId = Integer.parseInt(id);
                if (!(password.isEmpty() && id.isEmpty()))
                    SystemController.getInstance().login(integerId, password, LoginUI.this);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "Invalid id");
            }
        });
    }


    public void displayUI(Role role) {
        MainView mainView = new MainView(role);
        mainView.setVisible(true);
    }
    public void displayLoginError() {
        JOptionPane.showMessageDialog(this, "Invalid login credentials");
    }
}
