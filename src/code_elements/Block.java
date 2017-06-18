package code_elements;

import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Block extends CodeElement {

    static String CREATE_REGEX = ".*\\{";
    static String END_REGEX = "\\}";

    ArrayList<CodeElement> elements;
    String definition_line;

    protected Block(BufferedReader f, String def_line) throws IOException,
            BadElementException{
        this.definition_line = def_line;
        String line;
        elements = new ArrayList<>();
        while((line=f.readLine())!=null){
            if(check_match(line,END_REGEX)){
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
        if(CodeElement.check_match(line,CREATE_REGEX)) {
            if ((elem = Method.createFromLine(f, line)) == null) {
                elem = Condition.createFromLine(f, line);
            }
        }
        return elem;
    }

    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException{
        ArrayList<Variable> vars = new ArrayList<>();
        for(Variable v2: scope_vars){
            vars.add(v2);
        }
        for(CodeElement e : elements){
            e.is_legal(vars);
            if(e instanceof VarDeclaration){
                for(Variable v : ((VarDeclaration) e).getVars()){
                    for(Variable v2: scope_vars){
                        if(v.getName().equals(v2.getName())){
                            vars.remove(v2); //override outer scope variable
                            break;
                        }
                    }
                    for(Variable v2: vars){
                        if(v.getName().equals(v2.getName())){
                            throw new BadElementException(); //two variables
                            // with same name, declared in this scope!
                        }
                    }
                    vars.add(v);
                }
            }
        }
    }
}
