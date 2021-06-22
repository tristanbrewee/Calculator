package com.syntra.tristanbrewee.serverutils;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.Calculator;

import java.io.*;
import java.net.Socket;

/**
 * A Runnable task for handling a single client's Calculations with as params:
 * socket (Socket)
 * fromClient (ObjectInputStream)
 * toClient (ObjectOutputStream)
 */
public class HandleAClientCalculation implements Runnable{

    private Socket socket;
    private ObjectInputStream fromClient;
    private ObjectOutputStream toClient;

    /**
     * @param socket - A Socket
     * Creates a HandleAClientCalculation object based on the given params
     */
    public HandleAClientCalculation(Socket socket) {
        this.socket = socket;
    }

    /**
     * Starts the thread. First runs connect() and then process()
     */
    @Override
    public void run(){
        connect();
        process();
    }

    /**
     * Sets toClient with socket's OutputsStream, and fromClient with socket's InputStream
     */
    private void connect(){
        try{
            toClient = new ObjectOutputStream(socket.getOutputStream());
            fromClient = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Receives Calculation's from fromClient, fills in the result, and sends them to toClient
     */
    private void process(){
        while (true){
            try {
                Calculation calculation = (Calculation) fromClient.readObject();
                calculation.setResult(Calculator.getResult(calculation));
                toClient.writeObject(calculation);
                toClient.flush();
            }
            catch (IOException e){
                //Do nothing
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
