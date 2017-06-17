package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Statement extends CodeElement {

    static CodeElement createFromLine(String line) {
        CodeElement elem;
        if ((elem = VarDeclaration.createFromLine(line)) == null) {
            if ((elem = VarAssignment.createFromLine(line)) == null) {
                elem = MethodCall.createFromLine(line);
            }
        }
        return elem;
    }
}
