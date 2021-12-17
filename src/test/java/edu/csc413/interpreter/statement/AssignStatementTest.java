package edu.csc413.interpreter.statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.ConstantExpression;
import org.junit.jupiter.api.Test;


public class AssignStatementTest {
    @Test
    public void run_newVariable() {;
        AssignStatement statement = new AssignStatement("x", new ConstantExpression(5));

        statement.run();

        assertThat(ProgramState.getProgramState().getVariable("x"), equalTo(5));
    }

    @Test
    public void run_changedVariable() {
        ProgramState.getProgramState().setVariable("x", 5);
        AssignStatement statement = new AssignStatement("x", new ConstantExpression(8));

        statement.run();

        assertThat(ProgramState.getProgramState().getVariable("x"), equalTo(8));
    }
}
