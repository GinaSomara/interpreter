package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

import java.util.ArrayList;
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
    public void runBlock(ProgramState programState)
    {
        for(Statement statement : getBody())
        {
            statement.run(programState);
        }
    }

    @Override
    public void run(ProgramState programState)
    {
        int start = rangeStart.evaluate(programState);
        int end = rangeStop.evaluate(programState);

        //need to store variable so that items in the for loop can access the counter variable

        for(int loopVar = start ; loopVar < end ; loopVar++)
        {
            programState.setVariable(forVariableName, loopVar);
            runBlock(programState);
        }
    }
}
