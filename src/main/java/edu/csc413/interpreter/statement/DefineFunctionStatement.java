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
        /**need to somehow turn the functionParameters from a list of Strings
         * into a list of ints
         */
        List<Integer> parametersAsInts = getValueOfParameters(programState);

    }

    private List<Integer> getValueOfParameters(ProgramState programState)
    {
        List<Integer> parameters = new ArrayList<>();
        for(String s : functionParameters)
        {
            parameters.add(programState.getVariable(s));
        }
        return parameters;
    }
}
