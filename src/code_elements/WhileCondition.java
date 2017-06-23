package code_elements;

import java.io.BufferedReader;
import java.io.IOException;

import static oop.Custom_Regexes.CheckMatch;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class WhileCondition extends Condition {

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
