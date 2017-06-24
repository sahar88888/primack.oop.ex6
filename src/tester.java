/**
 * Created by t8417719 on 13/06/2017.
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static oop.Custom_Regexes.*;
public class tester {
    static String CREATE_REGEX =  P_WHITESPACE +
            "("+P_WHITESPACE+VAR_TYPE+WHITESPACE+
            "("+NAME_OR_ASSIGNMENT+WHITESPACE+","+WHITESPACE+")*"
            +NAME_OR_ASSIGNMENT+P_WHITESPACE +")"+LINE_END;
    static String regex2 = P_WHITESPACE + VAR_TYPE+WHITESPACE+NAME_OR_ASSIGNMENT;

    public static void main(String[] args) {

        String line ="final int bla";
        Pattern p = Pattern.compile(VAR_TYPE);
        Matcher m = p.matcher(line);//searching for the type
        m.find();
        System.out.println(m.group(2));
    }





}
