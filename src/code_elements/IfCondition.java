package code_elements;

import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static oop.Custom_Regexes.CheckMatch;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class IfCondition extends Condition {

    static String CREATE_REGEX = "if.*";

    protected IfCondition(BufferedReader f, String def_line) throws
            IOException, BadElementException{
        super(f,def_line);
    }

    static IfCondition createFromLine(BufferedReader f, String line)  throws
            IOException, BadElementException {
        if(CheckMatch(line,CREATE_REGEX)){
            return new IfCondition(f, line);
        }
        else return null;
    }
}
