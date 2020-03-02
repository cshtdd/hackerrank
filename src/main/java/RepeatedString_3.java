public class RepeatedString_3 {
    static long repeatedString(String s, long n) {
        if (s.chars().allMatch(c -> c == 'a')){
            return n;
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            int index = i % s.length();
            if ('a' == s.charAt(index)){
                result++;
            }
        }

        return result;
    }
}
