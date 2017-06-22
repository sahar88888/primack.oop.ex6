package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by t8431103 on 6/17/2017.
 */
public class MethodCall extends Statement {

    String[] varNames;
    String methodName;
    private MethodCall(String line){
        String[] split = line.split("\\(");
        methodName = split[0];
        varNames = (split[1]).split(Custom_Regexes.WHITESPACE);
    }

    static CodeElement createFromLine(BufferedReader f, String line){
        if(CodeElement.check_match(line, Custom_Regexes.METHOD_CALL)){
            return new MethodCall(line);
        }
        return null;
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {

    }
}
