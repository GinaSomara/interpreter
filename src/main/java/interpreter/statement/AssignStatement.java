package interpreter.statement;

import interpreter.ProgramState;
import interpreter.expression.Expression;

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
