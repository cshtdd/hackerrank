import java.util.HashMap;

public class SherlockIsValid {
    static String isValid(String s) {
        HashMap<Integer, Integer> characterFrequencies = new HashMap<>();

        s.chars().forEach(c -> {
            count(c, characterFrequencies);
        });

        Integer[] uniqueCounts = characterFrequencies.values().stream()
                .distinct()
                .toArray(Integer[]::new);

        if (uniqueCounts.length == 1){
            return "YES";
        }

        if (uniqueCounts.length != 2){
            return "NO";
        }

        int countDifference = Math.abs(uniqueCounts[0] - uniqueCounts[1]);

        if (countDifference != 1){
            return "NO";
        }

        int maxCount = Math.max(uniqueCounts[0], uniqueCounts[1]);

        long maxOccurrenceCount = characterFrequencies.values().stream()
                .filter(i -> i == maxCount)
                .count();

        if (maxOccurrenceCount == 1){
            return "YES";
        }

        return "NO";
    }

    private static void count(Integer c, HashMap<Integer, Integer> characterFrequencies) {
        if (!characterFrequencies.containsKey(c)) {
            characterFrequencies.put(c, 0);
        }

        int currentValue = characterFrequencies.get(c);
        characterFrequencies.put(c, currentValue + 1);
    }
}
