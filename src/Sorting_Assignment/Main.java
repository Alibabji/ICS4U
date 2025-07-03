package Sorting_Assignment;
/*
Name: Nick Jeong
Title: Sorting
Date: Jun 1, 2024
Description: sorts different hocky points
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String [] args){
        try {
            new FileOutputStream("/home/alibabji/IdeaProjects/ICS4U/src/Sorting_Assignment/output.txt").close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ClassHandler test = new ClassHandler();
        test.Run();
    }
}
