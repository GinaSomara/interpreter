package interpreter.expression;

import interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DivideExpressionTest
{
    @Test
    public void test_constantDivision()   // x = 6 / 3
    {
        DivideExpression expression = new DivideExpression(new ConstantExpression(6), new ConstantExpression(3));
        assertThat(expression.evaluate(), equalTo(2));
    }

    @Test
    public void test_variableDivision()  // z = x / y
    {
        ProgramState.getProgramState().setVariable("x", 200);
        ProgramState.getProgramState().setVariable("y", 100);

        DivideExpression expression = new DivideExpression(new VariableExpression("x"), new VariableExpression("y"));
        assertThat(expression.evaluate(), equalTo(2));
    }

    @Test
    public void test_oneConstant_oneVariable()  // z = x / 1;
    {
        ProgramState.getProgramState().setVariable("x", 15);

        DivideExpression expression = new DivideExpression(new VariableExpression("x"),  new ConstantExpression(3));

        assertThat(expression.evaluate(), equalTo(5));
    }

    @Test
    public void test_NegativeDivision()  // z = -10 / 5
    {
        DivideExpression expression = new DivideExpression(new ConstantExpression(-10), new ConstantExpression(5));
        assertThat(expression.evaluate(), equalTo(-2));
    }

    //NEED SOMETHING FOR DIVISION BY 0?
}