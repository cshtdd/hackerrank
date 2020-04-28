import java.util.ArrayList;
import java.util.List;

public class BFS {
    public static final int VISITED = -1;

    private static class Location {
        public final int row;
        public final int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        List<Location> neighborsWithinRange(int rowCount, int colCount) {
            List<Location> result = new ArrayList<>();

            if (row - 1 >= 0) {
                result.add(new Location(row - 1, col));
            }

            if (col + 1 < colCount) {
                result.add(new Location(row, col + 1));
            }

            if (row + 1 < rowCount) {
                result.add(new Location(row + 1, col));
            }

            if (col - 1 >= 0) {
                result.add(new Location(row, col - 1));
            }

            return result;
        }
    }

    private static void traverse(int row, int col, int rowCount, int colCount, int[][] matrix) {
        List<Location> queue = new ArrayList<>();
        queue.add(new Location(row, col));
        int index = 0;

        while (index < queue.size()) {
            Location current = queue.get(index);
            index++;

            if (isVisited(current, matrix)) {
                continue;
            }

            System.out.println(matrix[current.row][current.col]);
            markVisited(current, matrix);

            for (Location l : current.neighborsWithinRange(rowCount, colCount)) {
                if (!isVisited(l, matrix)) {
                    queue.add(l);
                }
            }
        }
    }

    private static boolean isVisited(Location l, int[][] matrix) {
        return matrix[l.row][l.col] == VISITED;
    }

    private static void markVisited(Location l, int[][] matrix) {
        matrix[l.row][l.col] = VISITED;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{ 1,  2,  3,  4,  5,  6},
                new int[]{ 7,  8,  9, 10, 11, 12},
                new int[]{13, 14, 15, 16, 17, 18},
                new int[]{19, 20, 21, 22, 23, 24}
        };

        traverse(0, 0, 4, 6, matrix);
    }
}
