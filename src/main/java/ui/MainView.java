package ui;

import business.AuthType;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JButton addBook, addMember, checkOutBook, addBookCopy, printCheckoutRecord;
    private AuthType authType;

    MainView(AuthType authType) {
        JPanel mainPanel = new JPanel(new FlowLayout());
        addBook = new JButton("Add Book");
        addBookCopy = new JButton("Add Book Copy");
        addMember = new JButton("Add member");
        checkOutBook = new JButton("Checkout book");
        printCheckoutRecord = new JButton("Print checkout record");
        this.authType = authType;

        setInsets(addBook, addMember, checkOutBook, addBookCopy, printCheckoutRecord);
        mainPanel.add(addBook);
        mainPanel.add(addBookCopy);
        mainPanel.add(addMember);
        mainPanel.add(checkOutBook);
        mainPanel.add(printCheckoutRecord);

        switch (authType) {
            case LIBRARIAN: {
                addMember.setVisible(false);
                addBook.setVisible(false);
                addBookCopy.setVisible(false);
                break;
            }
            case ADMIN:
                checkOutBook.setVisible(false);
                printCheckoutRecord.setVisible(false);
                break;
        }

        getContentPane().add(mainPanel);
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GuiControl.SCREEN_WIDTH, GuiControl.SCREEN_HEIGHT);
        GuiControl.centerFrameOnDesktop(this);

        handle();
    }

    private void handle() {
        addMember.addActionListener(e -> {
            new AddMemberUIForm().setVisible(true);
        });

        addBook.addActionListener(e -> {
            new AddBookUIForm().setVisible(true);
        });

        checkOutBook.addActionListener(e -> {
            new CheckOutUIForm().setVisible(true);
        });

        addBookCopy.addActionListener(e -> {
            new AddBookCopyUIForm().setVisible(true);
        });

        printCheckoutRecord.addActionListener(e -> {
            new CheckoutRecordPrintUI().setVisible(true);
        });

    }

    private void setInsets(JButton... buttons) {
        for (JButton b : buttons) {
            b.setMargin(new Insets(5, 5, 5, 5));
        }
    }

}
