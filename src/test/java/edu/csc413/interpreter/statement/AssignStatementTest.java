package edu.csc413.interpreter.statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for AssignStatement. Uncomment the lines in the tests once AssignStatement is implemented. I encourage you
 * to add tests of your own as well.
 */
public class AssignStatementTest {
    @Test
    public void run_newVariable() {
        //ProgramState programState = new ProgramState();
        //AssignStatement statement = new AssignStatement("x", 5);

        //statement.run(programState);

        //assertThat(programState.getVariable("x"), equalTo(5));
    }

    @Test
    public void run_changedVariable() {
        //ProgramState programState = new ProgramState();
        //programState.setVariable("x", 5);
        //AssignStatement statement = new AssignStatement("x", 8);

        //statement.run(programState);

        //assertThat(programState.getVariable("x"), equalTo(8));
    }
}
