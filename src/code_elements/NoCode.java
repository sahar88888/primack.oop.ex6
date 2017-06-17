package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class NoCode extends CodeElement {

    static public NoCode createFromLine(String line) {
        NoCode elem;
        if ((elem = Empty.createFromLine(line)) == null) {
            elem = Comment.createFromLine(line);
        }
        return elem;
    }

}
