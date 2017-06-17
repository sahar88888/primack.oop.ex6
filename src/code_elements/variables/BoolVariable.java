package code_elements.variables;


import code_elements.CodeElement;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class BoolVariable extends Variable {

    public BoolVariable() {
        this.type = VarType.BOOLEAN;
    }

    static CodeElement createFromLine(String line){
        // TODO wild sex
        return new BoolVariable();
    }
}
