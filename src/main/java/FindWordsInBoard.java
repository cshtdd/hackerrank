import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FindWordsInBoard {
    private static class Location {
        public int row;
        public int col;

        public Location(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return row == location.row &&
                    col == location.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static String[] findWords(String[][] board, String[] dictionary){
        List<String> result = new ArrayList<String>();

        int boardSize = board.length;
        for (int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                // String c = board[row][col];
                List<Location> visitedLocations = new ArrayList<Location>();
                // visitedLocations.add(new Location(row, col));

                List<String> words = findWordsStartingAt(row, col, board, dictionary, "", visitedLocations);
                result.addAll(words);
            }
        }

        List<String> listWithoutDuplicates = new ArrayList<>(new HashSet<>(result));
        return listWithoutDuplicates.toArray(new String[0]);
    }

    private static List<String> findWordsStartingAt(int row, int col, String[][] board, String[] dictionary, String prevString, List<Location> visitedLocations){
        List<String> result = new ArrayList<String>();

        // if (prevString.equals("pineappl")){
        //     System.out.println("Found it");
        // }

        // if (dictContainsWord(prevString, dictionary)){
        //     result.add(prevString);
        // }

        int boardSize = board.length;
        String currentString = String.format("%s%s", prevString, board[row][col]);

        if (dictContainsWord(currentString, dictionary)){
            result.add(currentString);
        }

        Location currentLocation = new Location(row, col);
        List<Location> currentVisitedLocations = new ArrayList<Location>();
        currentVisitedLocations.addAll(visitedLocations);
        currentVisitedLocations.add(currentLocation);

        for (int newRow = row - 1; newRow <= row + 1; newRow++){
            for (int newCol = col - 1; newCol <= col + 1; newCol++){
                if (newRow < 0 || newRow >= boardSize){
                    continue;
                }

                if (newCol < 0 || newCol >= boardSize) {
                    continue;
                }

                Location location = new Location(newRow, newCol);

                if (currentLocation.equals(location)){
                    continue;
                }

                if (visitedLocations.contains(location)){
                    continue;
                }

                List<String> foundWords = findWordsStartingAt(newRow, newCol, board, dictionary, currentString, currentVisitedLocations);
                result.addAll(foundWords);
            }
        }

        return result;
    }

    private static boolean dictContainsWord(String word, String[] dictionary){
        for (int i = 0; i < dictionary.length; i++){
            if (dictionary[i].equals(word)){
                return true;
            }
        }
        return false;
    }

    private static void printArray(String[] arr){
        for (String s : arr){
            System.out.println(s);
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        String[] dictionary = new String[]{
                "app", "apple", "elk", "ent", "lan", "lei", "nap", "nil", "pane",
                "pea", "pen", "pie", "pin", "pine", "pineapple", "pip", "zilch"
        };

        String[][] board = new String[][]{
                new String[]{ "p", "e", "a" },
                new String[]{ "i", "n", "p" },
                new String[]{ "e", "l", "p" }
        };

        String[] expectedResult = new String[]{
                "app", "apple", "lei", "nap", "nil",
                "pane", "pea", "pen", "pie",
                "pin", "pine", "pineapple"
        };

        String[] actualResult = findWords(board, dictionary);

        System.out.println("Expected:");
        printArray(expectedResult);

        System.out.println("Actual:");
        printArray(actualResult);
    }
}
