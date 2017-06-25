package code_elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static oop.Custom_Regexes.CheckMatch;
import static oop.Custom_Regexes.P_WHITESPACE;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Block extends CodeElement {

    static String CREATE_REGEX = ".*\\{" + P_WHITESPACE;
    protected String END_REGEX;

    protected ArrayList<CodeElement> elements;
    protected ArrayList<Variable> local_vars;

    protected Block(BufferedReader f, String def_line) throws IOException,
            BadElementException{
        super(def_line);
        this.set_End_Regex();
        local_vars = new ArrayList<>();
        String line;
        elements = new ArrayList<>();

        while((line=f.readLine())!=null){

            if(Strings_match(line, this.END_REGEX)){ //skipping all lines until end
                break;
            }

            else{
                CodeElement elem = CodeElement.createFromLine(f, line);
                if(elem==null){
                    throw new BadElementException();
                }
                elements.add(elem);
            }
        }
        if(!Strings_match(line, this.END_REGEX)){ //if the end wasn't the expected closing line.
            throw new BadElementException("matching closing line not found.");
        }
        for(CodeElement e : elements) {
            // validate that there are only fitting elements inside the block (e.g. a method can't contain methods.)
            checkElementType(e);
        }
    }

    /**
     * A method for setting the regex ending the block. by default a "}", but Program overrides it with null.
     */
    protected void set_End_Regex() {
        this.END_REGEX = "\\}" + P_WHITESPACE;
    }

    protected abstract void checkElementType(CodeElement e) throws BadElementException;

    static Block createFromLine(BufferedReader f,String line) throws

            IOException, BadElementException{
        Block elem = null;
        if(CheckMatch(line,CREATE_REGEX)) {
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
        Copy_vars(scope_vars);//copying the vars in the upper scope.

        for(CodeElement e : elements){

            if(e instanceof VarDeclaration){ //declaration
                VarDeclaration declaration = (VarDeclaration)e;
                for(Variable v : declaration.getVars()){
                    overrideVarByName(v.getName(),scope_vars);
                }
            }

            e.is_legal(local_vars);

            if(e instanceof VarDeclaration){
                for(Variable v : ((VarDeclaration) e).getVars()){
                    local_vars.add(v);
                }
                ((VarDeclaration) e).make_assignments(scope_vars);
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

    /**
     * checking if a string and a regex match. different from string.matches by handling the null case.
     * @param s1 the string
     * @param regex the regex.
     * @return
     */
    static boolean Strings_match(String s1, String regex)
    {
        if (s1==null)
            return (regex==null);
        return s1.matches(regex);
    }

}
