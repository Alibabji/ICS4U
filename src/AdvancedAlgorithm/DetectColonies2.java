package AdvancedAlgorithm;
/*
Name: Nick Jeong
Title: DetectColonies2
Date: Jun 1, 2024
Description: d
*/
import java.util.Scanner;

public class DetectColonies2 {

    static int rows, cols;
    static int[][] slide;
    static boolean[][] visited;

    /**
     * The main method to read slide data, detect colonies, and print the results.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read slide dimensions
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        scanner.nextLine();  // consume the remaining newline

        // Initialize the slide and visited arrays
        slide = new int[rows][cols];
        visited = new boolean[rows][cols];

        // Read slide data
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < cols; j++) {
                slide[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        // Detect colonies
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && slide[i][j] != 0) {
                    int color = slide[i][j];
                    ColonyInfo colony = new ColonyInfo(color, i, j);
                    detectColony(i, j, color, colony);
                    System.out.println("Color: " + color + ", Size: " + colony.size + ", Location: " + (colony.startRow + 1) + ", " + (colony.startCol + 1));
                }
            }
        }

        scanner.close();
    }

    /**
     * Recursively detects the size of a colony starting from a given cell.
     *
     * @param row    The current row index.
     * @param col    The current column index.
     * @param color  The color of the colony to detect.
     * @param colony The ColonyInfo object to store the size and starting position of the colony.
     */
    static void detectColony(int row, int col, int color, ColonyInfo colony) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || slide[row][col] != color) {
            return;
        }

        visited[row][col] = true;
        colony.size++;

        // Check all four directions (up, down, left, right)
        detectColony(row - 1, col, color, colony);
        detectColony(row + 1, col, color, colony);
        detectColony(row, col - 1, color, colony);
        detectColony(row, col + 1, color, colony);
    }

    /**
     * Inner class to store information about a colony.
     */
    static class ColonyInfo {
        int color;
        int startRow;
        int startCol;
        int size;

        /**
         * Constructor for the ColonyInfo class.
         *
         * @param color    The color of the colony.
         * @param startRow The starting row index of the colony.
         * @param startCol The starting column index of the colony.
         */
        ColonyInfo(int color, int startRow, int startCol) {
            this.color = color;
            this.startRow = startRow;
            this.startCol = startCol;
            this.size = 0;
        }
    }
}
