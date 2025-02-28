import java.util.NoSuchElementException;

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
            if (peek("+")){
                consume("+");
                v = new AST.BinaryArithExpr(v, "+", parseT());
            } else if (peek("-")){
                consume("-");
                v = new AST.BinaryArithExpr(v, "-", parseT());
            }
        }
        return v;
    }

    // T → F (* F)^* | F (/ F)^* | F (% F)^*
    private Expr parseT() throws NewException.SyntaxError {
        Expr v = parseF();
        while (peek("*") || peek("/") || peek("%")) {
            if (peek("*")){
                consume("*");
                v = new AST.BinaryArithExpr(v, "*", parseF());
            } else if (peek("/")){
                consume("/");
                v = new AST.BinaryArithExpr(v, "/", parseF());
            } else if (peek("%")){
                consume("%");
                v = new AST.BinaryArithExpr(v, "%", parseF());
            }
        }
        return v;
    }

    // F → n | x | ( E )
    private Expr parseF() throws NewException.SyntaxError {
        if (isNumber(tkz.peek())) {
            String tok = tkz.consume();
            return new AST.IntLit(Integer.parseInt(tok));
        } else if (isVariable(tkz.peek())) {
            String tok = tkz.consume();
            return new AST.Variable(tok);
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