import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Spiral {
    private enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP;

        public Direction next() {
            int nextValue = this.ordinal() + 1;
            Direction[] values = Direction.values();
            int nextValueIndex = nextValue % values.length;
            return values[nextValueIndex];
        }
    }

    private static class Location {
        public static final int EMPTY = 0;

        public final int row;
        public final int col;

        private Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean canMove(Direction direction, int[][] matrix) {
            Location updatedLocation = move(direction);

            if (updatedLocation.row < 0 ||
                    updatedLocation.col < 0) {
                return false;
            }

            if (updatedLocation.row >= matrix.length ||
                    updatedLocation.col >= matrix.length) {
                return false;
            }

            return matrix[updatedLocation.row][updatedLocation.col] == EMPTY;
        }

        public Location move(Direction direction) {
            Map<Direction, Location> deltas = new HashMap<Direction, Location>() {{
                put(Direction.RIGHT, new Location(0, 1));
                put(Direction.DOWN, new Location(1, 0));
                put(Direction.LEFT, new Location(0, -1));
                put(Direction.UP, new Location(-1, 0));
            }};

            Location delta = deltas.get(direction);
            return move(delta);
        }

        public Location move(Location delta) {
            return new Location(
                    row + delta.row,
                    col + delta.col
            );
        }
    }

    public static int[][] build(int size) {
        int[][] result = new int[size][size];
        int n = 1;

        Location current = new Location(0, 0);
        Direction direction = Direction.RIGHT;

        while (true) {
            result[current.row][current.col] = n;
            n++;

            Direction directionUpdated = direction;
            while (!current.canMove(directionUpdated, result)) {
                directionUpdated = directionUpdated.next();

                if (directionUpdated == direction) {
                    return result;
                }
            }

            direction = directionUpdated;
            current = current.move(direction);
        }
    }

    private static void print(int[][] matrix) {
        for (int[] rowNumbers : matrix) {
            String line = Arrays.stream(rowNumbers)
                    .mapToObj(i -> String.format("%02d", i))
                    .collect(Collectors.joining(" "));

            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(String.format("Building Spiral %d", i));
            print(build(i));
        }
    }
}