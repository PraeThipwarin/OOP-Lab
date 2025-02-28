import java.util.Map;

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

        for(int i = 0;i<1000000;i++) {
            R_WFile.ReadFile("src/TextSample");
        }
    }
}