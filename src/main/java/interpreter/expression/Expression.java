package interpreter.expression;

/**
 * An expression that can be evaluated by the interpreter to an integer value. Expressions may consist of integer
 * constants, references to variables, or combinations of those using arithmetic operators.
 */
public interface Expression
{
    /**
     * evaluate accepts a ProgramState parameter. If the expression needs to look up variable or function information to
     * be evaluated, it can do so by calling methods on programState.
     */
    int evaluate();
}
