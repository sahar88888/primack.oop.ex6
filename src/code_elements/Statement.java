package code_elements;

import java.io.BufferedReader;

import static oop.Custom_Regexes.CheckMatch;
import static oop.Custom_Regexes.P_WHITESPACE;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Statement extends CodeElement {
    static String CREATE_REGEX = ".*;"+P_WHITESPACE;

    protected Statement(String line){
        super(line);
    }

    static CodeElement createFromLine(BufferedReader f, String line) throws
            BadElementException {
        CodeElement elem;
        if(CheckMatch(line,CREATE_REGEX)){
            if ((elem = VarDeclaration.createFromLine(line)) == null) {
                if ((elem = VarAssignment.createFromLine(line)) == null) {
                    if((elem = Return.createFromLine(line))==null) {
                        elem = MethodCall.createFromLine(f, line);
                    }
                }
            }
            return elem;
        }
        return null;
    }
}
