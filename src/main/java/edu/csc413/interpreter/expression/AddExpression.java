package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

public class AddExpression extends ArithmeticExpression
{
    public AddExpression(Expression lhs, Expression rhs)
    {
        super(lhs, rhs);
    }

    @Override
    public int evaluate(ProgramState programState)
    {
        return getLhsValue(programState) + getRhsValue(programState);
    }
}