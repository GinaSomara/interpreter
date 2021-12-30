package interpreter.statement;

import interpreter.ProgramState;
import interpreter.expression.*;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReturnStatementTest
{
    @Test
    public void test_Return()  //return 1*2;
    {
        MultipleExpression multipleExpression = new MultipleExpression(new ConstantExpression(1),
                                                                       new ConstantExpression(2));
        //create Return statement and set into ProgramState
        ReturnStatement returnStatement = new ReturnStatement(multipleExpression);
        returnStatement.run();

        assertThat(ProgramState.getProgramState().getReturnValue(), equalTo(2));
    }

    // NEED ANOTHER method to test call stack return statements???????
}
