package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;
import oop.ex6.main.Program;

import java.io.BufferedReader;
import java.util.ArrayList;

import static oop.Custom_Regexes.CheckMatch;
import static oop.Custom_Regexes.GetSubStrings;
import static oop.Custom_Regexes.VAL;

/**
 * Created by t8431103 on 6/17/2017.
 */
public class MethodCall extends Statement {

    ArrayList<String> params;
    String methodName;
    private MethodCall(String line){
        String[] split = line.split("\\(");
        methodName = split[0];
        params = GetSubStrings(line, VAL);
    }

    static CodeElement createFromLine(BufferedReader f, String line){
        if(CheckMatch(line, Custom_Regexes.METHOD_CALL)){
            return new MethodCall(line);
        }
        return null;
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        Method method = null;
        for (Method m : Program.getMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
            }
        }
        if(method==null){
            throw new BadElementException();
        }
        for (int i = 0; i < params.size(); i++) {
            //check if it's a var name or a value.
            String param = params.get(i);
            if (CheckMatch(param, Custom_Regexes.VAR_NAME
                    + "|" + Custom_Regexes.VAL)) {
                if (CheckMatch(param, Custom_Regexes.VAR_NAME)) {
                    if (!varExists(param, scope_vars)) {
                        throw new BadElementException();
                    }
                }

                if (!(Custom_Regexes.GetValueTypeFromName(param) == method
                        .getParamType(i))){
                    throw new BadElementException();
                }
            } else {
                throw new BadElementException();
            }

        }
    }
}
