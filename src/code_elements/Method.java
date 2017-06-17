package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Method extends Block {

    String line;

    static String CREATE_REGEX = "void.*"+ Custom_Regexes.METHOD_NAME+"\\(" +
            "("+Custom_Regexes.VAR_TYPE+" "+Custom_Regexes.VAR_NAME+ ")*\\)" +
            "\\{";

    protected Method(BufferedReader f, String line) throws IOException,
            BadElementException{
        super(f,line);
        this.line = line;
    }

    static Method createFromLine(BufferedReader f, String line)  throws
            IOException, BadElementException{
        if(CodeElement.check_match(line,CREATE_REGEX)){
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
}
