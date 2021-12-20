package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Condition;

import java.util.List;

public class WhileStatement extends ConditionBlock
{
    public WhileStatement(Condition condition, List<Statement> body)
    {
        super(condition, body);
    }

    @Override
    public void run()
    {
        while (evaluateCondition())
        {
            runBlock();

            if (ProgramState.getProgramState().hasReturnValue())
                return;
        }
    }
}
