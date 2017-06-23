package code_elements;

import oop.ex6.main.Program;

import java.io.BufferedReader;
import java.util.ArrayList;

import static oop.Custom_Regexes.*;

/**
 * Created by t8431103 on 6/17/2017.
 */
public class MethodCall extends Statement {

    ArrayList<String> params;
    String methodName;
    protected MethodCall(String line){
        super(line);
        String[] split = line.split("\\(");
        methodName = split[0];
        params = GetSubStrings(line, VAL);
    }

    static CodeElement createFromLine(BufferedReader f, String line){
        if(CheckMatch(line, METHOD_CALL)){
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
        if(method!=null){
            for (int i = 0; i < params.size(); i++) {
                //check if it's a var name or a value.
                String param = params.get(i);
                Variable.VarType type = GetExpressionType(param,scope_vars);
                if(type!=method.getParamType(i)){
                    throw new BadElementException();
                }
            }
        }
        else{
            throw new BadElementException();
        }
    }

}
