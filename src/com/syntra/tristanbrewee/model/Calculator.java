package com.syntra.tristanbrewee.model;

/**
 * A class responsible for solving calculations
 */
public class Calculator {

    /**
     * @param calculation - A calculation
     * @return the result of the Calculation (Double)
     * @throws ArithmeticException when there is a division by 0
     */
    public static double getResult(Calculation calculation) throws ArithmeticException{
        double result = 0;
        try{
            switch (calculation.getOperator()){
                case ADD:
                    result = add(calculation.getNumber1(), calculation.getNumber2());
                    break;
                case SUBTRACT:
                    result = subtract(calculation.getNumber1(), calculation.getNumber2());
                    break;
                case MULTIPLY:
                    result = multiply(calculation.getNumber1(), calculation.getNumber2());
                    break;
                case DIVIDE:
                    result = divide(calculation.getNumber1(), calculation.getNumber2());
                    break;
                case POWER:
                    result = power(calculation.getNumber1(), calculation.getNumber2());
                    break;
                case MODULUS:
                    result = modulus(calculation.getNumber1(), calculation.getNumber2());
                    break;
                default:
                    System.out.println("This shouldn't (and can't) be reached!");
                    break;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * @param number1 - A Double
     * @param number2 - A Double
     * @return returns the sum of the given params
     */
    private static double add(double number1, double number2){
        return number1 + number2;
    }

    /**
     * @param number1 - A Double
     * @param number2 - A Double
     * @return returns the result of number1 - number2
     */
    private static double subtract(double number1, double number2){
        return number1 - number2;
    }

    /**
     * @param number1 - A Double
     * @param number2 - A Double
     * @return returns the product op the given params
     */
    private static double multiply(double number1, double number2){
        return number1 * number2;
    }

    /**
     * @param number1 - A Double
     * @param number2 - A Double
     * @return returns the result of number1 / number2
     * @throws ArithmeticException when number2 = 0
     */
    private static double divide(double number1, double number2) throws ArithmeticException{
        if (number2 == 0.0)
            throw new ArithmeticException("Can't divide by 0!");
        else
            return number1 / number2;
    }

    /**
     * @param number1 - A Double
     * @param number2 - A Double
     * @return returns the result of number1 to the power of number2
     */
    private static double power(double number1, double number2){
        return Math.pow(number1, number2);
    }

    /**
     * @param number1 - A Double
     * @param number2 - A Double
     * @return returns the result of number1 modulus number2
     */
    private static double modulus(double number1, double number2){
        return number1%number2;
    }
}
