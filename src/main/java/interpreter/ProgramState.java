//PROVIDED CODE

package interpreter;

import interpreter.statement.Statement;

import java.util.*;

/**
 * ProgramState represents the Program's storage information at any point in time while the program is running. It
 * keeps track of all defined variables, as well as their assigned values, and allow the running program to read
 * or set those values.
 */
public class ProgramState
{
    private Stack<HashMap<String, Integer>> stackVariableScope;
    private HashMap<String, FunctionInformation> functionHashMap;
    private Integer returnValue = null;


    //======= Used to create a static ProgramState that will be used by the whole class ========//
    private static ProgramState programState = new ProgramState();

    private ProgramState()
    {
        stackVariableScope = new Stack<>();
        functionHashMap = new HashMap<>();
        addCallFrame();
    }

    public static ProgramState getProgramState() { return programState;}
    //==========================================================================================//


    /** Returns value associated with the specified variable name in the current call frame. */
    public int getVariable(String variable)
    {
        return stackVariableScope.peek().get(variable);
    }

    /** Sets the value for the specified variable name to the specified value in the current call frame. */
    public void setVariable(String variable, int value)
    {
        stackVariableScope.peek().put(variable, value);
    }

    /** Adds a new, empty call frame to the top of the call stack, making it the new current call frame. */
    public void addCallFrame()
    {
        HashMap<String, Integer> variablesInScope = new HashMap<>();
        stackVariableScope.push(variablesInScope);
    }

    /**
     * Removes the topmost call frame from the call stack. The current call frame becomes the previous one in the stack.
     */
    public void removeCallFrame()
    {
        if(stackVariableScope.isEmpty())
            throw new RuntimeException("Stack is empty! Nothing to pop off.");

        stackVariableScope.pop();
    }

    /**
     * Registers a function's parameter names and function statements so that they can be looked up later on using just
     * the function name.
     */
    public void registerFunction(String functionName, List<String> parameterNames, List<Statement> functionStatements) {
        FunctionInformation functionInformation = new FunctionInformation(parameterNames, functionStatements);
        functionHashMap.put(functionName, functionInformation);
    }
    
    public List<String> getParameterNames(String functionName)
    {
       FunctionInformation tempFunctionInfo = functionHashMap.get(functionName);
       return tempFunctionInfo.getFunctionParameters();
    }

    public List<Statement> getFunctionStatements(String functionName)
    {
        FunctionInformation tempFunctionInfo = functionHashMap.get(functionName);
        return tempFunctionInfo.getFunctionStatements();
    }

    public boolean hasReturnValue()
    {
        return returnValue != null;
    }

    public int getReturnValue()
    {
        if(!hasReturnValue())
            throw new RuntimeException("Cannot retrieve Return Value, return value is NULL");

        return returnValue;
    }

    public void setReturnValue(int returnValue)
    {
        this.returnValue = returnValue;
    }

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
