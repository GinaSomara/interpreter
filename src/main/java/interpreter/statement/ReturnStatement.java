package interpreter.statement;

import interpreter.ProgramState;
import interpreter.expression.Expression;

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
