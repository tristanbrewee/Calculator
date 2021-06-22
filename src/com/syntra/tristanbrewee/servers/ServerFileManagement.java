package com.syntra.tristanbrewee.servers;

import com.syntra.tristanbrewee.serverutils.HandleAClientFileManagement;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server class responsible for Handling clients that want anything involving files
 */
public class ServerFileManagement {

    private static final int PORT_NUMBER = 9002;

    private static ServerSocket serverSocket;
    private static Socket socketClient;

    /**
     * @param args - Not important
     * Starts up the server
     */
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            while (true){
                socketClient = serverSocket.accept();
                new Thread(new HandleAClientFileManagement(socketClient)).start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
