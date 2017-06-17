package code_elements;

import code_elements.variables.Variable;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Block extends CodeElement {

    static String CREATE_REGEX = ".*\\{";
    static String END_REGEX = "\\}";

    ArrayList<Variable> scope_vars;
    ArrayList<CodeElement> elements;

    protected Block(BufferedReader f) throws IOException, BadElementException{
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

    static Block createFromLine(BufferedReader f,String line){
        Block elem = null;
        if(CodeElement.check_match(line,CREATE_REGEX)) {
            if ((elem = Method.createFromLine(line)) == null) {
                elem = Condition.createFromLine(line);
            }
        }
        return elem;
    }
}
