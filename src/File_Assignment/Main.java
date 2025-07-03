package File_Assignment;
/*
Name: Nick Jeong
Title: Array Assignment
Date: March 22nd, 2024
Description: Automatically calculates the average and output it on file
*/
import java.io.IOException;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) {
        // Create an empty marks.txt file if it doesn't exist
        try {
            new FileOutputStream("/home/alibabji/IdeaProjects/ICS4U/src/File_Assignment/marks.txt").close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Instantiate ClassHandler object and run the main process
        ClassHandler file = new ClassHandler();
        file.Run();
        System.out.println("Done!");
    }
}
