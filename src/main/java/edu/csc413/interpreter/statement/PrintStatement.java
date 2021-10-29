package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;

public class PrintStatement implements Statement
{
    //IMPLEMENT
    @Override
    public void run(ProgramState programState)
    {
        System.out.println();
    }
}
