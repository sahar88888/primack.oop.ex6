package oop;

/**
 * A library of  custom regexes for our use.
 */
 public class Custom_Regexes {
    public static String VAR_TYPE = "[int|double|String|boolean|char]";
    public static String VAR_NAME = "[a-zA-Z_][\\w]*";// a pattern for a var name. (e.g. A1, _Zorg_42).
    public static String METHOD_NAME = "[a-zA-Z][\\w]*";// a pattern for a name. (e.g. A1, print_files
}
