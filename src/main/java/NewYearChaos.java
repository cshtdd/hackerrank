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
        }

        for (int i = 0; i < q.length - 1; i++) {
            int currentValue = q[i];
            int nextValue = q[i + 1];

            if (currentValue > nextValue) {
                swap(q, i, i + 1);

                if (!rotations.containsKey(currentValue)) {
                    rotations.put(currentValue, 1);
                } else {
                    int rotationsCount = rotations.get(currentValue) + 1;

//                    if (rotationsCount > 2){
//                        System.out.println("Too chaotic");
//                        return;
//                    }

                    rotations.put(currentValue, rotationsCount);
                }

                i = -1;
            }
        }

        int result = rotations.values().stream().reduce((x, y) -> x + y).get();
        System.out.println(result);
    }

    private static void swap(int[] arr, int index1, int index2) {
        int buffer = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = buffer;
    }
}
