package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WhileStatementTest
{
    @Test
    public void test_whileStatement()
    {
        // x = 0
        // while(x < 10)
        //    x = x + 1

        ProgramState.getProgramState().setVariable("x", 0);
        VariableExpression variableExpression = new VariableExpression("x");

        // objects for While Statement constructor
        LessThanCondition lessThanCondition = new LessThanCondition(variableExpression, new ConstantExpression(10));
        List<Statement> whileBody = new ArrayList<>();

        whileBody.add(new AssignStatement("x", new AddExpression(variableExpression, new ConstantExpression(1))));

        WhileStatement whileStatement = new WhileStatement(lessThanCondition, whileBody);
        whileStatement.run();

        assertThat(variableExpression.evaluate(), equalTo(10));
    }
}
