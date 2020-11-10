import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger; 

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        Long[] memory = Processor.memory;
        HashMap<String, Long> registers = Processor.registers; 
        do {
            System.out.println();
            showRegisters(registers);
            System.out.println();
            showMemory(memory);
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println("Please Enter a Hex Instruction");
            System.out.println("-------------------------------");
            System.out.print("Instruction: ");
            String hexInstruction= Converter.convertHexToBinary(scanner.next());
            String instruction = Controller.correctOpCode(hexInstruction);
            char instructionFormat = Controller.getType(instruction);  
            System.out.println("Instruction Format: " + instructionFormat);
            System.out.println("Instruction: " + Controller.formatter(instruction, instructionFormat));
            System.out.println("After Instruction: " + Controller.operator(instruction, instructionFormat));
            System.out.println("Would you like to perform another opperation? (y or n)"); 
            answer = scanner.next(); 
        } while (!answer.equals("n")); 
        System.out.println("Goodbye!"); 
    }

    /** Method used to print out the memory */
    public static void showMemory(Long[] memory) {
        System.out.println("MEMORY:");
        for(int i = 0; i < memory.length; i++) {
            if (i % 6 == 0 && i != 0) {
                System.out.println(); 
            }
            if (i == memory.length - 1) {
                System.out.print("Memory" +"[" + i + "]" + " = " + memory[i]);
            }else{
                System.out.print("Memory" +"[" + i + "]" + " = " + memory[i] + ", ");
            }
        }
    }

    /** Method used to print out all of the registers */
    public static void showRegisters(HashMap<String, Long> registers) {
        AtomicInteger counter = new AtomicInteger(0);
        System.out.println("REGISTERS:");
        registers.entrySet().forEach(entry->{ 
            if (counter.get() % 11 == 0 && counter.get() != 0) {
                System.out.println(); 
            }
            if (counter.get() == registers.size() - 1) {
                System.out.print(entry.getKey() + " = " + entry.getValue());
            } else {
                System.out.print(entry.getKey() + " = " + entry.getValue() + ", ");
            }
            counter.getAndIncrement(); 
        });
        System.out.println();
    }
}