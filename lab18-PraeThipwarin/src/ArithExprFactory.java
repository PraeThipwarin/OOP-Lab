public class ArithExprFactory {
    private static ArithExprFactory INSTANCE;

    public static ArithExprFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ArithExprFactory();
        }
        return INSTANCE;
    }

    public AST.BinaryArithExpr createBinaryExpr(Expr left, String op, Expr right) {
        return new AST.BinaryArithExpr(left, op, right);
    }

    public AST.IntLit createIntLit(int value) {
        return new AST.IntLit(value);
    }

    public AST.Variable createVariable(String name) {
        return new AST.Variable(name);
    }
}
