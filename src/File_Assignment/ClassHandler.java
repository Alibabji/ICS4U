package File_Assignment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class ClassHandler {
    private static double[] subjectNum;
    private static int studentNum;
    private static long lineCount;
    private static String[] Subject;

    // Constructor to initialize studentNum
    public ClassHandler() {
        studentNum = 0;
    }

    /********************
     * Pre: None
     * Post: Appends a line to the marks.txt file
     * @param line The line to be added
     ********************/
    public static void AddLine(String line) {
        try {
            File file = new File("/home/alibabji/IdeaProjects/ICS4U/src/File_Assignment/marks.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /********************
     * Pre: Subject array is initialized
     * Post: Initializes marks.txt with header containing subjects
     ********************/
    public static void InitMarks() {
        String data = "First Name     Last Name      ";
        for (int i = 0; i < Subject.length; i++) {
            data += Subject[i];
            for (int j = 0; j < 10 - Subject[i].length(); j++)
                data += " ";
        }
        data += "Average";
        AddLine(data);
    }

    /********************
     * Pre: data.txt file exists
     * Post: Adds mark for a student to marks.txt
     * @param lineNum The line number of the student's data in data.txt
     ********************/
    public static void AddMark(int lineNum) {
        double average = 0;
        String[] temp;
        Path path = Paths.get("/home/alibabji/IdeaProjects/ICS4U/src/File_Assignment/data.txt");
        try {
            Stream<String> stream = Files.lines(path);
            String list = stream.skip(lineNum).findFirst().get();
            temp = list.split(" ");
            String data = "";
            for (int i = 0; i < 2; i++) {
                data += temp[i];
                for (int j = 0; j < 15 - temp[i].length(); j++)
                    data += " ";
            }
            for (int i = 2; i < Subject.length + 2; i++) {
                data += temp[i];
                for (int j = 0; j < 10 - temp[i].length(); j++)
                    data += " ";
                subjectNum[i - 2] += Double.parseDouble(temp[i]);
                average += Double.parseDouble(temp[i]);
            }
            average /= Subject.length * 1.0;
            average = (double) Math.round(average * 100) / 100;
            subjectNum[Subject.length] += average;
            data += Double.toString(average);
            AddLine(data);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /********************
     * Pre: Subject array is initialized, studentNum is initialized
     * Post: Outputs average marks for each subject to marks.txt
     ********************/
    public static void OutputAvg() {
        String data = "Average                       ";
        for (int i = 0; i < subjectNum.length; i++) {
            double temp = (double) subjectNum[i] / studentNum;
            String str = Double.toString((double) Math.round(temp * 100) / 100);
            data += str;
            for (int j = 0; j < 10 - str.length(); j++) {
                data += " ";
            }
        }
        AddLine(data);
    }

    /********************
     * Pre: data.txt file exists
     * Post: Reads data from data.txt, initializes subjects and calculates averages
     ********************/
    public static void Run() {
        Path path = Paths.get("/home/alibabji/IdeaProjects/ICS4U/src/File_Assignment/data.txt");
        try {
            lineCount = Files.lines(path).count();
            if (lineCount == 0) {
                System.out.println("There is nothing written in data.txt!! Please Edit and Rerun!!");
                System.exit(0);
            }
            Stream<String> stream = Files.lines(path);
            String list = stream.skip(0).findFirst().get();
            Subject = list.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Subject.length > 8) {
            System.out.println("Maximum number of subjects are 8!!\nPlease Edit data.txt and rerun!");
            System.exit(0);
        }

        subjectNum = new double[Subject.length + 1];

        InitMarks();

        for (int i = 1; i < lineCount; i++) {
            AddMark(i);
            studentNum++;
        }

        OutputAvg();

    }

}
