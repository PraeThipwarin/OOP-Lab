import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

interface Parser {
    Expr parse() throws NewException.SyntaxError;
}

interface Tokenizer {
    boolean hasNextToken();
    String peek();
    String consume();
}

interface Node {
    void prettyPrint(StringBuilder sb);
}

interface Expr extends Node {
    int eval(Map<String, Integer> bindings);
}

public class Main {
    public static void main(String[] args) {
        String expression = "2*x+6";
        ExprTokenizer tokenizer = new ExprTokenizer(expression);
        ExprParser parser = new ExprParser(tokenizer);

        Map<String, Integer> bindings = new HashMap<>();
        bindings.put("x", 2);


        try {
            Expr ast = parser.parse();
            StringBuilder sb = new StringBuilder();
            ast.prettyPrint(sb);
            int result = ast.eval(bindings);

            System.out.println("Expression: " + sb.toString());
            System.out.println("Result: " + result);

        } catch (NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Expression: " + expression);
        }
    }
}