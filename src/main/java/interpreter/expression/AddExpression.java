package interpreter.expression;

public class AddExpression extends ArithmeticExpression
{
    public AddExpression(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public int evaluate()
    {
        return getLhsValue() + getRhsValue();
    }
}