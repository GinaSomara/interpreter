package interpreter.expression;

import interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LessThanConditionTest
{
    @Test
    public void test_ConstantLessThan()    // 1 < 5    (!) 5 < 1
    {
        LessThanCondition condition2 = new LessThanCondition(new ConstantExpression(1), new ConstantExpression(5));
        assertThat(condition2.evaluate(), equalTo(true));

        LessThanCondition condition1 = new LessThanCondition(new ConstantExpression(5), new ConstantExpression(1));
        assertThat(condition1.evaluate(), equalTo(false));
    }

    @Test
    public void test_VariableLessThan()  //    x < y    (!) y < x
    {
        ProgramState.getProgramState().setVariable("x", 3);
        ProgramState.getProgramState().setVariable("y", 8);

        VariableExpression expressionX  = new VariableExpression("x");
        VariableExpression expressionY = new VariableExpression("y");

        LessThanCondition condition1 = new LessThanCondition(expressionX, expressionY);
        assertThat(condition1.evaluate(), equalTo(true));

        LessThanCondition condition2 = new LessThanCondition(expressionY, expressionX);
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_oneConstant_oneVariable()  //  x < 5    (!) 5 < x
    {
        ProgramState.getProgramState().setVariable("x", 1);
        VariableExpression expressionX = new VariableExpression("x");
        ConstantExpression expression1 = new ConstantExpression(5);

        LessThanCondition condition1 = new LessThanCondition(expressionX, expression1);
        assertThat(condition1.evaluate(), equalTo(true));

        LessThanCondition condition2 = new LessThanCondition(expression1, expressionX);
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_NegativeValue()   // -3 < -1
    {
        LessThanCondition condition = new LessThanCondition(new ConstantExpression(-3), new ConstantExpression(-1));
        assertThat(condition.evaluate(), equalTo(true));
    }
}
