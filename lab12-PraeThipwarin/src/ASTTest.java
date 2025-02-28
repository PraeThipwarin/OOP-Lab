import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ASTTest {
    private Map<String, Integer> bindings;

    @BeforeEach
    public void setUp() {
        bindings = new HashMap<>();
        bindings.put("x", 5);
    }

    @Test
    void AST_withParse_Test1() {
        String expression1 = "261200";
        ExprTokenizer tokenizer1 = new ExprTokenizer(expression1);
        ExprParser parser1 = new ExprParser(tokenizer1);
        Expr ast1 = parser1.parse();
        StringBuilder sb1 = new StringBuilder();
        ast1.prettyPrint(sb1);
        assertEquals("261200", sb1.toString());
        assertEquals(261200, ast1.eval(bindings));

        String expression2 = "2*x+6";
        ExprTokenizer tokenizer2 = new ExprTokenizer(expression2);
        ExprParser parser2 = new ExprParser(tokenizer2);
        Expr ast2 = parser2.parse();
        StringBuilder sb2 = new StringBuilder();
        ast2.prettyPrint(sb2);
        assertEquals("((2*x)+6)", sb2.toString());
        assertEquals(16, ast2.eval(bindings));

        String expression3 = "2+x*6";
        ExprTokenizer tokenizer3 = new ExprTokenizer(expression3);
        ExprParser parser3 = new ExprParser(tokenizer3);
        Expr ast3 = parser3.parse();
        StringBuilder sb3 = new StringBuilder();
        ast3.prettyPrint(sb3);
        assertEquals("(2+(x*6))", sb3.toString());
        assertEquals(32, ast3.eval(bindings));

        bindings.put("a", 5);
        bindings.put("b", 3);
        bindings.put("c", 6);
        String expression4 = "3 * a + 4 * b - (2 * c)";
        ExprTokenizer tokenizer4 = new ExprTokenizer(expression4);
        ExprParser parser4 = new ExprParser(tokenizer4);
        Expr ast4 = parser4.parse();
        StringBuilder sb4 = new StringBuilder();
        ast4.prettyPrint(sb4);
        assertEquals("(((3*a)+(4*b))-(2*c))", sb4.toString());
        assertEquals(15, ast4.eval(bindings));
    }

    @Test
    void AST_withParse_Test2() {
        String expression1 = "2&6";
        ExprTokenizer tokenizer1 = new ExprTokenizer(expression1);
        ExprParser parser1 = new ExprParser(tokenizer1);
        assertThrows(NewException.LexicalError.class, parser1::parse);

        String expression2 = "(2+3";
        ExprTokenizer tokenizer2 = new ExprTokenizer(expression2);
        ExprParser parser2 = new ExprParser(tokenizer2);
        assertThrows(NewException.SyntaxError.class, parser2::parse);

        String expression3 = "4+2-9*10/0";
        ExprTokenizer tokenizer3 = new ExprTokenizer(expression3);
        ExprParser parser3 = new ExprParser(tokenizer3);
        Expr ast3 = parser3.parse();
        assertThrows(NewException.DivideOrModBy0.class, () -> ast3.eval(bindings));

        String expression4 = "66666666666666666/6666";
        ExprTokenizer tokenizer4 = new ExprTokenizer(expression4);
        ExprParser parser4 = new ExprParser(tokenizer4);
        assertThrows(NewException.SyntaxError.class, parser4::parse);
    }
}

