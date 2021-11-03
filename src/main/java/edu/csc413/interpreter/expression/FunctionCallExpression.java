package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.statement.ReturnStatement;
import edu.csc413.interpreter.statement.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FunctionCallExpression implements Expression
{
    private String functionName;
    private List<Expression> parameterValues;
    private HashMap<String, Integer> functionScopeVariablesHM;

    public FunctionCallExpression(String functionName, List<Expression> parameterValues)
    {
        this.functionName = functionName;
        this.parameterValues = parameterValues;
        functionScopeVariablesHM = new HashMap<>();
    }

    @Override
    public int evaluate(ProgramState programState)
    {
        List<Integer> parametersAsInts = getValueOfParameters(programState);
        storeParametersIntoHM(programState, parametersAsInts);

        List<Statement> statements = programState.getFunctionStatements(functionName);
        for(Statement statement : statements)
        {
            statement.run(programState);

            if(statement instanceof ReturnStatement)
            {
                if(programState.hasReturnValue())
                {
                    int returnValue = programState.getReturnValue();
                     programState.clearReturnValue();
                     return returnValue;
                }
                else throw new RuntimeException("Function " + functionName + "contains no/void Return Value.");
            }
        }
        //return should occur in forEach loop
        throw new RuntimeException("Function " + functionName +" contains no Return Statement.");
    }

    private List<Integer> getValueOfParameters(ProgramState programState)
    {
        List<Integer> parametersAsInts = new ArrayList<>();
        for(Expression expressions : parameterValues)
        {
            parametersAsInts.add(expressions.evaluate(programState));
        }
        return parametersAsInts;
    }

    private void  storeParametersIntoHM(ProgramState programState, List<Integer> parameterIntValues)
    {
        Iterator<Integer> itParameterValue = parameterIntValues.iterator();
        Iterator<String> itParameterName = programState.getParameterNames(functionName).iterator();

        while(itParameterName.hasNext() && itParameterValue.hasNext())
        {
            functionScopeVariablesHM.put(itParameterName.next(), itParameterValue.next());
        }

        //ERROR checking//
        if(itParameterName.hasNext())
            throw new RuntimeException("There are more parameter names than values!");
        if(itParameterValue.hasNext())
            throw new RuntimeException("There are more values that parameter names!");
    }
}
