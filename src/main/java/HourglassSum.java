import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HourglassSum {

    public static final int WIDTH = 3;

    static int hourglassSum(int[][] arr) {
        Integer result = null;

        for (int row = 0; row <= arr.length - WIDTH; row++) {
            int[] topRowData = arr[row];
            int[] midRowData = arr[row + 1];
            int[] botRowData = arr[row + WIDTH - 1];
            for (int col = 0; col <= topRowData.length - WIDTH; col++) {
                int topRowSum = sum(topRowData, col, WIDTH);
                int midRowSum = sum(midRowData, col + 1, 1);
                int botRowSum = sum(botRowData, col, WIDTH);

                int hourglassSum = topRowSum + midRowSum + botRowSum;
                if (result == null || hourglassSum > result) {
                    result = hourglassSum;
                }
            }
        }

        return result;
    }

    static int sum(int[] arr, int startIndex, int count) {
        int result = 0;

        for (int i = startIndex; i < (startIndex + count); i++) {
            result += arr[i];
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        String input =
                "1 1 1 0 0 0\n" +
                "0 1 0 0 0 0\n" +
                "1 1 1 0 0 0\n" +
                "0 0 2 4 4 0\n" +
                "0 0 0 2 0 0\n" +
                "0 0 1 2 4 0";
        runProgram(input);
    }

    private static void runProgram(String input) throws IOException {
        Scanner scanner = new Scanner(input);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
