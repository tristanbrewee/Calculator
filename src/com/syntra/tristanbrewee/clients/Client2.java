package com.syntra.tristanbrewee.clients;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.CalculationListFileObject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;

public class Client2 {

    private static final String HOSTNAME = "localhost";
    private static final int PORT_NUMBER_CALCULATIONS = 9001;
    private static final int PORT_NUMBER_FILE_MANAGEMENT = 9002;
    private static final String FILE_TO_READ_FROM = "input.txt";
    private static final String FILE_TO_WRITE_TO = "client2Output.txt";

    private static Socket socket;
    private static ObjectInputStream fromServer;
    private static ObjectOutputStream toServer;

    private static HashSet<Calculation> calculations = new HashSet<>();

    public static void main(String[] args) {
        connectToServer(HOSTNAME, PORT_NUMBER_FILE_MANAGEMENT);
        CalculationListFileObject calculationListFileObject = new CalculationListFileObject(FILE_TO_READ_FROM, calculations);
        calculationListFileObject.setReadFromFile(true);
        calculationListFileObject = readFromServer(calculationListFileObject);
        connectToServer(HOSTNAME, PORT_NUMBER_CALCULATIONS);
        process(calculationListFileObject);
        connectToServer(HOSTNAME, PORT_NUMBER_FILE_MANAGEMENT);
        calculationListFileObject.setFileName(FILE_TO_WRITE_TO);
        writeToServer(calculationListFileObject);
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

    private static CalculationListFileObject readFromServer(CalculationListFileObject calculationListFileObject){
        try{
            toServer.writeObject(calculationListFileObject);
            toServer.flush();
            calculationListFileObject = (CalculationListFileObject) fromServer.readObject();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            return calculationListFileObject;
        }
    }

    private static void process(CalculationListFileObject calculationListFileObject){
        for (Calculation calculation: calculationListFileObject.getCalculations()) {

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
        calculationListFileObject.setCalculations(calculations);
    }

    private static void writeToServer(CalculationListFileObject calculationListFileObject){
        try{
            toServer.writeObject(calculationListFileObject);
            toServer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
