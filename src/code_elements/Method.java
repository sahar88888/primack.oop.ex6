package code_elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static oop.Custom_Regexes.*;


/**
 * Created by t8417719 on 12/06/2017.
 */
public class Method extends Block {

    String line;
    ArrayList<Variable.VarType> paramTypes;
    String name;
    protected Method(BufferedReader f, String line) throws IOException,
            BadElementException{
        super(f,line);
        if(!(elements.get(elements.size()-1) instanceof Return)){
            throw new BadElementException();
        }
        this.line = line;
        paramTypes = new ArrayList<>();
    }

    static Method createFromLine(BufferedReader f, String line)  throws
            IOException, BadElementException{
        if(CheckMatch(line,METHOD_DECLARATION)){
            return new Method(f, line);
        }
        else return null;
    }


    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (e instanceof Method){
            throw new BadElementException();
        }
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        for(String sub : GetSubStrings(line,PARAM_REGEX)){
            sub = sub+";";
            // add parameters as variables
            VarDeclaration var_dec = VarDeclaration.createFromLine(sub);
            for(Variable v: var_dec.getVars()) {
                v.assign(ExampleString(v.getType()));
                paramTypes.add(v.getType());
            }
            elements.add(0,var_dec); // add var declaration like it is in
            // the first line.
        }
        super.is_legal(scope_vars);
    }

    public Variable.VarType getParamType(int index){
        return paramTypes.get(index);
    }

    public String getName(){
        return name;
    }
}
