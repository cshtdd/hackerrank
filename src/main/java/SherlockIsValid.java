import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class SherlockIsValid {
    static String isValid(String s) {
        HashMap<Integer, Integer> characterFrequencies = new HashMap<>();

        s.chars().forEach(c -> increment(c, characterFrequencies));

        Integer[] uniqueFrequencies = readDistinctFrequencyCounts(characterFrequencies);
        if (uniqueFrequencies.length == 1) {
            return "YES";
        }

        int maxFrequency = Arrays.stream(uniqueFrequencies)
                .max(Integer::compareTo)
                .get();

        Integer firstCharacterWithMaxFrequency = characterFrequencies.keySet()
                .stream()
                .filter(i -> characterFrequencies.get(i) == maxFrequency)
                .findFirst()
                .get();
        decrement(firstCharacterWithMaxFrequency, characterFrequencies);

        uniqueFrequencies = readDistinctFrequencyCounts(characterFrequencies);
        if (uniqueFrequencies.length == 1) {
            return "YES";
        }

        increment(firstCharacterWithMaxFrequency, characterFrequencies);

        Optional<Integer> firstCharacterWithMinFrequency = characterFrequencies.keySet()
                .stream()
                .filter(i -> characterFrequencies.get(i) == 1)
                .findFirst();
        if (firstCharacterWithMinFrequency.isPresent()) {
            decrement(firstCharacterWithMinFrequency.get(), characterFrequencies);
        }

        uniqueFrequencies = readDistinctFrequencyCounts(characterFrequencies);
        if (uniqueFrequencies.length == 1) {
            return "YES";
        }

        return "NO";
    }

    private static Integer[] readDistinctFrequencyCounts(HashMap<Integer, Integer> characterFrequencies) {
        return characterFrequencies.values()
                .stream()
                .distinct()
                .toArray(Integer[]::new);
    }

    private static void increment(Integer c, HashMap<Integer, Integer> characterFrequencies) {
        if (!characterFrequencies.containsKey(c)) {
            characterFrequencies.put(c, 1);
        } else {
            int currentValue = characterFrequencies.get(c);
            characterFrequencies.put(c, currentValue + 1);
        }
    }

    private static void decrement(Integer c, HashMap<Integer, Integer> characterFrequencies) {
        int currentValue = characterFrequencies.get(c);
        int updatedValue = currentValue - 1;

        if (updatedValue == 0) {
            characterFrequencies.remove(c);
        } else {
            characterFrequencies.put(c, updatedValue);
        }
    }

    public static void main(String[] args){
        System.out.println(isValid("abc"));
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
    }
}
