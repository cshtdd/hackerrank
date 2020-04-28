public class DFS {

    public static final int VISITED = -1;

    private static void traverse(int row, int col, int rowCount, int colCount, int[][] matrix) {
        if (row < 0 || col < 0 ||
                row >= rowCount || col >= colCount) {
            return;
        }

        if (matrix[row][col] == VISITED){
            return;
        }

        System.out.println(matrix[row][col]);
        matrix[row][col] = VISITED;

        traverse(row - 1, col, rowCount, colCount, matrix);
        traverse(row, col + 1, rowCount, colCount, matrix);
        traverse(row + 1, col, rowCount, colCount, matrix);
        traverse(row, col - 1, rowCount, colCount, matrix);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 2, 3, 4, 5, 6},
                new int[]{7, 8, 9, 10, 11, 12},
                new int[]{13, 14, 15, 16, 17, 18},
                new int[]{19, 20, 21, 22, 23, 24}
        };

        traverse(0, 0, 4, 6, matrix);
    }
}
