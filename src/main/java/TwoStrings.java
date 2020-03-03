import java.util.HashSet;

public class TwoStrings {
    static String twoStrings(String s1, String s2) {
        HashSet<Integer> uniqueCharsS1 = new HashSet<>();

        s1.chars().forEach(uniqueCharsS1::add);

        if (s2.chars().anyMatch(uniqueCharsS1::contains)){
            return "YES";
        }

        return "NO";
    }
}
