public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        int[] arr = new int[n];
        int result = -1;

        for (int i = 0; i < queries.length; i++) {
            int startIndex = queries[i][0] - 1;
            int endIndex = queries[i][1] - 1;
            int increment = queries[i][2];

            int largestValue = incrementArray(arr, startIndex, endIndex, increment);
            if (largestValue > result){
                result = largestValue;
            }
        }

        return result;
    }

    private static int incrementArray(int[] arr, int startIndex, int endIndex, int increment) {
        int result = -1;

        for (int i = startIndex; i <= endIndex; i++) {
            arr[i] += increment;

            if (arr[i] > result) {
                result = arr[i];
            }
        }

        return result;
    }
}
