package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class IfStatementTest
{
    @Test
    public void test_ifStatement_True()
    {
        // x = 0
        // if(1 == 1)
        //    x = 2

        ProgramState.getProgramState().setVariable("x", 0);
        VariableExpression variableExpression = new VariableExpression("x");

        // Creating parameters for If Statement constructor
        EqualToCondition equalToCondition = new EqualToCondition(new ConstantExpression(1), new ConstantExpression(1));
        List<Statement> ifBody  = new ArrayList<>();

        ifBody.add(new AssignStatement("x", new ConstantExpression(2)));

        // Create and Run ifStatement
        IfStatement ifStatement = new IfStatement(equalToCondition, ifBody);
        ifStatement.run();

        assertThat(variableExpression.evaluate(), equalTo(2));
    }

    @Test
    public void test_ifStatement_False()
    {
        // x = 0
        // if(1 == 0)     (!)
        //    x = 2

        ProgramState.getProgramState().setVariable("x", 0);
        VariableExpression variableExpression = new VariableExpression("x");

        // Creating parameters for If Statement constructor
        EqualToCondition equalToCondition = new EqualToCondition(new ConstantExpression(1), new ConstantExpression(0));
        List<Statement> ifBody  = new ArrayList<>();

        ifBody.add(new AssignStatement("x", new ConstantExpression(2)));

        // Create and Run ifStatement
        IfStatement ifStatement = new IfStatement(equalToCondition, ifBody);
        ifStatement.run();

        assertThat(variableExpression.evaluate(), equalTo(0));
    }
}
