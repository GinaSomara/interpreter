package interpreter.statement;

import static org.hamcrest.MatcherAssert.assertThat;

import interpreter.expression.ConstantExpression;
import org.junit.jupiter.api.Test;


public class PrintStatementTest
{
    @Test
    public void printConstant()
    {
        PrintStatement printStatement = new PrintStatement(new ConstantExpression(10));

        printStatement.run();
    }
}
