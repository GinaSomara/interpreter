package edu.csc413.interpreter.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;

public class GreaterThanConditionTest
{
    @Test
    public void test_ConstantGreaterThan()    // 5 > 1      (!) 1 > 5
    {
        GreaterThanCondition condition1 = new GreaterThanCondition(new ConstantExpression(5), new ConstantExpression(1));
        assertThat(condition1.evaluate(), equalTo(true));

        GreaterThanCondition condition2 = new GreaterThanCondition(new ConstantExpression(1), new ConstantExpression(5));
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_VariableGreaterThan()  //    x > y    (!) y < x
    {
        ProgramState.getProgramState().setVariable("x", 20);
        ProgramState.getProgramState().setVariable("y", 10);

        VariableExpression expressionX  = new VariableExpression("x");
        VariableExpression expressionY = new VariableExpression("y");

        GreaterThanCondition condition1 = new GreaterThanCondition(expressionX, expressionY);
        assertThat(condition1.evaluate(), equalTo(true));

        GreaterThanCondition condition2 = new GreaterThanCondition(expressionY, expressionX);
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_oneConstant_oneVariable()  //  x > 1    (!) 1 < x
    {
        ProgramState.getProgramState().setVariable("x", 5);
        VariableExpression expressionX = new VariableExpression("x");
        ConstantExpression expression1 = new ConstantExpression(1);

        GreaterThanCondition condition1 = new GreaterThanCondition(expressionX, expression1);
        assertThat(condition1.evaluate(), equalTo(true));

        GreaterThanCondition condition2 = new GreaterThanCondition(expression1, expressionX);
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_NegativeValue()   // -1 > -5
    {
        GreaterThanCondition condition = new GreaterThanCondition(new ConstantExpression(-1), new ConstantExpression(-5));
        assertThat(condition.evaluate(), equalTo(true));
    }
}
