import java.util.HashMap;
import java.util.Optional;

public class NewYearChaos {
    public static void main(String[] args) {
        minimumBribes(new int[]{2, 1, 5, 3, 4});
        minimumBribes(new int[]{2, 5, 1, 3, 4});
    }

    static void minimumBribes(int[] q) {
        HashMap<Integer, Integer> rotations = new HashMap<Integer, Integer>();

        for (int i = 0; i < q.length - 1; i++) {
            int currentValue = q[i];

            int originalIndex = currentValue - 1;
            if (originalIndex > i + 2){
                System.out.println("Too chaotic");
                return;
            }

            int nextValue = q[i + 1];

            if (currentValue > nextValue) {
                swap(q, i, i + 1);
                increment(rotations, currentValue);
                i = -1;
            }
        }

        int result = rotations.values().stream().reduce((x, y) -> x + y).get();
        System.out.println(result);
    }

    private static int increment(HashMap<Integer, Integer> map, int key){
        if (!map.containsKey(key)) {
            map.put(key, 1);
            return 1;
        }

        int result = map.get(key) + 1;
        map.put(key, result);
        return result;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int buffer = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = buffer;
    }
}
