package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.statement.DefineFunctionStatement;
import edu.csc413.interpreter.statement.ReturnStatement;
import edu.csc413.interpreter.statement.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class FunctionCallExpressionTest
{
    @Test
    public void test_simpleFunctionCall()
    {
        /*  def test_Function(x, y)
         *      return x * y
         */

        String functionName = "test_Function";

        // 1) ===== Must Create a DefineFunction Object first

        // for Define Function Object
        List<String> parameters = new ArrayList<>();
        List<Statement> statementsInFunction = new ArrayList<>();

        // for Function Call Object
        List<Expression> parametersAsExpressions = new ArrayList<>();
        VariableExpression variableX = new VariableExpression("x");
        VariableExpression variableY = new VariableExpression("y");

        //create required list of expressions for FunctionCallExpression constructor
        parametersAsExpressions.add(variableX);
        parametersAsExpressions.add(variableY);
        ProgramState.getProgramState().setVariable("x", 2);
        ProgramState.getProgramState().setVariable("y", 5);

        // Add parameters passed into DefineFunction
        parameters.add("x");
        parameters.add("y");

        // Add statements in Function Scope for Define Function
        ReturnStatement returnStatement = new ReturnStatement(new MultipleExpression(variableX, variableY));
        statementsInFunction.add(returnStatement);

        //create Function Definition + feed to ProgramState
        DefineFunctionStatement functionDefinition = new DefineFunctionStatement(functionName, parameters, statementsInFunction);
        functionDefinition.run();


        // 2) ====== Create and Call FunctionCall
        FunctionCallExpression functionCall = new FunctionCallExpression(functionName, parametersAsExpressions);

        assertThat(functionCall.evaluate(), equalTo(10));
    }
}
