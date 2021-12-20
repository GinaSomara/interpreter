package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.VariableExpression;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DefineFunctionStatementTest
{
    @Test
    public void test_simpleFunctionDefinition()
    {
        /*  def test_Function(x)
         *      return x
         */
        List<String> parameters = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        String functionName = "test_Function";

        // Parameters passed in
        parameters.add("x");

        // Statement in Function Scope
        ReturnStatement returnStatement = new ReturnStatement(new VariableExpression("x"));
        statements.add(returnStatement);

        //create Function Definition + feed to ProgramState
        DefineFunctionStatement functionDefinition = new DefineFunctionStatement(functionName, parameters, statements);
        functionDefinition.run();

        //Ensure Function Parameters are correct
        assertThat(ProgramState.getProgramState().getParameterNames(functionName), contains("x"));
        //Ensure Function Statements are correct
        assertThat(ProgramState.getProgramState().getFunctionStatements(functionName), contains(returnStatement));
    }
}
