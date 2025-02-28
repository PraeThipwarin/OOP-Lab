import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadfileTest {
    @Test
    void testValidateExpression_Valid() {
        ArrayList<String> result = Main.validateExpression("30902817 +54- 4018368401034877837 + 13084613");
        assertNotNull(result);
        assertEquals(7, result.size());
        assertEquals("30902817", result.get(0));
        assertEquals("+", result.get(1));
        assertEquals("54", result.get(2));
        assertEquals("-", result.get(3));
        assertEquals("4018368401034877837", result.get(4));
        assertEquals("+", result.get(5));
        assertEquals("13084613", result.get(6));
    }

    @Test
    void testValidateExpression_Invalid() {
        ArrayList<String> result = Main.validateExpression("6-aevxfff");
        assertNull(result);
    }

    @Test
    void testValidateExpression_Empty() {
        ArrayList<String> result = Main.validateExpression("");
        assertNull(result);
    }

    @Test
    void testValidateExpression_WrongOrderProposition() {
        ArrayList<String> result = Main.validateExpression("30 5 + - 4");
        assertNull(result);
    }

    @Test
    void testValidateExpression_NegativeNumber() {
        ArrayList<String> result = Main.validateExpression("-567");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("-", result.get(0));
        assertEquals("567", result.get(1));
    }

    @Test
    void testCalculateExpression_ValidAddition() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("5555");
        expression.add("/");
        expression.add("5");
        expression.add("%");
        expression.add("100");
        expression.add("+");
        expression.add("5");
        expression.add("-");
        expression.add("1");
        expression.add("*");
        expression.add("5");
        String result = Main.calculateExpression(expression);
        assertNotNull(result);
        assertEquals("75", result);
    }

    @Test
    void testCalculateExpression_Overbound() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("555555555555555555");
        expression.add("/");
        expression.add("5");
        String result = Main.calculateExpression(expression);
        assertNull(result);
    }

    @Test
    void testCalculateExpression_InvalidAddition() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("-");
        expression.add("567");
        String result = Main.calculateExpression(expression);
        assertNull(result);
    }

    @Test
    void testCalculateExpression_NotIntergerResult() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("555");
        expression.add("/");
        expression.add("6");
        String result = Main.calculateExpression(expression);
        assertNotNull(result);
        assertEquals("92", result);
    }

    @Test
    void testCalculateExpression_InvalidDivisionByZero() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("10");
        expression.add("/");
        expression.add("0");
        assertNull(Main.calculateExpression(expression));
        ArrayList<String> expression2 = new ArrayList<>();
        expression.add("17");
        expression.add("%");
        expression.add("0");
        assertNull(Main.calculateExpression(expression2));
    }

    @Test
    void testCalculateExpression_MissingOperands() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("+");
        assertNull(Main.calculateExpression(expression));
    }

    @Test
    void testCalculateExpression_MissingOperators() {
        ArrayList<String> expression = new ArrayList<>();
        expression.add("17");
        expression.add("1397");
        assertNull(Main.calculateExpression(expression));
    }

    @Test
    void testNumeric_True() {
        assertTrue(Main.Numeric("123"));
        assertTrue(Main.Numeric("12340"));
    }

    @Test
    void testNumeric_Overbound() {
        assertFalse(Main.Numeric("12345678910"));
    }

    @Test
    void testNumeric_False() {
        assertFalse(Main.Numeric("+"));
        assertFalse(Main.Numeric("-"));
        assertFalse(Main.Numeric("*"));
        assertFalse(Main.Numeric("%"));
        assertFalse(Main.Numeric("y"));
        assertFalse(Main.Numeric("a"));
        assertFalse(Main.Numeric("/"));
    }

    @Test
    void testReadFile_ValidFile() throws IOException {
        String textContent = Files.readString(Paths.get("src/TestInput.txt"));
        assertTrue(textContent.contains("3 + 5 -2\n" + "10 / 2\n" + "67 - 8\n"));
        Main.ReadFile("src/TestInput.txt","src/Result1");
        String resultContent = Files.readString(Paths.get("src/Result1"));
        assertTrue(resultContent.contains("3 + 5 -2 = 6\n" + "10 / 2 = 5\n" + "67 - 8 = 59\n"));
    }

    @Test
    void testReadFile_InvalidFile() {
        Main.ReadFile("src/TestInput23456y.txt","src/Result2");
    }

    @Test
    void testReadFile_InvalidText() {
        assertThrows(IllegalArgumentException.class, () -> Main.ReadFile("src/TestInput2","Result3"));
    }

    @Test
    void testReadFile_EmptyFile() throws IOException {
        Main.ReadFile("src/EmptyFile","src/Result4");
        assertThrows(IOException.class, () -> Files.readString(Paths.get("src/Result4")));
    }

    @Test
    void testWriteFile() throws IOException {
        String testFile = "src/TestOutput.txt";
        String content = "Test write content.";
        Main.WriteFile(content, testFile);
        String fileContent = Files.readString(Paths.get(testFile));
        assertEquals(content, fileContent);
    }
}