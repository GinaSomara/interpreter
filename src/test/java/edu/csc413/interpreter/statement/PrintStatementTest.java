package edu.csc413.interpreter.statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.ConstantExpression;
import org.junit.jupiter.api.Test;


public class PrintStatementTest
{
    @Test
    public void printConstant()
    {
        ProgramState programState = new ProgramState();
        PrintStatement printStatement = new PrintStatement(new ConstantExpression(10));

        printStatement.run(programState);
    }
}
