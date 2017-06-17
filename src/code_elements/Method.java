package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Method extends Block {

    static String CREATE_REGEX = "void.*";

    static CodeElement createFromLine(String line){
        if(CodeElement.check_match(line,CREATE_REGEX)){
            return new Method();
        }
        else return null;
    }
}
