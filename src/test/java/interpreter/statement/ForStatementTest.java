package interpreter.statement;

import interpreter.ProgramState;
import interpreter.expression.*;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class ForStatementTest
{
    @Test
    public void test_simpleForStatement()
    {
        // x = 0
        // for i in range(0,10)
        //        x = x+1

        // expressions used in block statement (inside loop)
        VariableExpression variableExpression = new VariableExpression("x");
        ConstantExpression constantExpression = new ConstantExpression(1);
        ProgramState.getProgramState().setVariable("x", 0);

        // set statement(s) to go inside for loop
        List<Statement> statements = new ArrayList<>();
        statements.add(new AssignStatement("x", new AddExpression(variableExpression, constantExpression)));


        //set for loop iteration variable
        ProgramState.getProgramState().setVariable("i", 0);

        ForStatement forStatement = new ForStatement("i",
                                        new ConstantExpression(0),
                                        new ConstantExpression(10),
                                        statements);
        forStatement.run();

        assertThat(variableExpression.evaluate(), equalTo(10));
    }
}
