public class RepeatedString_3 {
    static long repeatedString(String s, long n) {
        long countInString = s.chars().filter(c -> c == 'a').count();
        long fullStringCount = n / s.length();
        long result = fullStringCount * countInString;

        long reminderCount = n % s.length();
        for (int i = 0; i < reminderCount; i++) {
            if (s.charAt(i) == 'a'){
                result++;
            }
        }

        return result;
    }
}
