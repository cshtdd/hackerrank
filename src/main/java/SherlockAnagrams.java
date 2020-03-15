import java.util.*;

public class SherlockAnagrams {
    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("mom") + " == 2");
        System.out.println(sherlockAndAnagrams("abba") + " == 4");
        System.out.println(sherlockAndAnagrams("abcd") + " == 0");
        System.out.println(sherlockAndAnagrams("ifailuhkqq") + " == 3");
        System.out.println(sherlockAndAnagrams("kkkk") + " == 10");
        System.out.println(sherlockAndAnagrams("cdcd") + " == 5");
    }

    static int sherlockAndAnagrams(String str) {
        String[] substrings = substrings(str)
                .stream()
                .map(SherlockAnagrams::sort)
                .toArray(String[]::new);

        HashMap<String, Integer> substringFrequencies = new HashMap<>();

        Arrays.stream(substrings).forEach(s -> count(s, substringFrequencies));

        return substringFrequencies.values()
                .stream()
                .filter(c -> c > 0)
                .map(c -> c + 1)
                .map(c -> c * (c - 1) / 2)
                .reduce(0, Integer::sum);
    }

    private static void count(String s, HashMap<String, Integer> frequencies) {
        if (!frequencies.containsKey(s)) {
            frequencies.put(s, 0);
        } else {
            int currentValue = frequencies.get(s);
            frequencies.put(s, currentValue + 1);
        }
    }

    private static List<String> substrings(String s) {
        List<String> result = new ArrayList<>();

        for (int length = 1; length < s.length(); length++) {
            for (int i = 0; i + length <= s.length(); i++) {
                String substring = s.substring(i, i + length);
                result.add(substring);
            }
        }

        return result;
    }

    private static String sort(String s) {
        StringBuilder result = new StringBuilder();

        s.chars()
                .mapToObj(c -> (char) c)
                .sorted()
                .forEach(result::append);

        return result.toString();
    }
}
