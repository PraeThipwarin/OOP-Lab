import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ExprParserTest {

    @Test
    void parse_SimpleExpr() {
        Tokenizer tokenizer = new ExprTokenizer("2+ 3");
        ExprParser parser = new ExprParser(tokenizer);
        assertEquals(5, parser.parse());

        Tokenizer tokenizer2 = new ExprTokenizer("10 -4");
        ExprParser parser2 = new ExprParser(tokenizer2);
        assertEquals(6, parser2.parse());

        Tokenizer tokenizer3 = new ExprTokenizer("12 * 5");
        ExprParser parser3 = new ExprParser(tokenizer3);
        assertEquals(60, parser3.parse());

        Tokenizer tokenizer4 = new ExprTokenizer("20 /4");
        ExprParser parser4 = new ExprParser(tokenizer4);
        assertEquals(5, parser4.parse());

        Tokenizer tokenizer5 = new ExprTokenizer("25%2");
        ExprParser parser5 = new ExprParser(tokenizer5);
        assertEquals(1, parser5.parse());
    }

    @Test
    void parse_ComplexExpr() {
        Tokenizer tokenizer = new ExprTokenizer("2 + 3 * 4 - 6 / 2");
        ExprParser parser = new ExprParser(tokenizer);
        assertEquals(11, parser.parse());

        Tokenizer tokenizer2 = new ExprTokenizer("(2 + 3) * (4 - 1)");
        ExprParser parser2 = new ExprParser(tokenizer2);
        assertEquals(15, parser2.parse());

        Tokenizer tokenizer3 = new ExprTokenizer("(2 * (3 + 4)) - (8 / (1 + 1))");
        ExprParser parser3 = new ExprParser(tokenizer3);
        assertEquals(10, parser3.parse());
    }

    @Test
    void parse_WrongExpr() {
        Tokenizer tokenizer = new ExprTokenizer("234 abc");
        ExprParser parser = new ExprParser(tokenizer);
        assertThrows(NoSuchElementException.class, parser::parse);

        Tokenizer tokenizer2 = new ExprTokenizer("2 +");
        ExprParser parser2 = new ExprParser(tokenizer2);
        assertThrows(NoSuchElementException.class, parser2::parse);

        Tokenizer tokenizer3 = new ExprTokenizer("(2 + 3");
        ExprParser parser3 = new ExprParser(tokenizer3);
        assertThrows(NoSuchElementException.class, parser3::parse);

        Tokenizer tokenizer4 = new ExprTokenizer("2 $ 3");
        ExprParser parser4 = new ExprParser(tokenizer4);
        assertThrows(NoSuchElementException.class, parser4::parse);

        Tokenizer tokenizer5 = new ExprTokenizer("55555555555555555/555555555555555");
        ExprParser parser5 = new ExprParser(tokenizer5);
        assertThrows(NoSuchElementException.class, parser5::parse);

        Tokenizer tokenizer6 = new ExprTokenizer("-2 + 2");
        ExprParser parser6 = new ExprParser(tokenizer6);
        assertThrows(NoSuchElementException.class, parser6::parse);
    }

    @Test
    void parse_ModOrDivideByZero() {
        Tokenizer tokenizer = new ExprTokenizer("8 / 0");
        ExprParser parser = new ExprParser(tokenizer);
        assertThrows(ArithmeticException.class, parser::parse);

        Tokenizer tokenizer2 = new ExprTokenizer("8 % 0");
        ExprParser parser2 = new ExprParser(tokenizer2);
        assertThrows(ArithmeticException.class, parser2::parse);
    }
}