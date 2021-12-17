package edu.csc413.interpreter.expression;

public class SubtractExpression extends ArithmeticExpression
{
    public SubtractExpression(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public int evaluate()
    {
        return getLhsValue() - getRhsValue();
    }
}
