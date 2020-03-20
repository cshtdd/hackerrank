public class BubbleSort {
    static void countSwaps(int[] arr) {
        int swapCount = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapCount++;
                }
            }
        }

        System.out.println(String.format("Array is sorted in %d swaps.", swapCount));
        System.out.println(String.format("First Element: %d", arr[0]));
        System.out.println(String.format("Last Element: %d", arr[n - 1]));
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
