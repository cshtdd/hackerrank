import java.util.HashMap;
import java.util.Map;

public class SockMerchant_1 {
    public static void main(String[] args){
        int n = sockMerchant(7, new int[]{1, 2, 1, 2, 1, 3, 2});
        System.out.println(String.format("Sock pair: %d", n));

        n = sockMerchant(9, new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20});
        System.out.println(String.format("Sock pair: %d", n));
    }

    static int sockMerchant(int count, int[] arr) {
        Map<Integer, Integer> sockSizeMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            int n = arr[i];
            if (!sockSizeMap.containsKey(n)){
                sockSizeMap.put(n, 1);
            }
            else {
                int prevCount = sockSizeMap.get(n);
                sockSizeMap.put(n, prevCount + 1);
            }
        }

        return sockSizeMap
                .values()
                .stream()
                .map(v -> v / 2)
                .reduce((x, y) -> x + y)
                .get();
    }
}
