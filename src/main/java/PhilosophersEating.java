import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

public class PhilosophersEating {
    public static class Philosopher implements Runnable {
        private static Lock chopsticksLock = new ReentrantLock();

        private final int index;
        public final boolean[] chopsticks;
        private final Random random = new Random();

        public Philosopher(int index, boolean[] chopsticks) {
            this.index = index;
            this.chopsticks = chopsticks;
        }

        @Override
        public void run() {
            try {
                while (!canEat()) {
                    waitPatiently();
                }

                eat();
            } catch (InterruptedException e) {
                log("Interrupted");
            } finally {
                finishEating();
            }
        }

        private void finishEating() {
            criticalSection(() -> {
                releaseChopsticks();
                return true;
            });
        }

        private boolean canEat() {
            return criticalSection(() -> {
                if (!isLeftChopstickFree() || !isRightChopstickFree()) {
                    return false;
                }

                grabChopsticks();
                return true;
            });
        }

        private boolean criticalSection(BooleanSupplier callback) {
            try {
                chopsticksLock.lock();
                return callback.getAsBoolean();
            } finally {
                chopsticksLock.unlock();
            }
        }

        private void eat() throws InterruptedException {
            try {
                log("Started eating");

                Thread.sleep(1000 + random.nextInt(1000));
            } finally {
                log("Finished eating");
            }
        }

        private void waitPatiently() throws InterruptedException {
            Thread.sleep(1);
        }

        private int leftChopstickIndex() {
            return index;
        }

        private int rightChopstickIndex() {
            return (index + 1) % chopsticks.length;
        }

        private boolean isLeftChopstickFree() {
            return isChopstickFreeAtIndex(leftChopstickIndex());
        }

        private boolean isRightChopstickFree() {
            return isChopstickFreeAtIndex(rightChopstickIndex());
        }

        private boolean isChopstickFreeAtIndex(int index) {
            return !chopsticks[index];
        }

        private void grabChopsticks() {
            grabChopstickAtIndex(leftChopstickIndex());
            grabChopstickAtIndex(rightChopstickIndex());
        }

        private void grabChopstickAtIndex(int index) {
            log(String.format("grab chopstick [%d]", index));
            chopsticks[index] = true;
        }

        private void releaseChopsticks() {
            releaseChopstickAtIndex(leftChopstickIndex());
            releaseChopstickAtIndex(rightChopstickIndex());
        }

        private void releaseChopstickAtIndex(int index) {
            log(String.format("release chopstick [%d]", index));
            chopsticks[index] = false;
        }

        private void log(String str) {
            System.out.println(String.format("Ph[%d] - %s", index, str));
        }
    }

    public static void main(String[] args) {
        int count = 10;
        boolean[] chopsticks = new boolean[count];
        Thread[] threads = new Thread[count];

        for (int i = 0; i < count; i++) {
            Philosopher philosopher = new Philosopher(i, chopsticks);
            threads[i] = new Thread(philosopher);
        }

        System.out.println("Launching philosophers");
        Arrays.stream(threads).forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread crashed");
            }
        }

        System.out.println("Ending philosophers");
    }
}
