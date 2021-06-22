package com.syntra.tristanbrewee.servers;

import com.syntra.tristanbrewee.serverutils.HandleAClientCalculation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server class responsible for handling clients that want Calculations solved
 */
public class ServerCalculations {

    private static final int PORT_NUMBER = 9001;

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
                new Thread(new HandleAClientCalculation(socketClient)).start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
