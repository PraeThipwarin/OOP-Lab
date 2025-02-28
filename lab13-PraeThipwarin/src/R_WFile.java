import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

class R_WFile {
    public static void ReadFile(String fileName) {
        Path file = Paths.get(fileName);
        Charset charset = Charset.forName("UTF-8");

        StringBuilder results = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String textLine = null;
            while ((textLine = reader.readLine()) != null) {
                String expression = textLine;
                ExprTokenizer tokenizer = new ExprTokenizer(expression);
                ExprParser parser = new ExprParser(tokenizer);

                try {
                    Expr ast = parser.parse();
                    StringBuilder sb = new StringBuilder();
                    ast.prettyPrint(sb);
                    results.append(sb.toString());
                    results.append(" ");

                } catch (NoSuchElementException e) {
                    System.err.println("Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid Expression: " + expression);
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        System.out.println(results.toString());
        WriteFile(results.toString(), "Result");
    }

    public static void WriteFile(String text, String fileName) {
        Path file = Paths.get(fileName);
        Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(text);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
