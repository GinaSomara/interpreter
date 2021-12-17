package edu.csc413.interpreter.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;


public class AddExpressionTest
{
    /**  */

    @Test
    public void test_constantAddition()   // x = 5 + 3
    {
        AddExpression expression = new AddExpression(new ConstantExpression(5), new ConstantExpression(3));
        assertThat(expression.evaluate(), equalTo(8));
    }

    @Test
    public void test_variableAddition()  // z = x + y
    {
        ProgramState.getProgramState().setVariable("x", 100);
        ProgramState.getProgramState().setVariable("y", 200);

        AddExpression expression = new AddExpression(new VariableExpression("x"), new VariableExpression("y"));
        assertThat(expression.evaluate(), equalTo(300));
    }

    @Test
    public void test_oneConstant_oneVariable()  // z = x + 1;
    {
        ProgramState.getProgramState().setVariable("x", 5);

        AddExpression expression = new AddExpression(new VariableExpression("x"),  new ConstantExpression(1));

        assertThat(expression.evaluate(), equalTo(6));
    }

    @Test
    public void test_NegativeAddition()
    {
        AddExpression expression = new AddExpression(new ConstantExpression(-1), new ConstantExpression(0));
        assertThat(expression.evaluate(), equalTo(-1));

    }
}
