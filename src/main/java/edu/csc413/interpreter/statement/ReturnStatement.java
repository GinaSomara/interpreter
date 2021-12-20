package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

public class ReturnStatement implements Statement
{
    private Expression returnExpression;

    public ReturnStatement(Expression expression)
    {
        this.returnExpression = expression;
    }

    @Override
    public void run()
    {
        ProgramState.getProgramState().setReturnValue(returnExpression.evaluate());
    }
}
