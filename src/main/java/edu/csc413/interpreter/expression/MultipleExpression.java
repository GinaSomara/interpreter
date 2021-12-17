package edu.csc413.interpreter.expression;

public class MultipleExpression extends ArithmeticExpression
{
    public MultipleExpression(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public int evaluate()
    {
        return getLhsValue() * getRhsValue();
    }
}
