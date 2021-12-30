package interpreter.expression;


/** Conditional comparison of two expressions that can evaluate to true or false. */
public abstract class Condition
{
    private Expression lhs;
    private Expression rhs;

    public Condition(Expression lhs, Expression rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** Resolves the comparison to true or false based on the lhs and rhs expressions and the operator. */
    public abstract boolean evaluate();

    /**
     * getLhsValue and getRhsValue can be called by the subclasses of Condition that you add, in order to evaluate the
     * lhs and rhs expressions without having direct access to them.
     */
    protected int getLhsValue()
    {
        return lhs.evaluate();
    }

    protected int getRhsValue()
    {
        return rhs.evaluate();
    }
}
