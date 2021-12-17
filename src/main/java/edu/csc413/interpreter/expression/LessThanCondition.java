package edu.csc413.interpreter.expression;

public class LessThanCondition extends Condition
{
    public LessThanCondition(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public boolean evaluate()
    {
        return getLhsValue() < getRhsValue();
    }
}
