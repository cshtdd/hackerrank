import java.util.HashMap;

public class MakeAnagrams {
    private static class Occurrence {
        public int count1 = 0;
        public int count2 = 0;

        public boolean countsDiffer() { return count1 != count2; }

        public int difference() {
            return Math.abs(count1 - count2);
        }
    }

    static int makeAnagram(String a, String b) {
        HashMap<Integer, Occurrence> characterDistribution = new HashMap<>();

        a.chars()
                .mapToObj(c -> readOccurrence(c, characterDistribution))
                .forEach(o -> o.count1++);

        b.chars()
                .mapToObj(c -> readOccurrence(c, characterDistribution))
                .forEach(o -> o.count2++);

        return characterDistribution.values().stream()
                .filter(Occurrence::countsDiffer)
                .map(Occurrence::difference)
                .reduce(0, Integer::sum);
    }

    private static Occurrence readOccurrence(Integer c, HashMap<Integer, Occurrence> characterDistribution) {
        if (!characterDistribution.containsKey(c)) {
            characterDistribution.put(c, new Occurrence());
        }

        return characterDistribution.get(c);
    }
}
