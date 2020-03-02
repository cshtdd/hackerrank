public class MinimumSwaps {
    public static void main(String[] args){
        System.out.println(minimumSwaps(new int[]{ 1, 3, 5, 2, 4, 6, 7 }));
        System.out.println(minimumSwaps(new int[]{ 2, 3, 4, 1, 5 }));
        System.out.println(minimumSwaps(new int[]{ 7, 1, 3, 2, 4, 5, 6 }));
    }

    static int minimumSwaps(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - 1 == i){
                continue;
            }

            int correctLocation = indexOf(arr, i + 1);
            swap(arr, i, correctLocation);
            result++;
            i--;
        }
        return result;
    }

    private static int indexOf(int[] arr, int n){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n){
                return i;
            }
        }

        return -1;
    }

    static void swap(int[] arr, int i, int j){
        int buffer = arr[j];
        arr[j] = arr[i];
        arr[i] = buffer;
    }
}
