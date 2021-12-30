//PROVIDED CODE

package interpreter;

import interpreter.statement.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** NOTE: You do NOT have to change any code in this file for Assignment 3. */
public class Interpreter {
    private static final Pattern PRINT_PATTERN = Pattern.compile("^print\\((.+)\\)$");
    private static final Pattern ASSIGN_PATTERN = Pattern.compile("^(.+) = (.+)$");
    private static final Pattern RETURN_PATTERN = Pattern.compile("^return (.+)$");

    private static final Pattern IF_PATTERN = Pattern.compile("^if (.+):$");
    private static final Pattern WHILE_PATTERN = Pattern.compile("^while (.+):$");
    private static final Pattern FOR_PATTERN = Pattern.compile("^for (.+) in range\\((.+), (.+)\\):$");
    private static final Pattern DEFINE_FUNCTION_PATTERN =
            Pattern.compile("^def ([A-Za-z][A-Za-z0-9_]*)\\(([A-Za-z0-9_,\\s]*)\\):$");

    private final List<Statement> statements = new ArrayList<>();

    /**
     * Creates the Interpreter object from an array of Strings representing a program to be run. The constructor will
     * parse the lines into executable Statements, which can be invoked with the runProgram method.
     */
    public Interpreter(List<String> program) {
        Queue<String> lines =
                program.stream()
                        .filter(line -> !line.trim().isEmpty())
                        .filter(line -> !line.startsWith("#"))
                        .collect(Collectors.toCollection(LinkedList::new));

        Parser parser = new Parser();
        while (!lines.isEmpty()) {
            Statement statement = parseStatement(lines, parser, 0);
            statements.add(statement);
        }
    }

    private Statement parseStatement(Queue<String> lines, Parser parser, int indentationLevel) {
        String line = lines.remove();
        if (getIndentationLevel(line) != indentationLevel) {
            throw new RuntimeException("Line with unexpected indentation: " + line.trim());
        }
        line = line.trim();

        // Multi-line statements.
        Matcher ifMatcher = IF_PATTERN.matcher(line);
        if (ifMatcher.matches()) {
            String conditionAsString = ifMatcher.group(1).trim();
            List<Statement> bodyStatements = parseBodyStatements(lines, parser, indentationLevel);
            return parser.createIfStatement(conditionAsString, bodyStatements);
        }
        Matcher whileMatcher = WHILE_PATTERN.matcher(line);
        if (whileMatcher.matches()) {
            String conditionAsString = whileMatcher.group(1).trim();
            List<Statement> bodyStatements = parseBodyStatements(lines, parser, indentationLevel);
            return parser.createWhileStatement(conditionAsString, bodyStatements);
        }
        Matcher forMatcher = FOR_PATTERN.matcher(line);
        if (forMatcher.matches()) {
            String loopVariableName = forMatcher.group(1).trim();
            String rangeStartAsString = forMatcher.group(2).trim();
            String rangeEndAsString = forMatcher.group(3).trim();
            List<Statement> bodyStatements = parseBodyStatements(lines, parser, indentationLevel);
            return parser.createForStatement(loopVariableName, rangeStartAsString, rangeEndAsString, bodyStatements);
        }
        Matcher defineFunctionMatcher = DEFINE_FUNCTION_PATTERN.matcher(line);
        if (defineFunctionMatcher.matches()) {
            String functionName = defineFunctionMatcher.group(1).trim();
            List<String> parameterNames =
                    Arrays.stream(defineFunctionMatcher.group(2).split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
            if (parameterNames.size() == 1 && parameterNames.get(0).isEmpty()) {
                parameterNames.clear();
            }
            for (String parameterName: parameterNames) {
                if (!parameterName.matches("^[A-Za-z][A-Za-z0-9_]*$")) {
                    throw new RuntimeException("Invalid parameter name: " + parameterName);
                }
            }
            List<Statement> bodyStatements = parseBodyStatements(lines, parser, indentationLevel);
            return parser.createDefineFunctionStatement(functionName, parameterNames, bodyStatements);
        }

        Matcher printMatcher = PRINT_PATTERN.matcher(line);
        if (printMatcher.matches()) {
            String expressionAsString = printMatcher.group(1).trim();
            return parser.createPrintStatement(expressionAsString);
        }
        Matcher assignMatcher = ASSIGN_PATTERN.matcher(line);
        if (assignMatcher.matches()) {
            String variableName = assignMatcher.group(1).trim();
            String expressionAsString = assignMatcher.group(2).trim();
            return parser.createAssignStatement(variableName, expressionAsString);
        }
        Matcher returnMatcher = RETURN_PATTERN.matcher(line);
        if (returnMatcher.matches()) {
            String expressionAsString = returnMatcher.group(1).trim();
            return parser.createReturnStatement(expressionAsString);
        }

        throw new RuntimeException("Unrecognized statement: " + line);
    }

    // parseBodyStatements is called when parsing any statement type with multiple lines. It will keep converting lines
    // into Statements and collecting them in a List until it encounters a line with an indentation level to the left of
    // indentationLevel (i.e. unindented from what was expected).
    private List<Statement> parseBodyStatements(Queue<String> lines, Parser parser, int indentationLevel) {
        if (lines.isEmpty()) {
            throw new RuntimeException("Block statement (if, for, while, etc.) found with an empty body.");
        }

        int blockIndentationLevel = getIndentationLevel(lines.element());
        if (blockIndentationLevel <= indentationLevel) {
            throw new RuntimeException(
                    "Expected body of block statement to be further indented, but was not: " + lines.element().trim());
        }

        List<Statement> blockStatements = new ArrayList<>();
        while (!lines.isEmpty()) {
            // We peek at the next line before attempting to parse it, in case it signals the end of this block.
            String nextLine = lines.element();
            int nextIndentationLevel = getIndentationLevel(nextLine);

            // If the next line is unindented, this block is done.
            if (nextIndentationLevel <= indentationLevel) {
                return blockStatements;
            }
            // Otherwise, the next line must match the block indentation level.
            if (nextIndentationLevel != blockIndentationLevel) {
                throw new RuntimeException("Line with unexpected indentation: " + nextLine.trim());
            }

            // If the indentation is valid, we parse the next line as a normal Statement. Note that the next line can
            // itself be a multi-line statement, like an if statement or a while loop.
            blockStatements.add(parseStatement(lines, parser, blockIndentationLevel));
        }

        // If we ran out of lines, the block being parsed finishes at the end of the entire program.
        return blockStatements;
    }

    // Returns the index of the first character in the line that isn't a space character. This will determine how far
    // the line is indented.
    private int getIndentationLevel(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }

    /** Run the parsed program Statements. */
    public void runProgram()
    {
        for (Statement statement: statements)
        {
            statement.run();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Must provide the program file name to run.");
        }
        if (args.length > 1) {
            throw new RuntimeException("Only one argument expected (program file name).");
        }

        ArrayList<String> programLines = new ArrayList<>();
        try {
            String programFileName = args[0];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(programFileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                programLines.add(line);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        Interpreter interpreter = new Interpreter(programLines);
        interpreter.runProgram();
    }
}
