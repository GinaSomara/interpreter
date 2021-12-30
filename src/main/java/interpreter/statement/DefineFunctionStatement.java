package interpreter.statement;
import interpreter.ProgramState;

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
    public void run()
    {
        ProgramState.getProgramState().registerFunction(functionName, functionParameters, functionStatements);
    }
}
