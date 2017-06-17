package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Condition extends Block {

    static CodeElement createFromLine(String line){
        CodeElement elem;
        if((elem = IfCondition.createFromLine(line))==null){
            elem = WhileCondition.createFromLine(line);
        }
        return elem;
    }
}
