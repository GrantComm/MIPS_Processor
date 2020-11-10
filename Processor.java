import java.util.HashMap;
import java.util.Map; 

public class Processor {
    // Map that connects the register names to their values
    public static HashMap<String, Long> registers = new HashMap<String, Long>() {{
        put("$s0", 0L);
        put("$s1", 0L);
        put("$s2", 0L);
        put("$s3", 0L);
        put("$s4", 0L);
        put("$s5", 0L);
        put("$s6", 0L);
        put("$s7", 0L);
        put("$t0", 0L);
        put("$t1", 0L);
        put("$t2", 0L);
        put("$t3", 0L);
        put("$t4", 0L);
        put("$t5", 0L);
        put("$t6", 0L);
        put("$t7", 0L);
        put("$t8", 0L);
        put("$t9", 0L);
        put("$zero", 0L);
        put("$a0", 0L);
        put("$a1", 0L);
        put("$a2", 0L);
        put("$a3", 0L);
        put("$v0", 0L);
        put("$v1", 0L);
        put("$gp", 0L);
        put("$fp", 0L);
        put("$sp", 0L);
        put("$ra", 0L);
        put("$at", 0L);
    }};

    // Map that connects integers to the name of the register
    public static Map<Integer, String> registerNumbers = new HashMap<Integer, String>() {{
        put(0, "$zero");
        put(1, "$at");
        put(2, "$v0");
        put(3, "$v1");
        put(4, "$a0");
        put(5, "$a1");
        put(6, "$a2");
        put(7, "$a3");
        put(8, "$t0");
        put(9, "$t1");
        put(10, "$t2");
        put(11, "$t3");
        put(12, "$t4");
        put(13, "$t5");
        put(14, "$t6");
        put(15, "$t7");
        put(16, "$s0");
        put(17, "$s1");
        put(18, "$s2");
        put(19, "$s3");
        put(20, "$s4");
        put(21, "$s5");
        put(22, "$s6");
        put(23, "$s7");
        put(24, "$t8");
        put(25, "$t9");
        put(26, "$k0");
        put(27, "$k1");
        put(28, "$gp");
        put(29, "$sp");
        put(30, "$fp");
        put(31, "$ra");
    }};
    
    // Array of Memory
    public static Long[] memory = new Long[32];  

    /** Method that performs the addition function */
    public static String add(String dest, String src1, String src2) {
        registers.put(dest, registers.get(src1) + registers.get(src2));
        String binary = Converter.convertDecimalToBinary(registers.get(dest)); 
        return dest + " = " + Converter.convertBinaryToHex(binary); 
    }

    /** Method that performs the subtraction function */
    public static String sub(String dest, String src1, String src2) {
        registers.put(dest, registers.get(src1) - registers.get(src2));
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary); 
    }

    /** Method that performs the and function */
    public static String and(String dest, String src1, String src2) {
        registers.put(dest, registers.get(src1) & registers.get(src2));
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary); 
    }

    /** Method that performs the or function */
    public static String or(String dest, String src1, String src2) {
        registers.put(dest, registers.get(src1) | registers.get(src2));
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary); 
    }

    /** Method that performs the exclusive or function */
    public static String xor(String dest, String src1, String src2) {
        registers.put(dest, registers.get(src1) ^ registers.get(src2));
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary); 
    }

    /** Method that performs the jump function */
    public static String j(String dest, String src1, String src2) {
        return "Error! Not implemented yet!"; 
    }

    /** Method that performs the branch if equal function */
    public static String beq(String dest, String src1, String src2) {
        long val = registers.get(src1) == registers.get(src2) ? 1 : 0; 
        registers.put(dest, val);
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary);  
    }

    /** Method that performs the branch if not equal function */
    public static String bne(String dest, String src1, String src2) {
        long val = registers.get(src1) != registers.get(src2) ? 1 : 0;
        registers.put(dest, val);
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary);   
    }

    /** Method that performs the add immediate function */
    public static String addi(String dest, String src1, String src2) {
        registers.put(dest, registers.get(src1) + Long.parseLong(src2));
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary); 
    }

    /** Method that performs the load word function */
    public static String lw(String dest, String src1, String src2) {
        registers.put(dest, memory[(int)(registers.get(src1) + Long.parseLong(src2))]);
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return dest + " = " + Converter.convertBinaryToHex(binary);
    }

    /** Method that performs the store word function */
    public static String sw(String dest, String src1, String src2) {
        int address = (int)(registers.get(src1) + Long.parseLong(src2)); 
        memory[address] = registers.get(dest);
        String binary = Converter.convertDecimalToBinary(registers.get(dest));
        return "Memory" +"[" + address + "]" + " = " + Converter.convertBinaryToHex(binary); 
    }


}
