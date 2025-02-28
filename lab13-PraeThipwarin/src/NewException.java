import java.util.NoSuchElementException;

public class NewException {
    public static class SyntaxError extends NoSuchElementException {
        public SyntaxError(String message) {
            super(message);
        }
    }

    public static class LexicalError extends NoSuchElementException {
        public LexicalError(String message) {
            super(message);
        }
    }

    public static class EvalError extends NoSuchElementException {
        public EvalError(String message) {
            super(message);
        }
    }

    public static class DivideOrModBy0 extends ArithmeticException {
        public DivideOrModBy0(String message) {
            super(message);
        }
    }
}

