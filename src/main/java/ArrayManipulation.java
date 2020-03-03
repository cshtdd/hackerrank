public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        long[] arr = new long[n];
        long result = -1;

        for (int i = 0; i < queries.length; i++) {
            int startIndex = queries[i][0] - 1;
            int endIndex = queries[i][1] - 1;
            int increment = queries[i][2];

            long largestValue = incrementArray(arr, startIndex, endIndex, increment);
            if (largestValue > result){
                result = largestValue;
            }
        }

        return result;
    }

    private static long incrementArray(long[] arr, int startIndex, int endIndex, int increment) {
        long result = -1;

        for (int i = startIndex; i <= endIndex; i++) {
            arr[i] += increment;

            if (arr[i] > result) {
                result = arr[i];
            }
        }

        return result;
    }
}
