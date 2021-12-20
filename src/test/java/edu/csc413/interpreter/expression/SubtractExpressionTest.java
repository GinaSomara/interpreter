package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SubtractExpressionTest
{
    @Test
    public void test_constantSubtraction()   // x = 5 - 3
    {
        SubtractExpression expression = new SubtractExpression(new ConstantExpression(5), new ConstantExpression(3));
        assertThat(expression.evaluate(), equalTo(2));
    }

    @Test
    public void test_variableSubtraction()  // z = x - y
    {
        ProgramState.getProgramState().setVariable("x", 20);
        ProgramState.getProgramState().setVariable("y", 5);

        SubtractExpression expression = new SubtractExpression(new VariableExpression("x"), new VariableExpression("y"));
        assertThat(expression.evaluate(), equalTo(15));
    }

    @Test
    public void test_oneConstant_oneVariable()  // z = x - 1;
    {
        ProgramState.getProgramState().setVariable("x", 5);

        SubtractExpression expression = new SubtractExpression(new VariableExpression("x"),  new ConstantExpression(1));

        assertThat(expression.evaluate(), equalTo(4));
    }

    @Test
    public void test_NegativeSubtraction()   // z = -1 - -3
    {
        SubtractExpression expression = new SubtractExpression(new ConstantExpression(-1), new ConstantExpression(-3));
        assertThat(expression.evaluate(), equalTo(2));
    }
}
