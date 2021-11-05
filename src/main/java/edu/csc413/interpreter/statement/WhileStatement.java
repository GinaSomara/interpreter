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
    public boolean evaluateCondition(ProgramState programState)
    {
        return getCondition().evaluate(programState);
    }

    @Override
    public void run(ProgramState programState)
    {
        while (evaluateCondition(programState))
        {
            runBlock(programState);

            if (programState.hasReturnValue())
                return;
        }
    }

    @Override
    public void runBlock(ProgramState programState)
    {
        for(Statement statement :getBody())
        {
            statement.run(programState);

            if (programState.hasReturnValue())
                break;
        }
    }
}
