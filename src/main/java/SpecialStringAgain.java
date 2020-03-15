public class SpecialStringAgain {
    static long substrCount(int n, String s) {
        int result = 0;

        for (int i = 0; i < n; i++) {
            result++;

            if (i == n - 1) { // don't check anything if there's no next char
                continue;
            }

            int equalCount = 0;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(j) != s.charAt(i)) {
                    break;
                }

                result++;
                equalCount++;
            }

            int halfLength = equalCount + 1;
            int totalLength = (halfLength << 1) + 1;
            if (i + totalLength <= n) { // if there's enough space for the other half and the middle different char
                int matchCount = 0;

                for (int j = i + halfLength + 1; j < i + totalLength; j++) { // check if all chars in the other half match
                    if (s.charAt(j) != s.charAt(i)) {
                        break;
                    }

                    matchCount++;
                }

                if (matchCount == halfLength) {
                    result++;
                }
            }
        }

        return result;
    }
}
