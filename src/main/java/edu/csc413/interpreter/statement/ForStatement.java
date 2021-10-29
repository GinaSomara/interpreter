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
    //implement list of body statements (INHERITED from BlockStatement)
    public For()
    {
        //don't want to call super first becasue I need to make a list first???
        //or I could do super(newMethodThatCreatesList(List info)) ?
        //ADD code here for body statements in for loop
        List<Statement> tempBody = new ArrayList<>();

        super(new ArrayList<>());
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
