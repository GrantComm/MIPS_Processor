import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class Converter {

    /** Method that converts a decimal number to a binary string. */ 
    public static String convertDecimalToBinary(long decimalNumber) {
        /* First check to see if the decimal number to be converted is valid. 
        The largest decimal number that can be represented by 32 bits 
        is 4294967295. 
        */ 
        String result = ""; 
        if (decimalNumber >= 4294967295L) {
            return "The number you entered is too big"; 
        }
        
        if (decimalNumber == 0) {
            return "0000";
        }
        /* Divide the number by two until the quitient is 0. The remainders are 
        always 1's and 0's corresponding to digits in the binary string*/ 
        while (decimalNumber != 0) {
            String binaryDigit = Long.toString(decimalNumber % 2L);
            result += binaryDigit; 
            decimalNumber /= 2L; 
        }
        /* The result needs to be reversed because the string of remainders
        is supposed to be read right to left*/ 
        return new StringBuilder(result).reverse().toString(); 
    }

    /** Method that converts a binary string to a decimal number. */ 
    public static String convertBinaryToDecimal(String binaryString) {
        int len = binaryString.length();
        int result = 0;
        // Check if the input is eight bits 
        if (len > 32 || len == 0) {
            System.out.println("Invalid input");
            return "Invalid input"; 
        }

        for (int i = len - 1; i >= 0; i--) {
            char binaryDigit = binaryString.charAt(i); 
            if (binaryDigit != '1' && binaryDigit != '0'){
                System.out.println("Invalid input");
                return "Invalid input";  
            }
            /* For each digit in the binary string, multiply by 2 to the power of a number 
            depending on the digits place in the binary string. */
            result += Character.getNumericValue(binaryDigit) * (Math.pow(2, len - 1 - i)); 
        }

        return Integer.toString(result);
    }

    /** Method that returns a specific hex digit */
    public static String getHexDigits(long n) {
        if (n == 10) {
            return "A"; 
        } 
        else if (n == 11) {
            return "B";
        }
        else if (n == 12) {
            return "C";
        }
        else if (n == 13) {
            return "D";
        }
        else if (n == 14) {
            return "E";
        }
        else if (n == 15) {
            return "F";
        }
        return " "; 
    }

    /** Method that returns the value of a hex digit */
    public static long getHexValues(char digit) {
        if (digit == 'A') {
            return 10; 
        } else if (digit == 'B') {
            return 11; 
        } else if (digit == 'C') {
            return 12; 
        } else if (digit == 'D') {
            return 13; 
        } else if (digit == 'E') {
            return 14; 
        } else if (digit == 'F') {
            return 15; 
        }
        return 0; 
    }

    /** Method that converts a hexadecimal string to a binary string */
    public static String convertHexToBinary(String hexString) {
        int len = hexString.length();
        // List that contains the non numeric hex digits 
        ArrayList<Character> hexDigits = new ArrayList<Character>(
            Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'));  
        long decimalNumber = 0; 
        for (int i = len - 1; i >= 0; i--) {
            char hexDigit = hexString.charAt(i); 
            if (hexDigits.contains(hexString.charAt(i))) {
                decimalNumber += (Math.pow(16, len - 1 - i)) * getHexValues(hexDigit); 
            } else {
                decimalNumber += (Math.pow(16, len - 1 - i)) * Long.parseLong(String.valueOf(hexDigit)); 
            }
        }
        return Converter.convertDecimalToBinary(decimalNumber); 
    }

    /** Method that converts a binary string to hexadecimal form */
    public static String convertBinaryToHex(String binaryString) {
        String result = ""; 
        long decimalForm = Long.parseLong(Converter.convertBinaryToDecimal(binaryString));
        if (decimalForm == 0) {
            return "0"; 
        }
        while (decimalForm != 0) {
            long digit = decimalForm % 16; 
            if (digit > 9) {
                result += getHexDigits(digit); 
            } else {
                result += Long.toString(digit); 
            }
            decimalForm /= 16; 
        }
        return new StringBuilder(result).reverse().toString(); 
    }
}
