package com.syntra.tristanbrewee.generalutils;

/**
 * General utils used in the program.
 */
public class GeneralUtils {

    /**
     * @param numberAsString - The String to be checked.
     * @return Returns true if the String is a Double.
     */
    public static boolean isDouble(String numberAsString){
        try {
            Double.parseDouble(numberAsString);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * @param numberAsString - The String to be checked.
     * @return Returns true if the String is an Integer.
     */
    public static boolean isWholeNumber(String numberAsString){
        try{
            double number = Double.parseDouble(numberAsString);
            int numberAsInt = (int) Math.floor(number);
            if (number == numberAsInt)
                return true;
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * @param operator - The String to be checked.
     * @return Returns true if the String equals "^".
     */
    public static boolean isPowerOperator(String operator){
        return operator.equals("^");
    }
}
