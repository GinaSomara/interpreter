package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

public class AssignStatement implements Statement
{
    private String variableName;
    private Expression expressionToSave;

    public AssignStatement(String variableName, Expression expression)
    {
        this.variableName = variableName;
        this.expressionToSave = expression;
    }

    @Override
    public void run()
    {
        ProgramState.getProgramState().setVariable(variableName, expressionToSave.evaluate());
    }
}
