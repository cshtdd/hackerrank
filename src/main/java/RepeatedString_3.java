import java.util.Arrays;

public class RepeatedString_3 {
    static long repeatedString(String s, long n) {
        int[] chars = s.chars().toArray();
        int length = chars.length;

        if (Arrays.stream(chars).allMatch(c -> c == 'a')){
            return n;
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            int index = i % length;
            if ('a' == chars[index]){
                result++;
            }
        }

        return result;
    }
}
