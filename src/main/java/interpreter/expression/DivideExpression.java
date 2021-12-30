package interpreter.expression;

public class DivideExpression extends ArithmeticExpression
{
    public DivideExpression(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public int evaluate()
    {
        return getLhsValue() / getRhsValue();
    }
}
