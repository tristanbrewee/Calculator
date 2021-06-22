package com.syntra.tristanbrewee.clients;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.CalculationListFileObject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Random;

public class Client3 {

    private static final String HOSTNAME = "localhost";
    private static final int PORT_NUMBER_CALCULATIONS = 9001;
    private static final int PORT_NUMBER_FILE_MANAGEMENT = 9002;
    private static final String OUTPUT_FILE_NAME = "client3Output.txt";

    private static Socket socket;
    private static ObjectInputStream fromServer;
    private static ObjectOutputStream toServer;

    private static final Random random = new Random();
    private static HashSet<Calculation> calculations = new HashSet<>();

    public static void main(String[] args) {
        connectToServer(HOSTNAME, PORT_NUMBER_CALCULATIONS);
        process();
        connectToServer(HOSTNAME, PORT_NUMBER_FILE_MANAGEMENT);
        safeToFile();
    }

    private static void connectToServer(String hostname, int portNumber){
        try {
            socket = new Socket(hostname, portNumber);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void process(){
        for (int i = 0; i < 250; i++){
            Calculation calculation = new Calculation(random);
            while (calculationsContainsCalculation(calculation)){
                calculation = new Calculation(random);
            }
            try {
                toServer.writeObject(calculation);
                toServer.flush();
                calculation = (Calculation)fromServer.readObject();
                calculations.add(calculation);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private static void safeToFile(){
        CalculationListFileObject calculationListFileObject = new CalculationListFileObject(OUTPUT_FILE_NAME, calculations);
        try {
            toServer.writeObject(calculationListFileObject);
            toServer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean calculationsContainsCalculation(Calculation calculation){
        return calculations.contains(calculation);
    }
}
