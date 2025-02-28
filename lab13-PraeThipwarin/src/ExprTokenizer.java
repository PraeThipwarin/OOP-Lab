import java.util.NoSuchElementException;

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
        } else if (Character.isLetter(c)) {
            s.append(c);
            position++;
        } else if (c == '+' || c == '(' || c == '-' || c == '*' || c == '/' || c == ')' || c == '%') {  // Single-character operators or delimiters
            s.append(c);
            position++;
        } else {
            throw new NewException.LexicalError("Unknown character: " + c);
        }
        next_token = s.toString();
    }
}