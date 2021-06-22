package com.syntra.tristanbrewee.serverutils;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.CalculationListFileObject;
import com.syntra.tristanbrewee.model.Operator;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * A Runnable task for handling a single client's file requests with as params:
 * socket (Socket)
 * fromClient (ObjectInputStream)
 * toClient (ObjectOutputStream)
 * OUTPUT_PATH_BASE (String) - path to the output directory
 * INPUT_PATH_BASE (String) - path to the resources directory
 */
public class HandleAClientFileManagement implements Runnable {

    private Socket socket;
    private ObjectInputStream fromClient;
    private ObjectOutputStream toClient;

    private static final String OUTPUT_PATH_BASE = "src/com/syntra/tristanbrewee/output/";
    private static final String INPUT_PATH_BASE = "src/com/syntra/tristanbrewee/resources/";

    /**
     * @param socket - A Socket
     * Creates a HandleAClientFileManagement object based on the given params
     */
    public HandleAClientFileManagement(Socket socket) {
        this.socket = socket;
    }

    /**
     * Starts the thread. First runs connect() and then handleTask()
     */
    @Override
    public void run() {
        connect();
        handleTask();
    }

    /**
     * Sets toClient with socket's OutputsStream, and fromClient with socket's InputStream
     */
    private void connect() {
        try {
            toClient = new ObjectOutputStream(socket.getOutputStream());
            fromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives a calculationListFileObject from fromClient.
     * Decides if there should be read a file, and if so runs readFromFile().
     * Else it runs writeToFile().
     */
    private void handleTask() {
        boolean isNotCompleted = true;
        while (isNotCompleted) {
            try {
                CalculationListFileObject calculationListFileObject = (CalculationListFileObject) fromClient.readObject();
                if (calculationListFileObject.isReadFromFile())
                    readFromFile(calculationListFileObject);
                else
                    writeToFile(calculationListFileObject);
                isNotCompleted = false;
            } catch (IOException e) {
                //Do nothing
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param calculationListFileObject - A CalculationListFileObject
     * Reads Calculations from the desired .txt-file.
     * Adds them to calculationListFileObject.
     * Sets calculationListFileObject.readFromFile to false.
     * Sends calculationListFileObject to toClient.
     */
    private void readFromFile(CalculationListFileObject calculationListFileObject){
        String path = INPUT_PATH_BASE + calculationListFileObject.getFileName();
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)){
            scanner.useDelimiter("\n");
            while (scanner.hasNext()){
                String line = scanner.next();
                line = line.replaceAll(",", ".");
                double number1, number2;
                Operator operator;
                number1 = Double.parseDouble(line.substring(0, line.indexOf(';')));
                line = line.substring((line.indexOf(';') + 1));
                number2 = Double.parseDouble(line.substring(0, line.indexOf(';')));
                operator = Operator.getOperator(line.substring(line.indexOf(';') + 1));

                Calculation calculation = new Calculation(number1, number2, operator.toString());
                calculationListFileObject.getCalculations().add(calculation);
            }
            calculationListFileObject.setReadFromFile(false);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            toClient.writeObject(calculationListFileObject);
            toClient.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param calculationListFileObject - A CalculationListFileObject
     * Writes calculationListFileObject.calculations to the desired .txt-file
     */
    private void writeToFile(CalculationListFileObject calculationListFileObject) {
        boolean isNotCompleted = true;
        while (isNotCompleted) {
            String filePath = OUTPUT_PATH_BASE + calculationListFileObject.getFileName();
            StringBuilder stringBuilder = new StringBuilder();
            calculationListFileObject.getCalculations()
                    .stream()
                    .sorted()
                    .forEachOrdered(e -> stringBuilder.append(e.toString()).append("\n"));
            String output = stringBuilder.toString();
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(output);
                fileWriter.close();
                isNotCompleted = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
