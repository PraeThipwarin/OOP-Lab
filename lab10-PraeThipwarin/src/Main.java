import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void ReadFile(String fileName, String outputFile) {
        Path file = Paths.get(fileName);
        Charset charset = Charset.forName("UTF-8");

        StringBuilder results = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String textLine = null;
            while ((textLine = reader.readLine()) != null) {
                try {
                    String result = calculateExpression(validateExpression(textLine));
                    if (result != null) {
                        results.append(textLine).append(" = ").append(result).append("\n");
                        WriteFile(results.toString(), outputFile);
                    } else {
                        throw new NullPointerException("Invalid line: " + textLine);
                    }
                } catch (IllegalArgumentException | NullPointerException e) {
                    throw new IllegalArgumentException("Invalid line: " + textLine);
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Arithmetic error");
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static void WriteFile(String text,String fileName) {
        Path file = Paths.get(fileName);
        Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(text);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    static boolean Numeric(String str) {
        boolean numeric = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }

    static ArrayList<String> validateExpression(String expression) {
        String[] allArray = expression.split("");
        ArrayList<String> finalList = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        boolean hasNumBefore = false;

        for (String s : allArray) {
            if (Numeric(s) && !hasNumBefore) {
                number.append(s);
            } else if (s.equals(" ") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")) {
                if (!number.toString().isEmpty()) {
                    finalList.add(number.toString());
                    number = new StringBuilder();
                    hasNumBefore = true;
                }
                switch (s) {
                    case "+" -> {
                        finalList.add("+");
                        hasNumBefore = false;
                    }
                    case "-" -> {
                        finalList.add("-");
                        hasNumBefore = false;
                    }
                    case "*" -> {
                        finalList.add("*");
                        hasNumBefore = false;
                    }
                    case "/" -> {
                        finalList.add("/");
                        hasNumBefore = false;
                    }
                    case "%" -> {
                        finalList.add("%");
                        hasNumBefore = false;
                    }
                    case " " -> {}
                }
            } else if (!Numeric(s) && !number.toString().isEmpty()) {
                finalList.add(number.toString());
                number = new StringBuilder();
                hasNumBefore = true;
            } else {
                return null;
            }
        }

        if (!number.toString().isEmpty()) {
            finalList.add(number.toString());
        }

        return finalList;
    }

    static String calculateExpression(ArrayList<String> listOfProposition) {
        String finalResult = null;
        int result = 0;

        if (listOfProposition.isEmpty()) {
            return null;
        }

        Stack<String> queueForNumber = new Stack<>();
        Stack<String> queueForOperand = new Stack<>();


        for (String s : listOfProposition){
            if (Numeric(s)) {
                queueForNumber.addLast(s);
            } else {
                queueForOperand.addLast(s);
            }
        }
        try {
            for (String s : queueForOperand) {
                if (s.equals("+")) {
                    result = Integer.parseInt(queueForNumber.removeFirst());
                    result += Integer.parseInt(queueForNumber.removeFirst());
                    queueForNumber.addFirst(Integer.toString(result));
                } else if (s.equals("-")) {
                    result = Integer.parseInt(queueForNumber.removeFirst());
                    result -= Integer.parseInt(queueForNumber.removeFirst());
                    queueForNumber.addFirst(Integer.toString(result));
                } else if (s.equals("*")) {
                    result = Integer.parseInt(queueForNumber.removeFirst());
                    result *= Integer.parseInt(queueForNumber.removeFirst());
                    queueForNumber.addFirst(Integer.toString(result));
                } else if (s.equals("/")) {
                    result = Integer.parseInt(queueForNumber.removeFirst());
                    int divider = Integer.parseInt(queueForNumber.removeFirst());
                    if (divider != 0) {
                        result /= divider;
                    } else {
                        return null;
                    }
                    queueForNumber.addFirst(Integer.toString(result));
                } else if (s.equals("%")) {
                    result = Integer.parseInt(queueForNumber.removeFirst());
                    int divisor = Integer.parseInt(queueForNumber.removeFirst());
                    if (divisor != 0) {
                        result %= divisor;
                    } else {
                        return null;
                    }
                    queueForNumber.addFirst(Integer.toString(result));
                } else {
                    return null;
                }
            }
        } catch (NoSuchElementException e) {
            return null;
        }

        result = Integer.parseInt(queueForNumber.removeFirst());

        if (queueForNumber.isEmpty()) {
            finalResult = Integer.toString(result);
        }

        return finalResult;
    }
}