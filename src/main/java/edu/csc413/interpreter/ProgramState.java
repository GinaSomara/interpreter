//PROVIDED CODE

package edu.csc413.interpreter;

import edu.csc413.interpreter.statement.Statement;
import java.util.HashMap;
import java.util.List;

/**
 * ProgramState represents the Program's storage information at any point in time while the program is running. It
 * should keep track of all defined variables, as well as their assigned values, and allow the running program to read
 * or set those values.
 */
public class ProgramState
{
    private HashMap<String, Integer> variableHashMap;
    private HashMap<String, FunctionInformation> functionHashMap;
    private Integer returnValue = null;

    public ProgramState()
    {
        variableHashMap = new HashMap<>();
        functionHashMap = new HashMap<>();

    }

    /** Returns the integer value associated with the specified variable name in the current call frame. */
    public int getVariable(String variable)
    {
        return variableHashMap.get(variable);
    }

    /** Sets the value for the specified variable name to the specified value in the current call frame. */
    public void setVariable(String variable, int value)
    {
        variableHashMap.put(variable, value);
    }

    /** Adds a new, empty call frame to the top of the call stack, making it the new current call frame. */
    public void addCallFrame()
    {
        // TODO: Implement.
    }

    /**
     * Removes the topmost call frame from the call stack. The current call frame becomes the previous one in the stack.
     */
    public void removeCallFrame()
    {
        // TODO: Implement.
    }

    /**
     * Registers a function's parameter names and function statements so that they can be looked up later on using just
     * the function name.
     */
    public void registerFunction(String functionName, List<String> parameterNames, List<Statement> functionStatements) {
        FunctionInformation functionInformation = new FunctionInformation(parameterNames, functionStatements);
        functionHashMap.put(functionName, functionInformation);
    }

    /** Returns the list of parameter names associated with the specified function name. */
    public List<String> getParameterNames(String functionName)
    {
       FunctionInformation tempFunctionInfo = functionHashMap.get(functionName);
       return tempFunctionInfo.getFunctionParameters();
    }

    /** Returns the list of function statements associated with the specified function name. */
    public List<Statement> getFunctionStatements(String functionName)
    {
        FunctionInformation tempFunctionInfo = functionHashMap.get(functionName);
        return tempFunctionInfo.getFunctionStatements();
    }

    /** Returns whether or not a return value has been recorded. */
    public boolean hasReturnValue()
    {
        if(returnValue == null)
            return false;
        else return true;
    }

    /** Returns the recorded return value, if it exists. */
    public int getReturnValue()
    {
        return returnValue;
    }

    /** Records a return value. hasReturnValue should return true after this method is called. */
    public void setReturnValue(int returnValue)
    {
        this.returnValue = returnValue;
    }

    /** Clears the recorded return value. hasReturnValue should return false after this method is called. */
    public void clearReturnValue()
    {
        returnValue = null;
    }

 //=================================================================================//
    private static class FunctionInformation
 {
        List<String> parameterNames;
        List<Statement> functionStatements;

        FunctionInformation(List<String> parameterNames, List<Statement> functionStatements)
        {
            this.parameterNames = parameterNames;
            this.functionStatements = functionStatements;
        }

        List<String> getFunctionParameters()
        {
            return this.parameterNames;
        }

        List<Statement> getFunctionStatements()
        {
            return this.functionStatements;
        }
    }
}
