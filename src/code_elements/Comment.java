package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Comment extends NoCode {
    static String CREATE_REGEX = "^//.*";

    static CodeElement createFromLine(String line) {
        if (CodeElement.check_match(line, CREATE_REGEX)) {
            return new Comment();
        }
        else return null;
    }
}
