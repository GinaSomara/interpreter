package interpreter.statement;

import interpreter.ProgramState;
import interpreter.expression.Condition;

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
