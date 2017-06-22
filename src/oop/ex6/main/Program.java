package oop.ex6.main;
import code_elements.*;
import code_elements.variables.Variable;
import com.sun.org.apache.bcel.internal.classfile.Code;

import javax.xml.bind.Element;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Program extends Block {

    static ArrayList<Method> methods;

    public Program(BufferedReader f) throws IOException, BadElementException{
        super(f, "");
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
                for (Variable v : ((VarDeclaration) e).getVars()) {
                    local_vars.add(v);
                }
                elements.remove(i);
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
            e.is_legal();
        }
    }

    public static ArrayList<Method> getMethods(){
        return methods;
    }
}
