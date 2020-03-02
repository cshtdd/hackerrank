public class CountingValleys_4 {
    static int countingValleys(int n, String s) {
        int result = 0;
        int altitude = 0;
        boolean isValley = false;

        for (int c : s.chars().toArray()){
            if (c == 'D'){
                altitude--;
            }
            if (c == 'U'){
                altitude++;
            }

            if (!isValley){
                if (altitude < 0){
                    isValley = true;
                }
            }
            else {
                if (altitude == 0){
                    result++;
                    isValley = false;
                }
            }
        }

        return result;
    }
}
