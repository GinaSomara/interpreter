package edu.csc413.interpreter.statement;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockStatement implements Statement
{
    private List<Statement> body;

    public BlockStatement(List<Statement> body)
    {
        this.body = body;
    }

    public abstract void runBlock();
}
