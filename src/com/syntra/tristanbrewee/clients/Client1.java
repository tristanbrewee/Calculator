package com.syntra.tristanbrewee.clients;

import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.CalculationListFileObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;

public class Client1 extends Application {

    private static final String FXML_PATH = "../javafx/fxmls/Client1Fxml.fxml";

    private static final String HOSTNAME = "localhost";
    private static final int PORT_NUMBER_CALCULATIONS = 9001;
    private static final int PORT_NUMBER_FILE_MANAGEMENT = 9002;
    private static final String OUTPUT_FILE_NAME = "client1Output.txt";

    private static Socket socket;
    private static ObjectInputStream fromServer;
    private static ObjectOutputStream toServer;

    private static HashSet<Calculation> calculations = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Client 1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void connectToServer(String hostname, int portNumber) {
        try {
            socket = new Socket(hostname, portNumber);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Calculation process(Calculation calculation) {
        try {
            toServer.writeObject(calculation);
            toServer.flush();
            calculation = (Calculation) fromServer.readObject();
            calculations.add(calculation);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            return calculation;
        }
    }

    public static boolean calculationsContainsCalculation(Calculation calculation) {
        return calculations.contains(calculation);
    }

    public static void safeToFile(){
        connectToServer(HOSTNAME, PORT_NUMBER_FILE_MANAGEMENT);
        CalculationListFileObject calculationListFileObject = new CalculationListFileObject(OUTPUT_FILE_NAME, calculations);
        try {
            toServer.writeObject(calculationListFileObject);
            toServer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static HashSet<Calculation> getCalculations() {
        return calculations;
    }

    public static String getHostname(){
        return HOSTNAME;
    }

    public static int getPortNumberCalculations(){
        return PORT_NUMBER_CALCULATIONS;
    }
}
