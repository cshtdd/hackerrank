public class JumpingClouds_2 {
    public static void main(String[] args) {
        System.out.println(jumpingOnClouds(new int[]{0, 0, 1, 0, 0, 1, 0}));
        System.out.println(jumpingOnClouds(new int[]{0, 0, 0, 0, 1, 0}));
        System.out.println(jumpingOnClouds(new int[]{0, 0, 0, 1, 0, 0}));
    }

    static int jumpingOnClouds(int[] c) {
        int result = 0;

        for (int i = 0; i < c.length - 1; i++) {
            result++;

            if (i + 2 < c.length && c[i + 2] == 0){
                i++;
            }
        }

        return result;
    }
}
