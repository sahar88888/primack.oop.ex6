package code_elements;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.BufferedReader;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Statement extends CodeElement {
    static String CREATE_REGEX = ".*;";
    static CodeElement createFromLine(BufferedReader f, String line) throws
            BadElementException {
        CodeElement elem;
        if(CodeElement.check_match(line,CREATE_REGEX)){
            if ((elem = VarDeclaration.createFromLine(line)) == null) {
                if ((elem = VarAssignment.createFromLine(line)) == null) {
                    elem = MethodCall.createFromLine(line);
                }
            }
            return elem;
        }
        return null;
    }
}
