package oop.ex6.main;
import code_elements.*;
import code_elements.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Program extends Block {

    static ArrayList<Method> methods = new ArrayList<>();

    public Program(BufferedReader f) throws IOException, BadElementException{
        super(f, "");
        this.END_REGEX = null;//programs end with last line.
    }

    @Override
    protected void set_End_Regex() {
        this.END_REGEX = null;
    }

    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (!(e instanceof VarDeclaration || e instanceof Method || e instanceof NoCode)){
            throw new BadElementException();
        }
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        // insert all variables as local, and delete them from 'elements' so
        // they aren't searched in block's is_legal, and they 'exist' for
        // all other lines of code..
        for(int i = 0; i < elements.size(); i++) {
            CodeElement e = elements.get(i);
            if (e instanceof VarDeclaration) {

                e.is_legal(local_vars);
                VarDeclaration declaration = (VarDeclaration) e;
                for (Variable v : declaration.getVars())
                {
                    local_vars.add(v);
                }
                declaration.make_assignments(local_vars);

                elements.remove(e); //was i.
                i--;
            }
            if(e instanceof Method) {
                for(Method m : methods){
                    if(m.getName().equals(((Method) e).getName())){
                        throw new BadElementException();
                    }
                }
                methods.add((Method) e);
            }

        }
        for(CodeElement e : elements){
            e.is_legal(local_vars);
        }
    }

    public static ArrayList<Method> getMethods(){
        return methods;
    }

}
