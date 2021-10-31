package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

public class AssignStatement implements Statement
{
    String variableName;
    private Expression expressionToSave;


    @Override
    public void run(ProgramState programState) {

    }
}
