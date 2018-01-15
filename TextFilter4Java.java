import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词过滤屏蔽
 * @see #parse 直接调用此方法后返回字符串 星号*替换原敏感词
 * NOTE:
 * 1.手动添加敏感词追加到R.raw.wd.txt的时候注意编码转换成UTF-8
 * @author gallon on 2018/1/15.
 */
public class TextFilter4Java {

    public static void main(String[] args) {
        String result = parse("你好3P好好3p好好");
        System.out.println(result);
    }

    private static List<String> list = new ArrayList<>();

    static {
        try {
            FileReader fr = new FileReader(new File("src/wd.txt"));
            BufferedReader bf = new BufferedReader(fr);
            String line = "";
            while (line != null) {
                line = bf.readLine();
                if (line != null && !line.equals("")) {
                    list.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] replacement = new String[]{"", "*", "**", "***", "****", "*****", "******", "*******", "********", "*********", "**********"};

    public static String parse(String content) {
        for (String s : list) {
            int length = s.length();
            if (length >= replacement.length) continue;
            content = content.replace(s.toUpperCase(), replacement[length]);
            content = content.replace(s.toLowerCase(), replacement[length]);
        }
        return content;
    }

}
