package edu.csc413.interpreter.expression;

public class EqualToCondition extends Condition
{
    public EqualToCondition(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public boolean evaluate()
    {
        return getLhsValue() == getRhsValue();
    }
}
