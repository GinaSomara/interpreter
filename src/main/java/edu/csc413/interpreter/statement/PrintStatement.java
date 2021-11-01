package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

public class PrintStatement implements Statement
{
    private Expression expressionToPrint;

    public PrintStatement(Expression expression)
    {
        this.expressionToPrint = expression;
    }

    @Override
    public void run(ProgramState programState)
    {
        System.out.println(expressionToPrint.evaluate(programState));
    }
}
