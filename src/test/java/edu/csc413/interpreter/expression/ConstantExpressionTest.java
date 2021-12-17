package edu.csc413.interpreter.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;


public class ConstantExpressionTest
{
    @Test
    public void evaluate_positiveValue()
    {
        ConstantExpression expression = new ConstantExpression(5);

        assertThat(expression.evaluate(), equalTo(5));
    }

    @Test
    public void evaluate_negativeValue()
    {
        ConstantExpression expression = new ConstantExpression(-3);

        assertThat(expression.evaluate(), equalTo(-3));
    }

    @Test
    public void evaluate_multipleValues()
    {
        ConstantExpression expressionOne = new ConstantExpression(7);
        ConstantExpression expressionTwo = new ConstantExpression(-100);

        assertThat(expressionTwo.evaluate(), equalTo(-100));
    }
}
