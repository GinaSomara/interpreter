package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class ForStatement extends BlockStatement //implements Statement
{
    private String loopVariable;
    private Expression rangeStart,
               rangeStop;

    //need parameters
    public ForStatement(List<Statement> body) {
        super(body);
    }

    //add code
    @Override
    public void runBlock()
    {

    }

    //add code
    @Override
    public void run(ProgramState programState)
    {
        //used to call upon information in the programState class
    }
}
