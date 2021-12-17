package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.expression.Condition;

import java.util.List;

public abstract class ConditionBlock extends BlockStatement
{
    private Condition condition;

    public ConditionBlock(Condition condition, List<Statement> body)
    {
        super(body);
        this.condition = condition;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public abstract boolean evaluateCondition();

}
