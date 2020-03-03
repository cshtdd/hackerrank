import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> words = new HashMap<String, Integer>();

        for (String w : magazine) {
            increment(words, w);
        }

        for (String s : note) {
            if (!words.containsKey(s)) {
                System.out.println("No");
                return;
            }

            int remaining = decrement(words, s);
            if (remaining < 0){
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    private static Integer increment(Map<String, Integer> dict, String key){
        return change(dict, key, 1);
    }

    private static Integer decrement(Map<String, Integer> dict, String key){
        return change(dict, key, -1);
    }

    private static Integer change(Map<String, Integer> dict, String key, int delta){
        int value = 0;
        if (dict.containsKey(key)){
            value = dict.get(key);
        }

        value += delta;

        dict.put(key, value);
        return value;
    }


}
