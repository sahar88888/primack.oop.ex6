package code_elements;

import java.io.Reader;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class CodeElement {
    CodeElement parent;
    String src_str;
    CodeElement(Reader f){

    }
    public abstract boolean is_legal();

    static CodeElement createFromLine(String line){
        // TODO wild sex
    }

}
