import java.util.ArrayList;
import java.util.List;

public class CompareTriplets {
    static List<Integer> compareTriplets(List<Integer> alice, List<Integer> bob) {
        List<Integer> result = new ArrayList<Integer>() {{
            add(0);
            add(0);
        }};

        for (int i = 0; i < 3; i++) {
            int aliceScore = alice.get(i);
            int bobScore = bob.get(i);

            if (aliceScore > bobScore) {
                result.set(0, result.get(0) + 1);
            } else if (bobScore > aliceScore) {
                result.set(1, result.get(1) + 1);
            }
        }

        return result;
    }
}
