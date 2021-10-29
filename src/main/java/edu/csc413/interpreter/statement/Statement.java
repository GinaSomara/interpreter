//PROVIDED CODE

package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;

/**
 * A single runnable program statement. All Statements must support the run method, which defines what happens when the
 * statement is run by the Interpreter. Running a statement may lead to side effects, such as variables being updated or
 * values being printed to the console. A statement may also be a compound statement, where running that statement leads
 * to several statements being run (for example, an if statement with multiple statements in its body).
 */
public interface Statement {
    /**
     * run accepts a ProgramState parameter. If the statement needs to look up variable or function information when
     * being run, it can do so by calling methods on programState.
     */
    void run(ProgramState programState);
}
