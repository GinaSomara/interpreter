package interpreter.expression;

public abstract class ArithmeticExpression implements Expression
{
    private Expression lhs,
                        rhs;

    public ArithmeticExpression(Expression lhs, Expression rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    protected int getLhsValue()
    {
        return lhs.evaluate();
    }

    protected int getRhsValue() {
        return rhs.evaluate();
    }
}
