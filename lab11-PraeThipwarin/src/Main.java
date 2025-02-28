import java.util.NoSuchElementException;

interface Tokenizer {
    boolean hasNextToken();
    String peek();
    String consume();
}

interface Parser {
    int parse() throws NoSuchElementException;
}

class ExprTokenizer implements Tokenizer {
    private String expr, next_token;
    private int position;

    public ExprTokenizer(String expr) {
        this.expr = expr;
        this.position = 0;
        computeNext(); // Start processing the first token
    }

    public boolean hasNextToken() {
        return next_token != null;
    }

    public void checkNextToken() {
        if (!hasNextToken()) throw new NoSuchElementException("No more tokens");
    }

    public String peek() {
        checkNextToken();
        return next_token;
    }

    public String consume() {
        checkNextToken();
        String result = next_token;
        computeNext();
        return result;
    }

    private void computeNext() {
        StringBuilder s = new StringBuilder();

        // Skip whitespace
        while (position < expr.length() && Character.isWhitespace(expr.charAt(position))) {
            position++;
        }

        // Check if end of source is reached
        if (position == expr.length()) {
            next_token = null;
            return;
        }

        char c = expr.charAt(position);

        if (Character.isDigit(c)) {  // Start of a number
            while (position < expr.length() && Character.isDigit(expr.charAt(position))) {
                s.append(expr.charAt(position));
                position++;
            }
        } else if (c == '+' || c == '(' || c == '-' || c == '*' || c == '/' || c == ')' || c == '%') {  // Single-character operators or delimiters
            s.append(c);
            position++;
        } else {
            throw new NoSuchElementException("Unknown character: " + c);
        }
        next_token = s.toString();
    }
}

class ExprParser implements Parser {
    private Tokenizer tkz;
    public ExprParser(Tokenizer tkz) {
        this.tkz = tkz;
    }

    public boolean peek(String s) {
        if (!tkz.hasNextToken())
            return false;
        return tkz.peek().equals(s);
    }

    public void consume(String s) throws NoSuchElementException {
        if (peek(s))
            tkz.consume();
        else
            throw new NoSuchElementException(s + " expected");
    }

    public int parse() throws IllegalArgumentException {
        // begin parsing at start symbol
        int v = parseE();
        // reject if there is remaining token
        if (tkz.hasNextToken())
            throw new IllegalArgumentException("leftover token");

        return v;
    }
    // E → T (+ T)^* | T (- T)^* | T
    private int parseE() throws NoSuchElementException {
        int v = parseT();
        while (peek("+") || peek("-")) {
            if (peek("+")){
                consume("+");
                v = v + parseT();
            } else if (peek("-")){
                consume("-");
                v = v - parseT();
            }
        }
        return v;
    }

    // T → F (* F)^* | F (/ F)^* | F (% F)^* | F
    private int parseT() throws NoSuchElementException {
        int v = parseF();
        while (peek("*") || peek("/") || peek("%")) {
            if (peek("*")){
                consume("*");
                v = v * parseT();
            } else if (peek("/")){
                consume("/");
                v = v / parseT();
            } else if (peek("%")){
                consume("%");
                v = v % parseT();
            }
        }
        return v;
    }

    // F → n | ( E )
    private int parseF() throws NoSuchElementException {
        if (isNumber(tkz.peek())) {
            String tok = tkz.consume();
            return Integer.parseInt(tok);
        } else {
            consume("(");
            int v = parseE();
            consume(")");
            return v;
        }
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String expression = "((2 * 3) + 4) - (8 / (7 % 5))";
        ExprTokenizer tokenizer = new ExprTokenizer(expression);
        ExprParser parser = new ExprParser(tokenizer);

        try {
            int result = parser.parse();
            System.out.println("Result: " + result);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: " + e.getMessage());
        } catch (StackOverflowError e) {
            throw new StackOverflowError("InvalidExpression: " + expression);
        }
    }
}


