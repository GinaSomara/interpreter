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
    public void run(ProgramState programState)
    {
        int start = rangeStart.evaluate(programState);
        int end = rangeStop.evaluate(programState);

        for(int loopVar = start ; loopVar < end ; loopVar++)
        {
            programState.setVariable(forVariableName, loopVar);
            runBlock(programState);

            if (programState.hasReturnValue())
                return;
        }
    }

    @Override
    public void runBlock(ProgramState programState)
    {
        for(Statement statement : getBody())
        {
            statement.run(programState);

            if (programState.hasReturnValue())
                return;
        }
    }
}
