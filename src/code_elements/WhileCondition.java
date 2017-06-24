package code_elements;

import java.io.BufferedReader;
import java.io.IOException;

import static oop.Custom_Regexes.CheckMatch;
import static oop.Custom_Regexes.P_WHITESPACE;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class WhileCondition extends Condition {

    static String CREATE_REGEX = P_WHITESPACE + "while" + P_WHITESPACE;
    protected WhileCondition(BufferedReader f, String line) throws
            IOException, BadElementException{
        super(f, line);
    }

    static WhileCondition createFromLine(BufferedReader f, String line) throws
            IOException, BadElementException {
        if (CheckMatch(line, CREATE_REGEX)) {
            return new WhileCondition(f, line);
        } else return null;
    }
}
