package client;


import network.TCPConnection;
import network.TCPConnectionListener;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


@Component
public class Client extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IP_ADDRESS = "Your ip";
    private static final int PORT = 8280;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private final JTextArea log = new JTextArea();
    private final JTextField nickName = new JTextField("Nik");
    private final JTextField input = new JTextField();

    private TCPConnection connection;

    protected Client() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        log.setEditable(false);
        log.setLineWrap(true);
        input.addActionListener(this);

        add(log, BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
        add(nickName, BorderLayout.NORTH);

        try {
            connection = new TCPConnection(this, IP_ADDRESS,PORT);
        } catch (IOException e) {
            printMassage("Connection exception: " + e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ms = input.getText();
        if(ms.equals("")) return;
        input.setText(null);
        connection.sendString(nickName.getText() + ": " + ms );
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMassage("Connection ready..=)");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMassage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMassage("Connection close...=(");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMassage("Connection exception: " + e);
    }

    private synchronized void  printMassage(String ms) {
        SwingUtilities.invokeLater(() -> {
            log.append(ms + "\n");
            log.setCaretPosition(log.getDocument().getLength());
        });
    }
}
