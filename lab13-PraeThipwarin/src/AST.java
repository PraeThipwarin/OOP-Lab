import java.util.Map;

public class AST {
    record IntLit(int val) implements Expr {
        public int eval(Map<String, Integer> bindings) {
            return val;
        }
        public void prettyPrint(StringBuilder s) {
            s.append(val);
        }
    }

    record Variable(String name) implements Expr {
        public int eval(Map<String, Integer> bindings) {
            if (bindings.containsKey(name))
                return bindings.get(name);
            throw new NewException.EvalError("undefined variable: " + name);
        }
        public void prettyPrint(StringBuilder s) {
            s.append(name);
        }
    }

    record BinaryArithExpr(Expr left, String op, Expr right) implements Expr {
        public int eval(Map<String, Integer> bindings) {
            int lv = left.eval(bindings);
            int rv = right.eval(bindings);
            return switch (op) {
                case "+" -> lv + rv;
                case "-" -> lv - rv;
                case "*" -> lv * rv;
                case "/" -> {
                    try {
                        yield lv / rv;
                    } catch (ArithmeticException e) {
                        throw new NewException.DivideOrModBy0("Division by zero");
                    }

                }
                case "%" -> {
                    try {
                        yield lv % rv;
                    } catch (ArithmeticException e) {
                        throw new NewException.DivideOrModBy0("Modulo by zero");
                    }
                }
                default -> throw new NewException.EvalError("unknown op: " + op);
            };
        }
        public void prettyPrint(StringBuilder s) {
            s.append("(");
            left.prettyPrint(s);
            s.append(op);
            right.prettyPrint(s);
            s.append(")");
        }
    }
}
