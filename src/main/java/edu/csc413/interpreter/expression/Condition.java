package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;

/** Conditional comparison of two expressions that can evaluate to true or false. */
public abstract class Condition {
    private Expression lhs;
    private Expression rhs;

    public Condition(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** Resolves the comparison to true or false based on the lhs and rhs expressions and the operator. */
    public abstract boolean evaluate(ProgramState programState);

    /**
     * getLhsValue and getRhsValue can be called by the subclasses of Condition that you add, in order to evaluate the
     * lhs and rhs expressions without having direct access to them.
     */

    protected int getLhsValue(ProgramState programState) {
        return lhs.evaluate(programState);
    }

    protected int getRhsValue(ProgramState programState) {
        return rhs.evaluate(programState);
    }
}
