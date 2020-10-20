package server;


import network.TCPConnection;
import network.TCPConnectionListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
@Component
public class Server implements TCPConnectionListener {
    public static void main(String[] args) {
        new Server();

    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    protected Server() {
        System.out.println("Server started....");
        try(ServerSocket socket = new ServerSocket(8280)) {
            while(true) {
                try {
                    new TCPConnection(this, socket.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendAllUsers("User connection: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendAllUsers(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendAllUsers("User disconnection: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendAllUsers(String value) {
        System.out.println(value);
        connections.forEach(i -> i.sendString(value));
    }
}
