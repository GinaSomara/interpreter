package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.expression.Condition;

import java.util.List;

public abstract class ConditionBlock extends BlockStatement //implements Statement
{
    private Condition condition;

    public ConditionBlock(List<Statement> body) {
        super(body);
    }

    public abstract boolean evaluateCondition();
}
