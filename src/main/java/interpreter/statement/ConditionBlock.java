package interpreter.statement;

import interpreter.expression.Condition;

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

    protected boolean evaluateCondition()
    {
        return getCondition().evaluate();
    }

}
