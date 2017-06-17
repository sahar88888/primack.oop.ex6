package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class NoCode extends CodeElement {

    static CodeElement createFromLine(String line) {
        CodeElement elem;
        if ((elem = Empty.createFromLine(line)) == null) {
            elem = Comment.createFromLine(line);
        }
        return elem;
    }
}
