package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Condition;

import java.util.List;

public class IfStatement extends ConditionBlock
{
    public IfStatement(Condition condition, List<Statement> body)
    {
        super(condition, body);
    }

    @Override
    public void run()
    {
        if (evaluateCondition())
            runBlock();
    }
}