package code_elements.variables;

import code_elements.CodeElement;

/**
 * Created by t8417719 on 12/06/2017.
 */
abstract public class Variable extends CodeElement {
    public enum VarType{BOOLEAN,STRING,INT,DOUBLE}
    String name;
    VarType type;
    boolean is_final;
    boolean initialized;

    public Variable() {
    }

    static CodeElement createFromLine(String line){
        // TODO wild sex
    }

    public String getName() {
        return name;
    }

    public VarType getType() {
        return type;
    }

    public boolean is_final() {
        return is_final;
    }

    public boolean isInitialized() {
        return initialized;
    }
}
