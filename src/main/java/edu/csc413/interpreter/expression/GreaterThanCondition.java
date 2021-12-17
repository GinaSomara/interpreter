package edu.csc413.interpreter.expression;

public class GreaterThanCondition extends Condition
{
    public GreaterThanCondition(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public boolean evaluate()
    {
        return getLhsValue() > getRhsValue();
    }
}
