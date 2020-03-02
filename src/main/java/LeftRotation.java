public class LeftRotation {
    static int[] rotLeft(int[] a, int d) {
        int buffer;
        int length = a.length;

        while (d > 0){
            buffer = a[0];

            for (int i = 0; i < length - 1; i++) {
                a[i] = a[i + 1];
            }

            a[length - 1] = buffer;
            d--;
        }

        return a;
    }
}
