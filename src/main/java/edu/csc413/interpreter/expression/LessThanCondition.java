package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

public class LessThanCondition extends Condition
{

    public LessThanCondition(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public boolean evaluate(ProgramState programState)
    {
        return getLhsValue(programState) < getRhsValue(programState);
    }
}
