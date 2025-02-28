import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ExprTokenizerTest {

    @Test
    void hasNextToken_True() {
        ExprTokenizer tokenizer = new ExprTokenizer("123 + 456");
        assertTrue(tokenizer.hasNextToken());
    }

    @Test
    void hasNextToken_False() {
        ExprTokenizer tokenizer = new ExprTokenizer(" ");
        assertFalse(tokenizer.hasNextToken());
    }

    @Test
    void peek_FirstToken() {
        ExprTokenizer tokenizer = new ExprTokenizer("123 + 456");
        assertEquals("123", tokenizer.peek());
    }

    @Test
    void peek_EmptyToken() {
        ExprTokenizer tokenizer = new ExprTokenizer("");
        assertThrows(NoSuchElementException.class, tokenizer::peek);
    }

    @Test
    void consume_AllToken() {
        ExprTokenizer tokenizer = new ExprTokenizer("12 + 3 - 45*6/78 % 9 ");
        assertEquals("12", tokenizer.consume());
        assertEquals("+", tokenizer.consume());
        assertEquals("3", tokenizer.consume());
        assertEquals("-", tokenizer.consume());
        assertEquals("45", tokenizer.consume());
        assertEquals("*", tokenizer.consume());
        assertEquals("6", tokenizer.consume());
        assertEquals("/", tokenizer.consume());
        assertEquals("78", tokenizer.consume());
        assertEquals("%", tokenizer.consume());
        assertEquals("9", tokenizer.consume());

        assertFalse(tokenizer.hasNextToken());
    }

    @Test
    void consume_FalseToken() {
        ExprTokenizer tokenizer = new ExprTokenizer("2 abc");
        assertThrows(NoSuchElementException.class, tokenizer::consume);

        ExprTokenizer tokenizer2 = new ExprTokenizer("2 &");
        assertThrows(NoSuchElementException.class, tokenizer2::consume);
    }

    @Test
    void consume_EmptyToken() {
        ExprTokenizer tokenizer = new ExprTokenizer("");
        assertThrows(NoSuchElementException.class, tokenizer::consume);
    }
}