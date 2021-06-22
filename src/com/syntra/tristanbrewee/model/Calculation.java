package com.syntra.tristanbrewee.model;

import java.io.Serializable;
import java.util.Random;

/**
 * A calculation object with as params:
 * number1 (Double)
 * number2 (Double)
 * result (Double)
 * operator (Operator)
 */
public class Calculation implements Serializable, Comparable<Calculation> {

    private double number1, number2, result;
    private Operator operator;

    //Constructors
    /**
     * @param number1 - The first number of the calculation (Double)
     * @param number2 - The second number of the calculation (Double)
     * @param operatorSimple - An Operator as String (expected "+", "-", "/", "*", "^")
     * Creates a Calculation object with the given params. The result will be default value (0.0).
     */
    public Calculation(double number1, double number2, String operatorSimple) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = Operator.getOperator(operatorSimple);
    }

    /**
     * @param random - A Random
     * Creates a Calculation object with random params.
     * number1 will be a Double between -5000.0 and 5000.0
     * number2 will be a Double between -10.0 and 10.0
     * operator will be a random operator (ADD, SUBTRACT, DIVIDE, MULTIPLY, POWER or MODULUS)
     * The result will be default value (0.0).
     */
    public Calculation(Random random){
        this.number1 = random.nextDouble() * 10000.0 - 5000.0;
        this.operator = Operator.getRandomOperator(random);
        if (operator.equals(Operator.POWER))
            number2 = random.nextInt(20) - 10;
        else
            this.number2 = random.nextDouble() * 20.0 - 10.0;
    }

    //Overrides
    /**
     * @return The object as a String.
     * number1 with 2 decimal points, a space, operator as String, a space, number2 with 2 decimal points, a space, "=", a space, the result with 2 decimal points
     */
    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder();
        resultString.append(String.format("%.2f", number1))
                .append(" ")
                .append(operator.toString())
                .append(" ")
                .append(String.format("%.2f", number2))
                .append(" = ")
                .append(String.format("%.2f", result));
        return resultString.toString();
    }

    /**
     * @param e - A Calculation object
     * @return true if this toString() equals e's toString()
     */
    @Override
    public int compareTo(Calculation e){
        return this.toString().compareToIgnoreCase(e.toString());
    }

    /**
     * @param o - An object
     * @return returns true if e is a Calculation objects AND if this hashcode() equals e's hashcode()
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Calculation){
            if (this.hashCode() == o.hashCode())
                return true;
        }
        return false;
    }

    /**
     * @return returns a hashcode (Integer) based on number1 (with 2 decimal points), number2 (with 2 decimal points), and operator
     */
    @Override
    public int hashCode(){
        StringBuilder result = new StringBuilder();
        int operatorNumber;
        switch (operator){
            case ADD:
                operatorNumber = 1;
                break;
            case SUBTRACT:
                operatorNumber = 2;
                break;
            case MULTIPLY:
                operatorNumber = 3;
                break;
            case DIVIDE:
                operatorNumber = 4;
                break;
            case POWER:
                operatorNumber = 5;
                break;
            default:
                operatorNumber = 0;
                break;
        }
        int number1AsInt = (int)Math.floor(number1 * 100);
        int number2AsInt = (int)Math.floor(number2 * 100);
        String negatives = "";
        if (number2AsInt < 0){
            negatives = "1";
            number2AsInt = Math.abs(number2AsInt);
        }
        result.append(number1AsInt)
                .append(operatorNumber)
                .append(number2AsInt)
                .append(negatives);
        Long hashcodeAsLong = Long.parseLong(result.toString());
        return hashcodeAsLong.hashCode();
    }

    //Setters
    /**
     * @param result - A Double
     * Sets result with the given param
     */
    public void setResult(double result) {
        this.result = result;
    }

    //Getters
    /**
     * @return returns number1 as Double
     */
    public double getNumber1() {
        return number1;
    }

    /**
     * @return returns number2 as Double
     */
    public double getNumber2() {
        return number2;
    }

    /**
     * @return returns result as Double
     */
    public double getResult() {
        return result;
    }

    /**
     * @return returns operator
     */
    public Operator getOperator() {
        return operator;
    }
}
