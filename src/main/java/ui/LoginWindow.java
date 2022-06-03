package ui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.User;
import dataaccess.UserHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginWindow extends JFrame {

    public static final LoginWindow INSTANCE = new LoginWindow();

    private boolean isInitialized = false;

    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private JTextArea messageBar = new JTextArea();
    public void clear() {
        messageBar.setText("");
    }

    private LoginWindow () {
        windowInitializer();
        mainPanel();
    }

    private void windowInitializer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Window");
        setSize(400,300);
        setResizable(false);
    }

    private Component mainPanel() {
        int hPadding = 100;
        int vPadding = 48;

        JPanel panel = new JPanel();
        panel.setBackground(new Color(205, 216, 173));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
        panel.add(loginLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginLabel, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, loginLabel, vPadding, SpringLayout.NORTH, panel);

        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setForeground(new Color(50, 33, 231));
        panel.add(userNameLabel);
        layout.putConstraint(SpringLayout.WEST, userNameLabel, hPadding, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userNameLabel, 16, SpringLayout.SOUTH, loginLabel);

        JTextField userNameField = new JTextField(12);
        panel.add(userNameField);
        layout.putConstraint(SpringLayout.WEST, userNameField, 4, SpringLayout.EAST, userNameLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, userNameField, 0, SpringLayout.VERTICAL_CENTER, userNameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(50, 33, 231));
        panel.add(passwordLabel);
        layout.putConstraint(SpringLayout.WEST, passwordLabel, hPadding, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 8, SpringLayout.SOUTH, userNameLabel);

        JPasswordField passwordField = new JPasswordField(12);
        panel.add(passwordField);
        layout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, userNameField);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, 0, SpringLayout.VERTICAL_CENTER, passwordLabel);

        JButton loginButton = new JButton("Log In");
        panel.add(loginButton);
        layout.putConstraint(SpringLayout.EAST, loginButton, 0, SpringLayout.EAST, passwordField);
        layout.putConstraint(SpringLayout.NORTH, loginButton, 16, SpringLayout.SOUTH, passwordLabel);

        JButton backBtn = new JButton("Cancel");
        panel.add(backBtn);
        layout.putConstraint(SpringLayout.WEST, backBtn, hPadding - 8, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, backBtn, 16, SpringLayout.SOUTH, passwordLabel);

        messageBar.setEditable(false);
        messageBar.setLineWrap(true);
        messageBar.setOpaque(false);
        messageBar.setBorder(BorderFactory.createEmptyBorder());
        messageBar.setForeground(Color.RED);
        panel.add(messageBar);
        layout.putConstraint(SpringLayout.WEST, messageBar, 0, SpringLayout.WEST, passwordLabel);
        layout.putConstraint(SpringLayout.EAST, messageBar, 0, SpringLayout.EAST, passwordField);
        layout.putConstraint(SpringLayout.NORTH, messageBar, 12, SpringLayout.SOUTH, loginButton);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControllerInterface c = new SystemController();
                    String errorMessage = "";
                    if ((userNameField.getText() == null || userNameField.getText().strip().length() == 0) &&
                            (passwordField.getText() == null || passwordField.getText().strip().length() == 0)) {
                        errorMessage += "Please enter the username and password.\n";
                    } else if (userNameField.getText() == null || userNameField.getText().strip().length() == 0) {
                        errorMessage += "Please enter the username.\n";
                    } else if (passwordField.getText() == null || passwordField.getText().strip().length() == 0) {
                        errorMessage += "Please enter the password.\n";
                    } else {
                        User loggedInUser = c.login(userNameField.getText().trim(), passwordField.getText().trim());
                        UserHolder userHolder = UserHolder.getInstance();
                        userHolder.setUser(loggedInUser);

                        errorMessage = "Success";
                        messageBar.setForeground(Color.GREEN);

//                        Start.hideAllWindows();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if(!MainMenuWindow.INSTANCE.isInitialized()) {
                                    MainMenuWindow.INSTANCE.init();
                                    MainMenuWindow mainMenuWindow = MainMenuWindow.INSTANCE;
                                    mainMenuWindow.setVisible(true);
                                }
                            }
                        });
                    }
                    messageBar.setText(errorMessage);

                } catch(LoginException ex) {
                    messageBar.setText(ex.getMessage());
                }
            }
        });

        return getContentPane().add(panel);
    }
}