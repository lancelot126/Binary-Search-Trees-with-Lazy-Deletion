import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Need to run program with two arguments");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];
        LazyBinarySearchTree tree = new LazyBinarySearchTree();

        try (Scanner input = new Scanner(new File(inputFileName));
             PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    if (line.contains(":")) {
                        String[] parts = line.split(":");
                        if (parts.length < 2) {
                            output.println("Error in Line: " + line);
                            continue;
                        }

                        String command = parts[0];
                        int value;

                        try {
                            value = Integer.parseInt(parts[1]);
                        } catch (NumberFormatException e) {
                            output.println("Error in Line: " + line);
                            continue;
                        }

                        if (command.equals("Insert")) {
                            output.println(tree.insert(value) ? "True" : "False");
                        } else if (command.equals("Delete")) {
                            output.println(tree.delete(value) ? "True" : "False");
                        } else if (command.equals("Contains")) {
                            output.println(tree.contains(value) ? "True" : "False");
                        } else {
                            output.println("Error in Line: " + line);
                        }
                    } else {
                        if (line.equals("PrintTree")) {
                            output.println(tree.toString());
                        } else if (line.equals("FindMin")) {
                            output.println(tree.findMin());
                        } else if (line.equals("FindMax")) {
                            output.println(tree.findMax());
                        } else if (line.equals("Height")) {
                            output.println(tree.height());
                        } else if (line.equals("Size")) {
                            output.println(tree.size());
                        } else {
                            output.println("Error in Line: " + line);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    String command = line.split(":")[0].toLowerCase();
                    output.println("Error in " + command + ": IllegalArgumentException raised");
                }
            }
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }
}