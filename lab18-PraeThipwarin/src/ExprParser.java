import java.util.NoSuchElementException;

class ExprParser implements Parser {
    private final Tokenizer tkz;
    private final ArithExprFactory factory;

    public ExprParser(Tokenizer tkz) {
        this.tkz = tkz;
        this.factory = ArithExprFactory.getInstance();
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
            throw new NewException.SyntaxError(s + " expected");
    }

    public Expr parse() throws NewException.SyntaxError {
        Expr v = parseE();

        if (tkz.hasNextToken())
            throw new IllegalArgumentException("leftover token");

        return v;
    }
    // E → T (+ T)^* | T (- T)^*
    private Expr parseE() throws NewException.SyntaxError {
        Expr v = parseT();
        while (peek("+") || peek("-")) {
            String op = tkz.consume();
            v = factory.createBinaryExpr(v, op, parseT());
        }
        return v;
    }

    // T → F (* F)^* | F (/ F)^* | F (% F)^*
    private Expr parseT() throws NewException.SyntaxError {
        Expr v = parseF();
        while (peek("*") || peek("/") || peek("%")) {
            String op = tkz.consume();
            v = factory.createBinaryExpr(v, op, parseF());
        }
        return v;
    }

    // F → n | x | ( E )
    private Expr parseF() throws NewException.SyntaxError {
        if (isNumber(tkz.peek())) {
            return factory.createIntLit(Integer.parseInt(tkz.consume()));
        } else if (isVariable(tkz.peek())) {
            return factory.createVariable(tkz.consume());
        } else {
            consume("(");
            Expr v = parseE();
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

    private boolean isVariable(String s) {
        return s.matches("[a-zA-Z]+");
    }
}