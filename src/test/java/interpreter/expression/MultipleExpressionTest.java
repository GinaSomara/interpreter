package interpreter.expression;

import interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MultipleExpressionTest
{
    @Test
    public void test_constantMultiplication()   // x = 2 * 5
    {
        MultipleExpression expression = new MultipleExpression(new ConstantExpression(2), new ConstantExpression(5));
        assertThat(expression.evaluate(), equalTo(10));
    }

    @Test
    public void test_variableMultiplication()  // z = x * y
    {
        ProgramState.getProgramState().setVariable("x", 20);
        ProgramState.getProgramState().setVariable("y", 1);

        MultipleExpression expression = new MultipleExpression(new VariableExpression("x"), new VariableExpression("y"));
        assertThat(expression.evaluate(), equalTo(20));
    }

    @Test
    public void test_oneConstant_oneVariable()  // z = x * 1;
    {
        ProgramState.getProgramState().setVariable("x", 5);

        MultipleExpression expression = new MultipleExpression(new VariableExpression("x"),  new ConstantExpression(3));

        assertThat(expression.evaluate(), equalTo(15));
    }

    @Test
    public void test_NegativeMultiplication()
    {
        MultipleExpression expression = new MultipleExpression(new ConstantExpression(-10), new ConstantExpression(5));
        assertThat(expression.evaluate(), equalTo(-50));
    }
}
