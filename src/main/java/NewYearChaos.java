import java.util.HashMap;
import java.util.Optional;

public class NewYearChaos {
    public static void main(String[] args) {
        minimumBribes(new int[]{2, 1, 5, 3, 4});
        minimumBribes(new int[]{2, 5, 1, 3, 4});
        minimumBribes(new int[]{5, 1, 2, 3, 7, 8, 6, 4});
        minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
        minimumBribes(new int[]{1, 2, 5, 3, 4, 7, 8, 6});
    }

    static void minimumBribes(int[] q) {
        int result = 0;

        for (int i = 0; i < q.length; i++) {
            int currentValue = q[i];
            int originalIndex = currentValue - 1;

            if (originalIndex > i + 2) {
                System.out.println("Too chaotic");
                return;
            }
        }

        for (int i = 1; i < q.length - 1; i++) {
            int prevValue = q[i - 1];
            int currentValue = q[i];
            int nextValue = q[i + 1];

            if (prevValue < currentValue && currentValue < nextValue) {
                continue;
            }

            result++;

            if (prevValue > currentValue) {
                swap(q, i, i - 1);
                i = Math.max(0, i - 2);
                continue;
            }

            swap(q, i, i + 1);
            i = Math.max(0, i - 1);
        }

        System.out.println(result);
    }

    private static void swap(int[] arr, int index1, int index2) {
        int buffer = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = buffer;
    }
}
