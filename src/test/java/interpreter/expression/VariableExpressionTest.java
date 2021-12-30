package interpreter.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import interpreter.ProgramState;
import org.junit.jupiter.api.Test;

public class VariableExpressionTest
{
    @Test
    public void evaluate_positiveValue()
    {
        ProgramState.getProgramState().setVariable("x", 8);
        VariableExpression expression = new VariableExpression("x");

        assertThat(expression.evaluate(), equalTo(8));
    }

    @Test
    public void evaluate_negativeValue()
    {
        ProgramState.getProgramState().setVariable("num", -6);
        VariableExpression expression = new VariableExpression("num");

        assertThat(expression.evaluate(), equalTo(-6));
    }

    @Test
    public void evaluate_multipleVariables()
    {
        ProgramState.getProgramState().setVariable("m", 7);
        ProgramState.getProgramState().setVariable("n", -4);
        VariableExpression expression = new VariableExpression("m");

        assertThat(expression.evaluate(), equalTo(7));
    }

    @Test
    public void evaluate_changedValue()
    {
        ProgramState.getProgramState().setVariable("x", 7);
        ProgramState.getProgramState().setVariable("x", -4);
        VariableExpression expression = new VariableExpression("x");

        assertThat(expression.evaluate(), equalTo(-4));
    }
}
