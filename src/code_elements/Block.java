package code_elements;

import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Block extends CodeElement {

    static String CREATE_REGEX = "\\(.*\\)\\s*\\{";

    ArrayList<Variable> scope_vars;
    ArrayList<CodeElement> elements;

    static CodeElement createFromLine(BufferedReader f,String line){
        CodeElement elem = null;
        if(CodeElement.check_match(line,CREATE_REGEX)) {
            if ((elem = Method.createFromLine(line)) == null) {
                elem = Condition.createFromLine(line);
            }
        }
        return elem;
    }
}
