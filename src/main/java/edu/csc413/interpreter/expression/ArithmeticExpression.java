package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

public abstract class ArithmeticExpression implements Expression
{
    private Expression lhs,
                        rhs;

    public ArithmeticExpression(Expression lhs, Expression rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    protected int getLhsValue(ProgramState programState)
    {
        return lhs.evaluate(programState);
    }

    protected int getRhsValue(ProgramState programState) {
        return rhs.evaluate(programState);
    }
}
