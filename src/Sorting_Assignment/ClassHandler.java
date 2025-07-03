package Sorting_Assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;

public class ClassHandler {
    private static String[] name;
    private static int[][] points;

    /**
     * Adds a line of text to the output file.
     *
     * @param line The line of text to add.
     */
    public static void AddLine(String line) {
        try {
            File file = new File("/home/alibabji/IdeaProjects/ICS4U/src/Sorting_Assignment/output.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Constructor that initializes the names and points arrays based on the data file.
     */
    public ClassHandler() {
        Path path = Paths.get("/home/alibabji/IdeaProjects/ICS4U/src/Sorting_Assignment/data.txt");
        try {
            int lineCount = (int) Files.lines(path).count();
            name = new String[lineCount];
            points = new int[lineCount][5];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the name and points arrays by reading data from the file.
     */
    private static void nameInit() {
        String filePath = "/home/alibabji/IdeaProjects/ICS4U/src/Sorting_Assignment/data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int linenumber = 0;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                name[linenumber] = words[0];
                for (int i = 1; i < words.length; i++) {
                    points[linenumber][i - 1] = Integer.parseInt(words[i]);
                }
                points[linenumber][3] = points[linenumber][0] - points[linenumber][1] - points[linenumber][2];
                points[linenumber][4] = points[linenumber][1] * 2 + points[linenumber][3];
                linenumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats an integer as a string with a specified width.
     *
     * @param point The integer to format.
     * @return The formatted string.
     */
    private static String addStr(int point) {
        return String.format("%-4s", point);
    }

    /**
     * Outputs the formatted data to the output file and prints progress to the console.
     */
    private static void output() {
        for (int i = 0; i < name.length; i++) {
            String output = name[i].length() >= 4 ? name[i].substring(0, 4) : name[i];
            output = String.format("%-6s", output);
            output += addStr(points[i][0]) + addStr(points[i][1]) + addStr(points[i][2]) + addStr(points[i][3]) + addStr(points[i][4]);
            AddLine(output);
            System.out.printf("%.2f%%\n", ((100.0 / name.length) * (i + 1)));
        }
        System.out.println("Done!");
    }

    /**
     * Sorts the teams based on points and names.
     */
    public static void sort() {
        int n = name.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if ((points[j][4] < points[j + 1][4]) ||
                        (points[j][4] == points[j + 1][4] && name[j].compareTo(name[j + 1]) > 0)) {
                    // Swap names
                    String tempName = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = tempName;

                    // Swap corresponding points
                    int[] tempPoints = points[j];
                    points[j] = points[j + 1];
                    points[j + 1] = tempPoints;

                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Executes the main logic: initializes data, sorts it, and outputs the results.
     */
    public static void Run() {
        AddLine("Team  GP  W   L   OT  PTS");
        nameInit();
        if (name == null || points == null) {
            System.out.println("Initialization failed. Exiting.");
            return;
        }
        sort();
        output();
    }
}
