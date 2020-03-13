import java.util.Arrays;
import java.util.function.Function;

public class MultiThreadFizzBuzz {
    static class FizzBuzz implements Runnable{
        private final int upperBound;
        private final Function<Integer, Boolean> condition;
        private final String formatExpression;

        public FizzBuzz(int upperBound, Function<Integer, Boolean> condition, String formatExpression) {
            this.upperBound = upperBound;
            this.condition = condition;
            this.formatExpression = formatExpression;
        }

        @Override
        public void run() {
            for (int i = 1; i <= upperBound; i++) {
                if (condition.apply(i)){
                    System.out.println(String.format(formatExpression, i));
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int upperBound = 20;

        FizzBuzz[] fbs = new FizzBuzz[]{
                new FizzBuzz(upperBound, (i) -> i % 5 == 0 && i % 3 == 0, "%d - FizzBuzz"),
                new FizzBuzz(upperBound, (i) -> i % 5 == 0 && i % 3 != 0, "%d - Buzz"),
                new FizzBuzz(upperBound, (i) -> i % 5 != 0 && i % 3 == 0, "%d - Fizz"),
                new FizzBuzz(upperBound, (i) -> i % 5 != 0 && i % 3 != 0, "%d")
        };

        Thread[] threads = Arrays.stream(fbs)
                .map(Thread::new)
                .toArray(Thread[]::new);

        Arrays.stream(threads)
                .forEach(Thread::start);

        for(Thread t : threads){
            t.join();
        }
    }
}
