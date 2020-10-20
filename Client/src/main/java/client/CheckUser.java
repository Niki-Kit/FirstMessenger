package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CheckUser extends JFrame implements ActionListener {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;

    private final JTextField nameField = new JTextField();
    private final JTextField surnameField = new JTextField();
    private final JTextField passField = new JTextField();

    private String name;
    private String surname;
    private String pass;

    CheckUser() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        add(nameField, BorderLayout.NORTH);
        add(surnameField, BorderLayout.CENTER);
        add(passField, BorderLayout.SOUTH);

        nameField.setText(name);
        surnameField.setText(surname);
        passField.setText(pass);

        nameField.addActionListener(this);
        surnameField.addActionListener(this);
        passField.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InputWindows input = new InputWindows(nameField.getText(),surnameField.getText(),passField.getText());
        try {
            input.inputConnect();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }



}
