import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    // Map of the different functions for R-Format  
    static Map<Integer, String> rFunctions = new HashMap<Integer, String>(){{
        put(32, "add");
        put(34, "sub");
        put(41, "and"); 
        put(37, "or");
        put(38, "xor");
    }};

    // Map of the different functions for I-Format
    static Map<Integer, String> iFunctions = new HashMap<Integer, String>(){{
        put(2, "j");
        put(4, "beq");
        put(5, "bne");
        put(8, "addi");
        put(35, "lw");
        put(43, "sw"); 
    }};

    /** Method that checks the type of instruction R or I */
    public static char getType(String instruction) {
        String opCode = instruction.substring(0, 6);
        if (opCode.equals("000000")) {
            return 'R'; 
        }
        return 'I'; 
    }

    /** Method that appends the currect number of 0s to the end of the binary string */
    public static String correctOpCode(String instruction) {
        if (instruction.length() == 32) {
            return instruction; 
        }
        int difference = 32 - instruction.length(); 
        StringBuilder result = new StringBuilder(instruction); 
        for (int i = 0; i < difference; i++) {
            result.insert(0, '0'); 
        }
        return result.toString(); 
    }

    /** Method that parses the binary string and returns an array list of the instruction details*/
    private static ArrayList<String> parse(String instruction, char format) {
        ArrayList<String> results = new ArrayList<String>(); 
        String destRegister = ""; 
        String srcRegister1 = ""; 
        String srcRegister2 = "";
        String functionCode = "";  
        if (format != 'R') {
            srcRegister1 = Converter.convertBinaryToDecimal(instruction.substring(6, 11));
            destRegister = Converter.convertBinaryToDecimal(instruction.substring(11, 16));
            srcRegister2 = Converter.convertBinaryToDecimal(instruction.substring(16, 32)); 
            functionCode = Converter.convertBinaryToDecimal(instruction.substring(0,6));
            results.add(iFunctions.get(Integer.parseInt(functionCode)));
            results.add(Processor.registerNumbers.get(Integer.parseInt(destRegister)));
            results.add(Processor.registerNumbers.get(Integer.parseInt(srcRegister1)));
            results.add(srcRegister2); 
            return results; 
        }
        srcRegister1 = Converter.convertBinaryToDecimal(instruction.substring(6, 11));
        srcRegister2 = Converter.convertBinaryToDecimal(instruction.substring(11, 16));
        destRegister = Converter.convertBinaryToDecimal(instruction.substring(16, 21)); 
        functionCode = Converter.convertBinaryToDecimal(instruction.substring(26, 32));
        results.add(rFunctions.get(Integer.parseInt(functionCode)));
        results.add(Processor.registerNumbers.get(Integer.parseInt(destRegister)));
        results.add(Processor.registerNumbers.get(Integer.parseInt(srcRegister1)));
        results.add(Processor.registerNumbers.get(Integer.parseInt(srcRegister2))); 
        return results; 
    }

    /** Method that formats the string instruction */
    public static String formatter(String instruction, char format) {
        ArrayList<String> arr = parse(instruction, format); 
        if (format != 'R') {
            return arr.get(0) + " " + arr.get(1) + ", " + arr.get(2) + ", " + arr.get(3); 
        }
        return arr.get(0) + " " + arr.get(1) + ", " + arr.get(2) + ", " + arr.get(3); 
    }

    /** Method that performs the operation */
    public static String operator(String instruction, char format) {
        ArrayList<String> arr = parse(instruction, format); 
        if (arr.get(0) == "add"){
            return Processor.add(arr.get(1), arr.get(2), arr.get(3)); 
        }
        if (arr.get(0) == "sub"){
            return Processor.add(arr.get(1), arr.get(2), arr.get(3));
        }
        if (arr.get(0) == "and"){
            return Processor.and(arr.get(1), arr.get(2), arr.get(3));
        }
        if (arr.get(0) == "or"){
            return Processor.or(arr.get(1), arr.get(2), arr.get(3));
        }
        if (arr.get(0) == "xor"){
            return Processor.xor(arr.get(1), arr.get(2), arr.get(3));
        }
        if (arr.get(0) == "j"){}
        if (arr.get(0) == "beq"){}
        if (arr.get(0) == "bne"){}
        if (arr.get(0) == "addi"){
            return Processor.addi(arr.get(1), arr.get(2), arr.get(3));
        }
        if (arr.get(0) == "lw"){
            return Processor.lw(arr.get(1), arr.get(2), arr.get(3));
        }
        if (arr.get(0) == "sw"){
            return Processor.sw(arr.get(1), arr.get(2), arr.get(3));
        }
        return "Internal Error!";  
    }
}
