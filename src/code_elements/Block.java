package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Block extends CodeElement {

    static String CREATE_REGEX = ".*\\{";
    static String END_REGEX = "\\}";

    protected ArrayList<CodeElement> elements;
    String definition_line;
    protected ArrayList<Variable> local_vars;

    protected Block(BufferedReader f, String def_line) throws IOException,
            BadElementException{
        local_vars = new ArrayList<>();
        this.definition_line = def_line;
        String line;
        elements = new ArrayList<>();
        while((line=f.readLine())!=null){
            if(check_match(line,END_REGEX)){ //skipping all lines until end
                break;
            }
            else{
                CodeElement elem = CodeElement.createFromLine(f, line);
                if(elem==null || elem instanceof Method){
                    throw new BadElementException();
                }
                elements.add(elem);
            }
        }
        if(line==null){
            throw new BadElementException();
        }
        for(CodeElement e : elements) {
            // validate that there are only fitting elements inside the block (e.g. a method can't contain methods.)
            checkElementType(e);
        }
    }

    protected abstract void checkElementType(CodeElement e) throws BadElementException;

    static Block createFromLine(BufferedReader f,String line) throws

            IOException, BadElementException{
        Block elem = null;
        if((line,CREATE_REGEX)) {
            if ((elem = Method.createFromLine(f, line)) == null) {
                elem = Condition.createFromLine(f, line);
            }
        }
        return elem;
    }

    /**
     * copying all variables from the father's scope.
     * @throws BadElementException
     */
    private void Copy_vars(ArrayList<Variable> scope_vars) throws BadElementException
    {
        for (Variable var: scope_vars)
            local_vars.add(var.getCopy());
    }

    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException{

        Copy_vars(scope_vars);//copying the vers in the upper scope.
        for(CodeElement e : elements){

            if(e instanceof VarDeclaration){ //declaration
                for(Variable v : ((VarDeclaration) e).getVars()){
                    overrideVarByName(v.getName(),scope_vars);
                }
            }
            e.is_legal(local_vars);
            if(e instanceof VarDeclaration){
                for(Variable v : ((VarDeclaration) e).getVars()){
                    local_vars.add(v);
                }
            }
        }
    }

    protected void overrideVarByName(String name, ArrayList<Variable> scope_vars){
        for(Variable v: scope_vars){
            if(v.getName().equals(name)){
                local_vars.remove(v);
                break; //no more than one.
            }
        }
    }

}
