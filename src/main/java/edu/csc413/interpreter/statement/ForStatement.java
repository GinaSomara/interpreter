package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

import java.util.List;

public class ForStatement extends BlockStatement //implements Statement
{
    private String forVariableName;
    private Expression rangeStart,
                       rangeStop;

    public ForStatement(String forVariableNameName,
                        Expression rangeStartAsString,
                        Expression rangeEndAsString,
                        List<Statement> body)
    {
        super(body);

        this.forVariableName = forVariableNameName;
        this.rangeStart = rangeStartAsString;
        this.rangeStop = rangeEndAsString;
    }

    @Override
    public void run()
    {
        int start = rangeStart.evaluate();
        int end = rangeStop.evaluate();

        for(int loopVar = start ; loopVar < end ; loopVar++)
        {
            ProgramState.getProgramState().setVariable(forVariableName, loopVar);
            runBlock();

            if (ProgramState.getProgramState().hasReturnValue())
                return;
        }
    }

    @Override
    public void runBlock()
    {
        for(Statement statement : getBody())
        {
            statement.run();

            if (ProgramState.getProgramState().hasReturnValue())
                return;
        }
    }
}
