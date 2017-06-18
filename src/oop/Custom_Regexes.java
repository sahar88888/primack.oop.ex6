package oop;

import java.util.regex.Matcher;

import code_elements.BadElementException;
import code_elements.variables.Variable.VarType;

import static code_elements.variables.Variable.VarType.*;

/**
 * A library of  custom regexes for our use.
 */
 public class Custom_Regexes {
    public static String VAR_TYPE = "[int|double|String|boolean|char]";
    public static String VAR_NAME = "[a-zA-Z_][\\w]*";// a pattern for a var name. (e.g. A1, _Zorg_42).
    public static String METHOD_NAME = "[a-zA-Z][\\w]*";// a pattern for a name. (e.g. A1, print_files
    public static String WHITESPACE = "\\s*";

    // strings for variable values:
    public static String INT_VAL = "-?[\\d]*";
    public static String DOUBLE_VAL = INT_VAL+"\\.?"+"[\\d]*";
    public static String STRING_VAL = "\"\\.*\"";
    public static String BOOLEAN_VAL = "true|false|"+DOUBLE_VAL;
    public static String CHAR_VAL = "'\\.'";

    public static String VAL = INT_VAL+"|"+STRING_VAL+"|"+BOOLEAN_VAL+"|"+CHAR_VAL;//general value.
    public static String LOGICAL_VALUE = INT_VAL+"|"+STRING_VAL+"|"+BOOLEAN_VAL;//checkable value.

    public static String ASSIGNMENT = "="+WHITESPACE+VAL;
    public static String LINE_END = "\\.*;\\.*";


   /**
     * a function for cutting a string, with a given matcher - according to its start and end point.
     * @param s the String
     * @param m the matcher
     * @return a matching substring.
     */
    public static String Cut(String s, Matcher m)
    {
        return s.substring(m.start(),m.end());
    }

    /**
     * a g
     * @param valString: the string representing the value.
     * @return the matching type.
     * @throws BadElementException if no type was matched - illegal value.
     */
    public static VarType GetValueType (String valString) throws BadElementException
    {
        if(valString.matches(INT_VAL))
            return INT;
        if(valString.matches(DOUBLE_VAL))
            return DOUBLE;
        if(valString.matches(STRING_VAL))
            return STRING;
        if(valString.matches(BOOLEAN_VAL))
            return BOOLEAN;
        if(valString.matches(CHAR_VAL))
            return CHAR;

        throw new BadElementException(); //no type was matched.
    }
}
