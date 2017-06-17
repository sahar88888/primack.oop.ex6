package oop;

import java.util.regex.Matcher;

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
}
