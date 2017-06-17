package oop.ex6.main;
import code_elements.*;
import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Program extends Block {

    static ArrayList<Variable> GlobalVars;
    static ArrayList<String> methodNames;

    public Program(BufferedReader f) throws IOException, BadElementException{
        super(f);
    }

    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (!(e instanceof VarDeclaration || e instanceof Method || e instanceof NoCode)){
            throw new BadElementException();
        }
    }
}
