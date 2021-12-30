package interpreter.statement;

import interpreter.expression.Expression;

public class PrintStatement implements Statement
{
    private Expression expressionToPrint;

    public PrintStatement(Expression expression)
    {
        this.expressionToPrint = expression;
    }

    @Override
    public void run()
    {
        System.out.println(expressionToPrint.evaluate());
    }
}
