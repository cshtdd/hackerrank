import java.util.Arrays;

public class SwapNumbersInPlace {
    public static void swap(int[] arr){
        if (arr.length != 2){
            throw new IllegalArgumentException();
        }

        arr[1] += arr[0];
        arr[0] = arr[1] - arr[0];
        arr[1] -= arr[0];
    }

    public static String toString(int[] arr){
        String[] numbers = Arrays.stream(arr).mapToObj(i -> String.format("%d", i)).toArray(String[]::new);
        return String.join(", ", numbers);
    }

    private static void assertEquals(String a, String b){
        System.out.println(String.format("Assert %s == %s", a, b));

        if (a == null && b != null){
            throw new AssertionError();
        }

        if (a != null && b == null){
            throw new AssertionError();
        }

        if (!a.equals(b)){
            throw new AssertionError();
        }
    }

    public static void main(String[] args){
        int[] arr = null;

        arr = new int[]{ 3, 5 };
        assertEquals("3, 5", toString(arr));
        swap(arr);
        assertEquals("5, 3", toString(arr));

        arr = new int[]{ -3, 5 };
        assertEquals("-3, 5", toString(arr));
        swap(arr);
        assertEquals("5, -3", toString(arr));

        arr = new int[]{ -3, -5 };
        assertEquals("-3, -5", toString(arr));
        swap(arr);
        assertEquals("-5, -3", toString(arr));
    }
}
