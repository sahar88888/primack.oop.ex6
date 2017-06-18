package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Condition extends Block {

    static public String CREATE_REGEX = "blablabla"; //TODO fix
    static Variable.VarType[] boolTypes = new Variable.VarType[]{Variable.VarType.INT,
            Variable.VarType.BOOLEAN, Variable.VarType.DOUBLE};

    protected Condition(BufferedReader f, String def_line)  throws
            IOException, BadElementException{
        super(f,def_line);
    }

    static Condition createFromLine(BufferedReader f, String line) throws
            BadElementException, IOException{
        Condition elem = null;
        if(CodeElement.check_match(line, CREATE_REGEX)) {
            if ((elem = IfCondition.createFromLine(f, line))  ==
                    null) {
                elem = WhileCondition.createFromLine(f, line);
            }
        }
        return elem;
    }

    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (e instanceof Method){
            throw new BadElementException();
        }
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws
            BadElementException {
        String[] statements = ((this.definition_line.split("\\)")[0]).split
                ("\\(")[1]).split("logical operators");
        for(String statement : statements){
            boolean valid = false;
            if(CodeElement.check_match(statement,Custom_Regexes.LOGICAL_VALUE)){
                valid = true;
            }
            else if(CodeElement.check_match(statement, Custom_Regexes
                    .VAR_NAME)){
                //this is a variable name, and we need to check if it exists
                // within scope.
                for(Variable v : scope_vars){
                    if(v.getName().equals(statement)){
                        if(v.isInitialized()) {
                            for (Variable.VarType t : boolTypes) {
                                if (t == v.getType()) {
                                    valid = true;
                                }
                            }
                        }
                    }
                }
            }
            if(!valid){
                throw new BadElementException();
            }
        }
        super.is_legal();
    }
}
