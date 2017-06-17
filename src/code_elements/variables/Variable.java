package code_elements.variables;

import code_elements.CodeElement;

/**
 * Created by t8417719 on 12/06/2017.
 */
abstract public class Variable extends CodeElement {
    enum VarType{BOOLEAN,STRING,INT,DOUBLE}
    String name;
    VarType type;
    Boolean is_final;

    public Variable() {
    }

    static CodeElement createFromLine(String line){
        // TODO wild sex
    }
}
