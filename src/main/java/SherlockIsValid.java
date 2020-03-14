import java.util.HashMap;
import java.util.Optional;

public class SherlockIsValid {
    static String isValid(String s) {
        HashMap<Integer, Integer> characterFrequencies = new HashMap<>();

        s.chars().forEach(c -> increment(c, characterFrequencies));

        if (allCharsHaveTheSameFrequency(characterFrequencies)) {
            return "YES";
        }

        int maxFrequency = characterFrequencies.values()
                .stream()
                .max(Integer::compareTo)
                .get();

        Integer maxChar = firstCharacterWithFrequency(maxFrequency, characterFrequencies).get();
        decrement(maxChar, characterFrequencies);

        if (allCharsHaveTheSameFrequency(characterFrequencies)) {
            return "YES";
        }

        increment(maxChar, characterFrequencies);

        Optional<Integer> minChar = firstCharacterWithFrequency(1, characterFrequencies);
        if (minChar.isPresent()) {
            decrement(minChar.get(), characterFrequencies);
        }

        if (allCharsHaveTheSameFrequency(characterFrequencies)) {
            return "YES";
        }

        return "NO";
    }

    private static Optional<Integer> firstCharacterWithFrequency(int value, HashMap<Integer, Integer> characterFrequencies) {
        return characterFrequencies.keySet()
                .stream()
                .filter(i -> characterFrequencies.get(i) == value)
                .findFirst();
    }

    private static boolean allCharsHaveTheSameFrequency(HashMap<Integer, Integer> characterFrequencies){
        Integer[] uniqueFrequencies = readDistinctFrequencyCounts(characterFrequencies);
        return uniqueFrequencies.length == 1;
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
