package edu.csc413.interpreter.expression;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;

/** Unit tests for VariableExpression. Uncomment the lines in the tests once VariableExpression is implemented. */
public class VariableExpressionTest {
    @Test
    public void evaluate_positiveValue() {
        //ProgramState programState = new ProgramState();
        //programState.setVariable("x", 8);
        //VariableExpression expression = new VariableExpression("x");

        //assertThat(expression.evaluate(programState), equalTo(8));
    }

    @Test
    public void evaluate_negativeValue() {
        //ProgramState programState = new ProgramState();
        //programState.setVariable("num", -6);
        //VariableExpression expression = new VariableExpression("num");

        //assertThat(expression.evaluate(programState), equalTo(-6));
    }

    @Test
    public void evaluate_otherVariables() {
        //ProgramState programState = new ProgramState();
        //programState.setVariable("m", 7);
        //programState.setVariable("n", -4);
        //VariableExpression expression = new VariableExpression("m");

        //assertThat(expression.evaluate(programState), equalTo(7));
    }

    @Test
    public void evaluate_changedValue() {
        //ProgramState programState = new ProgramState();
        //programState.setVariable("x", 7);
        //programState.setVariable("x", -4);
        //VariableExpression expression = new VariableExpression("x");

        //assertThat(expression.evaluate(programState), equalTo(-4));
    }
}
