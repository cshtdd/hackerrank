public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        long[] arr = new long[n];

        for (int i = 0; i < queries.length; i++) {
            int startIndex = queries[i][0] - 1;
            int endIndex = queries[i][1];
            int increment = queries[i][2];

            arr[startIndex] += increment;
            if (endIndex < n){
                arr[endIndex] -= increment;
            }
        }

        long result = arr[0];
        long buffer = arr[0];

        for (int i = 1; i < n; i++) {
            buffer += arr[i];

            if (buffer > result){
                result = buffer;
            }
        }

        return result;
    }
}
