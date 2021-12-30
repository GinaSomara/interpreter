package interpreter.expression;

import interpreter.ProgramState;

public class VariableExpression implements Expression
{
    private String varName;

    public VariableExpression(String variableName)
    {
        this.varName = variableName;
    }

    @Override
    public int evaluate()
    {
        return ProgramState.getProgramState().getVariable(varName);
    }
}
