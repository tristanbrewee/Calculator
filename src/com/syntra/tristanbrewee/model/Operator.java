package com.syntra.tristanbrewee.model;

import java.util.Random;

/**
 * An Enum with the possible operators (ADD, SUBTRACT, MULTIPLY, DIVIDE, POWER, MODULUS)
 */
public enum Operator {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    POWER,
    MODULUS;

    /**
     * @param simpleOperator - A String
     * @return returns ADD if simpleOperator equals "+", SUBTRACT is simpleOperator equals "-", MULTIPLY is simpleOperator equals "*", DIVIDE id simpleOperator equals "/", POWER is simpleOperator equals "^", or MODULUS if simpleOperator equals "%"
     */
    public static Operator getOperator(String simpleOperator){
        switch (simpleOperator){
            case "+":
                return Operator.ADD;
            case "-":
                return Operator.SUBTRACT;
            case "*":
                return Operator.MULTIPLY;
            case "/":
                return Operator.DIVIDE;
            case "^":
                return Operator.POWER;
            case "%":
                return Operator.MODULUS;
            default:
                System.out.println("Invalid Operator");
                return null;
        }
    }

    /**
     * @param random - A Random
     * @return returns a random Operator
     */
    public static Operator getRandomOperator(Random random){
        int randomInt = random.nextInt(6) + 1;
        switch (randomInt){
            case 1:
                return Operator.ADD;
            case 2:
                return Operator.SUBTRACT;
            case 3:
                return Operator.MULTIPLY;
            case 4:
                return Operator.DIVIDE;
            case 5:
                return Operator.POWER;
            case 6:
                return Operator.MODULUS;
            default:
                System.out.println("This shouldn't (and can't) be reached!");
                return null;
        }
    }

    /**
     * @return returns "+" if this equals ADD, "-" if this equals SUBTRACT, "*" if this equals MULTIPLY, "/" if this equals DIVIDE, "^" if this equals POWER, or "%" if this equals MODULUS
     */
    public String toString(){
        switch (this){
            case ADD:
                return "+";
            case SUBTRACT:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            case POWER:
                return "^";
            case MODULUS:
                return "%";
            default:
                System.out.println("This should (and can't) be reached!");
                return null;
        }
    }
}
