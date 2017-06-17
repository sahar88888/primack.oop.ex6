package code_elements;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Condition extends Block {

    protected Condition(BufferedReader f, String def_line) throws
            IOException, BadElementException{
        super(f,def_line);
    }

    static Condition createFromLine(BufferedReader f, String line) throws
            BadElementException, IOException{
        Condition elem;
        if((elem = IfCondition.createFromLine(f ,line))==null){
            elem = WhileCondition.createFromLine(f, line);
        }
        return elem;
    }

    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (e instanceof Method){
            throw new BadElementException();
        }
    }
}
