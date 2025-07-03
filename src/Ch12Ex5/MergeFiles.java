package Ch12Ex5;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

public class MergeFiles {
    private static int i;
    private static int j;
    private static int num;

    // Constructor to initialize indices and the number of files
    public MergeFiles() {
        i = 0;
        j = 0;
        num = 2;
    }

    /********************
     * Pre: None
     * Post: Appends a number to File3.txt
     * @param num The number to be added
     ********************/
    public static void AddNum(int num) {
        try {
            String line = String.valueOf(num);
            line += " ";
            File file = new File("/home/alibabji/IdeaProjects/ICS4U/src/Ch12Ex5/File3.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /********************
     * Pre: File1.txt and File2.txt exist
     * Post: Reads a number from either File1.txt or File2.txt based on the fileNum parameter
     * @param n The index of the number to be read
     * @param fileNum The number of the file to read from (1 for File1.txt, 2 for File2.txt)
     * @return The number read from the file
     * @throws IOException If an I/O error occurs
     ********************/
    public static int get(int n, int fileNum) throws IOException {
        try {
            BufferedReader reader;
            if (fileNum == 1) {
                File file1 = new File("/home/alibabji/IdeaProjects/ICS4U/src/Ch12Ex5/File1.txt");
                reader = new BufferedReader(new FileReader(file1));
            } else {
                File file2 = new File("/home/alibabji/IdeaProjects/ICS4U/src/Ch12Ex5/File2.txt");
                reader = new BufferedReader(new FileReader(file2));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual integers
                String[] numbers = line.trim().split("\\s+");

                // Calculate the index of the number within this line
                int lineLength = numbers.length;
                int startIndex = 0;
                int endIndex = startIndex + lineLength;

                // Check if the desired index falls within this line
                if (n >= startIndex && n < endIndex) {
                    // Calculate the index within the numbers array
                    int indexWithinLine = n - startIndex;
                    return Integer.parseInt(numbers[indexWithinLine]);
                }

                // Update the start index for the next line
                startIndex += lineLength;
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println(e);
        }
        // If n exceeds the total number of integers in the file
        return 2147483647;
    }

    /********************
     * Pre: File1.txt and File2.txt exist
     * Post: Compares numbers from File1.txt and File2.txt, adds the smaller one to File3.txt
     * @param a Index in File1.txt
     * @param b Index in File2.txt
     * @return 0 if a is smaller, 1 if b is smaller, -1 if both files reach the end
     * @throws IOException If an I/O error occurs
     ********************/
    public static int compare(int a, int b) throws IOException {
        try {
            int num1 = get(a, 1);
            int num2 = get(b, 2);
            if (num1 == 2147483647 && num2 == 2147483647) return -1;
            else if (num1 <= num2) {
                AddNum(num1);
                return 0;
            } else if (num2 < num1) {
                AddNum(num2);
                return 1;
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println(e);
        }
        return -1;
    }

    /********************
     * Pre: File1.txt and File2.txt exist
     * Post: Merges File1.txt and File2.txt into File3.txt in sorted order
     * @throws IOException If an I/O error occurs
     ********************/
    public static void run() throws IOException {
        while (num != -1) {
            num = compare(i, j);
            if (num == 0) i++;
            else if (num == 1) j++;
        }
    }
}
