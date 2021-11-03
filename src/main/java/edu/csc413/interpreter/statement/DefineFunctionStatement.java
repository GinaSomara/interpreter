package edu.csc413.interpreter.statement;
import edu.csc413.interpreter.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class DefineFunctionStatement implements Statement
{
    private String functionName;
    private List<String> functionParameters;
    private List<Statement> functionStatements;

    public DefineFunctionStatement(String functionName, List<String> functionParameters, List<Statement> functionStatements)
    {
        this.functionName = functionName;
        this.functionParameters = functionParameters;
        this.functionStatements = functionStatements;
    }

    @Override
    public void run(ProgramState programState)
    {
        programState.registerFunction(functionName, functionParameters, functionStatements);
    }
}
