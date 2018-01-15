import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.util.List;

/**
 * 敏感词过滤
 * @author gallon on 2018/1/15.
 */
public class TextFilter {

    public static void main(String[] args) {
        String result = parse("你好好好3p好好");
        System.out.println(result);
    }

    private static String[] replacement = new String[]{"", "*", "**", "***", "****", "*****", "******", "*******", "********", "*********", "**********"};

    public static String parse(String content) {
        List<String> list = Word.list;
        for (String s : Word.list) {
            int length = s.length();
            if (length >= replacement.length) continue;
            content = content.replace(s.toUpperCase(), replacement[length]);
            content = content.replace(s.toLowerCase(), replacement[length]);
        }
        return content;
    }

}
