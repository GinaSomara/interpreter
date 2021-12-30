package interpreter.statement;

import interpreter.ProgramState;
import interpreter.expression.Expression;

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
    public void run()
    {
        int start = rangeStart.evaluate();
        int end = rangeStop.evaluate();

        for(int loopVar = start ; loopVar < end ; loopVar++)
        {
            ProgramState.getProgramState().setVariable(forVariableName, loopVar);
            runBlock();

            if (ProgramState.getProgramState().hasReturnValue())
                return;
        }
    }
}
