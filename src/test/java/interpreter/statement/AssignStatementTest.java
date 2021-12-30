package interpreter.statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import interpreter.ProgramState;
import interpreter.expression.ConstantExpression;
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
