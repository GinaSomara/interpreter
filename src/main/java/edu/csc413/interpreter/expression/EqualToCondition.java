package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

public class EqualToCondition extends Condition
{
    public EqualToCondition(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public boolean evaluate(ProgramState programState)
    {
        return getLhsValue(programState) == getRhsValue(programState);
    }
}
