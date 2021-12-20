package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;

import java.util.List;

public abstract class BlockStatement implements Statement
{
    private List<Statement> body;

    public BlockStatement(List<Statement> body)
    {
        this.body = body;
    }


    protected void runBlock()
    {
        for(Statement statement : body)
        {
            statement.run();

            if (ProgramState.getProgramState().hasReturnValue())
                break;
        }
    }
}
