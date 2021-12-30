package interpreter.expression;

import interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EqualToConditionTest
{
    @Test
    public void test_ConstantEqualTo()  // 7 == 7   7 != 3
    {
        EqualToCondition condition1 = new EqualToCondition(new ConstantExpression(7), new ConstantExpression(7));
        assertThat(condition1.evaluate(), equalTo(true));

        EqualToCondition condition2 = new EqualToCondition(new ConstantExpression(3), new ConstantExpression(7));
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_variableEqualTo()   // x == y    a != b
    {
        ProgramState.getProgramState().setVariable("x", 5);
        ProgramState.getProgramState().setVariable("y", 5);

        EqualToCondition condition1 = new EqualToCondition(new VariableExpression("x"), new VariableExpression("y"));
        assertThat(condition1.evaluate(), equalTo(true));


        ProgramState.getProgramState().setVariable("a", 1);
        ProgramState.getProgramState().setVariable("b", 2);

        EqualToCondition condition2 = new EqualToCondition(new VariableExpression("a"), new VariableExpression("b"));
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_oneConstant_oneVariable()    //x == 5    x != 1
    {
        ProgramState.getProgramState().setVariable("x", 5);

        EqualToCondition condition1 = new EqualToCondition(new VariableExpression("x"),  new ConstantExpression(5));
        assertThat(condition1.evaluate(), equalTo(true));

        EqualToCondition condition2 = new EqualToCondition(new VariableExpression("x"), new ConstantExpression(1));
        assertThat(condition2.evaluate(), equalTo(false));
    }

    @Test
    public void test_NegativeEqualTo()
    {
        EqualToCondition condition = new EqualToCondition(new ConstantExpression(-1), new ConstantExpression(-1));
        assertThat(condition.evaluate(), equalTo(true));
    }
}
