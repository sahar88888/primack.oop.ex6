package oop;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import code_elements.BadElementException;
import code_elements.variables.Variable.VarType;

import static code_elements.variables.Variable.VarType.*;

/**
 * A library of  custom regexes for our use.
 */
 public class Custom_Regexes {
    public static String WHITESPACE = "\\s+";
    public static String P_WHITESPACE= "\\s*"; //stands for possible whitespace.
    public static String VAR_TYPE = "(final)? "+WHITESPACE+"(int|double|String|boolean|char)";
    public static String VAR_NAME = "_?[a-zA-Z_][\\w]*";// a pattern for a var name. (e.g. A1, _Zorg_42).
    public static String VAR_DEClARATION = "("+VAR_TYPE+")"+WHITESPACE+"("+VAR_NAME+")";
    public static String METHOD_NAME = "[a-zA-Z][\\w]*";// a pattern for a name. (e.g. A1, print_files


    // strings for variable values:
    public static String INT_VAL = "-?[\\d]*";
    public static String DOUBLE_VAL = INT_VAL+"\\.?"+"[\\d]*";
    public static String STRING_VAL = "\"\\.*\"";
    public static String BOOLEAN_VAL = "true|false|"+DOUBLE_VAL;
    public static String CHAR_VAL = "'\\.'";

    public static String VAL = INT_VAL+"|"+STRING_VAL+"|"+BOOLEAN_VAL+"|"+CHAR_VAL;//general value.
    public static String LOGICAL_VALUE = INT_VAL+"|"+STRING_VAL+"|"+BOOLEAN_VAL;//checkable value.

    public static String ASSIGNMENT = "("+VAR_NAME+")"+WHITESPACE+"="+WHITESPACE+"("+VAL+")";
    public static String NAME_OR_ASSIGNMENT = "["+VAR_NAME+"|"+ASSIGNMENT+"]";
    public static String LINE_END = WHITESPACE+";"+WHITESPACE;

    public static String CONDITION_NAME = "if|while";
    public static String BOOLEAN_OPERATOR = "[\\|\\| | \\&\\&]";//string for boolean operator.

    public static String METHOD_DECLARATION= "void" + WHITESPACE +
            METHOD_NAME + "\\(("+ WHITESPACE + VAR_TYPE + WHITESPACE + VAR_NAME+")" +
            "*)\\)" + WHITESPACE+"\\{";
    public static String METHOD_CALL = METHOD_NAME + "\\((" +
            "("+WHITESPACE+VAR_NAME+WHITESPACE+")*\\);";


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
     * a function for getting the type of its value from a name.
     * @param valString: the string representing the value.
     * @return the matching type.
     * @throws BadElementException if no type was matched - illegal value.
     */
    public static VarType GetValueTypeFromName(String valString) throws BadElementException
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

    /**
     * getting the matching regex to a given VarType.
     * @param type the VarType.
     * @return the matching regex.
     */
    public static String GetRegexFromVarType(VarType type)
    {
        switch(type)
        {
            case INT: return INT_VAL;
            case DOUBLE: return DOUBLE_VAL;
            case STRING: return STRING_VAL;
            case BOOLEAN: return BOOLEAN_VAL;
            case CHAR: return CHAR_VAL;

            default: return null;//should never get here - we have only our varTypes, and we covered all of them.
        }
    }

    /**
     * find a substring in the string corresponding to a regex.
     * @param regex the regex in question.
     * @param string the string.
     * @return the first substring found matching the regex.
     */
    public static String Find(String regex, String string)
    {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        m.find();
        return Cut(string,m);
    }

    /**
     * @param line string to check.
     * @param regex regex to match.
     * @return whether the whole string matches the regex.
     */
    public static boolean CheckMatch(String line, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        return m.matches();
    }

    public static ArrayList<String> GetSubStrings(String line, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        ArrayList<String> substr = new ArrayList<>();
        while(m.find()){
            substr.add(line.substring(m.start(),m.end()));
        }
        return substr;
    }




}
