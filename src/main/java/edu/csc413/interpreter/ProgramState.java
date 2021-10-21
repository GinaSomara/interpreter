package edu.csc413.interpreter;

import edu.csc413.interpreter.statement.Statement;
import java.util.*;

/**
 * ProgramState represents the Program's storage information at any point in time while the program is running. It
 * should keep track of all defined variables, as well as their assigned values, and allow the running program to read
 * or set those values.
 */
public class ProgramState {
    // TODO: Implement. Add any instance variables you need.

    public ProgramState() {
        // TODO: Implement. Initialize any instance variables you added.
    }

    /** Returns the integer value associated with the specified variable name in the current call frame. */
    public int getVariable(String variable) {
        // TODO: Implement.
        return 0;
    }

    /** Sets the value for the specified variable name to the specified value in the current call frame. */
    public void setVariable(String variable, int value) {
        // TODO: Implement.
    }

    /** Adds a new, empty call frame to the top of the call stack, making it the new current call frame. */
    public void addCallFrame() {
        // TODO: Implement.
    }

    /**
     * Removes the topmost call frame from the call stack. The current call frame becomes the previous one in the stack.
     */
    public void removeCallFrame() {
        // TODO: Implement.
    }

    /**
     * Registers a function's parameter names and function statements so that they can be looked up later on using just
     * the function name.
     */
    public void registerFunction(String functionName, List<String> parameterNames, List<Statement> functionStatements) {
        // TODO: Implement.
    }

    /** Returns the list of parameter names associated with the specified function name. */
    public List<String> getParameterNames(String functionName) {
        // TODO: Implement.
        return null;
    }

    /** Returns the list of function statements associated with the specified function name. */
    public List<Statement> getFunctionStatements(String functionName) {
        // TODO: Implement.
        return null;
    }

    /** Returns whether or not a return value has been recorded. */
    public boolean hasReturnValue() {
        // TODO: Implement.
        return false;
    }

    /** Returns the recorded return value, if it exists. */
    public int getReturnValue() {
        // TODO: Implement.
        return 0;
    }

    /** Records a return value. hasReturnValue should return true after this method is called. */
    public void setReturnValue(int returnValue) {
        // TODO: Implement.
    }

    /** Clears the recorded return value. hasReturnValue should return false after this method is called. */
    public void clearReturnValue() {
        // TODO: Implement.
    }
}
